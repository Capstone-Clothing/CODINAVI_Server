package com.example.CODINAVI.controller.cloth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ImageUploadController {

    private static final Logger logger = LoggerFactory.getLogger(ImageUploadController.class);

    private final S3Client s3Client;

    @Value("${cloud.aws.s3.bucketName}")
    private String bucketName;

    public ImageUploadController(
            @Value("${cloud.aws.credentials.accessKey}") String accessKeyId,
            @Value("${cloud.aws.credentials.secretKey}") String secretAccessKey,
            @Value("${cloud.aws.region.static}") String region) {
        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKeyId, secretAccessKey);
        this.s3Client = S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .build();
    }

    @GetMapping("/upload")
    public ResponseEntity<String> handleGetRequests() {
        return new ResponseEntity<>("GET method is not supported for this endpoint. Please use POST method.", HttpStatus.METHOD_NOT_ALLOWED);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("Imagefile") MultipartFile file) {
        logger.debug("Received upload request");

        if (file.isEmpty()) {
            logger.warn("Received empty file");
            return new ResponseEntity<>("이미지가 비어 있습니다.", HttpStatus.BAD_REQUEST);
        }

        try {
            String key = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            s3Client.putObject(PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(key)
                            .contentType(file.getContentType())
                            .contentDisposition("inline")
                            .build(),
                    software.amazon.awssdk.core.sync.RequestBody.fromBytes(file.getBytes()));
            logger.info("Image uploaded successfully with key: {}", key);
            return new ResponseEntity<>("이미지 업로드 성공: " + key, HttpStatus.OK);
        } catch (IOException e) {
            logger.error("IOException during image upload", e);
            return new ResponseEntity<>("이미지 업로드 실패: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (SdkClientException e) {
            logger.error("AWS S3 client error during image upload", e);
            return new ResponseEntity<>("AWS S3 클라이언트 오류: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (S3Exception e) {
            logger.error("AWS S3 service error during image upload", e);
            return new ResponseEntity<>("AWS S3 서비스 오류: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            logger.error("Unknown error during image upload", e);
            return new ResponseEntity<>("이미지 업로드 중 오류 발생: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}