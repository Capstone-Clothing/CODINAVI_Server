package com.example.CODINAVI.controller.cloth;

import com.example.CODINAVI.dto.request.cloth.ClothHistoryRequest;
import com.example.CODINAVI.dto.request.cloth.ClothRecommendHistoryRequest;
import com.example.CODINAVI.dto.response.cloth.ClothHistoryResponse;
import com.example.CODINAVI.service.cloth.ClothHistoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClothHistoryController {

    private final ClothHistoryService clothHistoryService;

    public ClothHistoryController(ClothHistoryService clothHistoryService) {
        this.clothHistoryService = clothHistoryService;
    }

    @GetMapping("/cloth/history")
    public List<ClothHistoryResponse> getClothHistory() {
        return clothHistoryService.getClothHistory();
    }

    @PostMapping("/cloth/history")
    public void saveClothHistory(@RequestBody ClothHistoryRequest request) {
        clothHistoryService.saveClothHistory(request);
    }

    @PostMapping("/cloth/recommendHistory")
    public void saveClothRecommendHistory(@RequestBody ClothRecommendHistoryRequest request) {
        clothHistoryService.saveClothRecommendHistory(request);
    }
}
