package com.example.CODINAVI.controller.cloth;

import com.example.CODINAVI.domain.cloth.ClothHistory;
import com.example.CODINAVI.dto.request.cloth.ClothHistoryRequest;
import com.example.CODINAVI.dto.request.cloth.ClothRecommendHistoryRequest;
import com.example.CODINAVI.dto.request.cloth.ColorRecommendHistoryRequest;
import com.example.CODINAVI.dto.response.cloth.ClothHistoryResponse;
import com.example.CODINAVI.dto.response.cloth.RecommendClothHistory;
import com.example.CODINAVI.dto.response.cloth.RecommendColorHistory;
import com.example.CODINAVI.service.cloth.ClothHistoryService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/cloth/recommendHistory/{id}")
    public RecommendClothHistory getClothRecommendHistory(@PathVariable Long id) {
        return clothHistoryService.getRecommendClothHistory(id);
    }

    @GetMapping("/cloth/colorRecommendHistory/{id}")
    public RecommendColorHistory getColorRecommendHistory(@PathVariable Long id) {
        return clothHistoryService.getColorRecommendHistory(id);
    }

    @PostMapping("/cloth/history")
    public ClothHistory saveClothHistory(@RequestBody ClothHistoryRequest request) {
        return clothHistoryService.saveClothHistory(request);
    }

    @PostMapping("/cloth/recommendHistory")
    public void saveClothRecommendHistory(@RequestBody ClothRecommendHistoryRequest request) {
        clothHistoryService.saveClothRecommendHistory(request);
    }

    @PostMapping("/cloth/colorRecommendHistory")
    public void saveColorRecommendHistory(@RequestBody ColorRecommendHistoryRequest request) {
        clothHistoryService.saveColorRecommendHistory(request);
    }
}
