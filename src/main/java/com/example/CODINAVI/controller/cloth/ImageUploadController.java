package com.example.CODINAVI.controller.cloth;

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

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("Imagefile") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("이미지가 비어 있습니다.", HttpStatus.BAD_REQUEST);
        }

        try {
            String key = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            s3Client.putObject(PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(key)
                            .build(),
                    software.amazon.awssdk.core.sync.RequestBody.fromBytes(file.getBytes()));
            return new ResponseEntity<>("이미지 업로드 성공: " + key, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("이미지 업로드 실패: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (SdkClientException e) {
            e.printStackTrace();
            return new ResponseEntity<>("AWS S3 클라이언트 오류: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (S3Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("AWS S3 서비스 오류: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("이미지 업로드 중 오류 발생: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
