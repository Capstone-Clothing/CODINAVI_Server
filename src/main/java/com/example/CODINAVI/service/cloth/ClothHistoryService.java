package com.example.CODINAVI.service.cloth;

import com.example.CODINAVI.domain.ClothHistory;
import com.example.CODINAVI.domain.ClothHistoryRepository;
import com.example.CODINAVI.domain.RecommendHistory;
import com.example.CODINAVI.domain.RecommendHistoryRepository;
import com.example.CODINAVI.dto.request.cloth.ClothHistoryRequest;
import com.example.CODINAVI.dto.request.cloth.ClothRecommendHistoryRequest;
import com.example.CODINAVI.dto.response.cloth.ClothHistoryResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClothHistoryService {

    ClothHistoryRepository clothHistoryRepository;
    RecommendHistoryRepository recommendHistoryRepository;

    public ClothHistoryService(ClothHistoryRepository clothHistoryRepository, RecommendHistoryRepository recommendHistoryRepository) {
        this.clothHistoryRepository = clothHistoryRepository;
        this.recommendHistoryRepository = recommendHistoryRepository;
    }

    public List<ClothHistoryResponse> getClothHistory() {
        List<ClothHistory> allList = clothHistoryRepository.findAll();
        ArrayList<ClothHistoryResponse> responseList = new ArrayList<>();
        for (int i = 0; i < allList.size(); i++) {
            if (allList.get(i) != null) {
                responseList.add(new ClothHistoryResponse(allList.get(i).getId(), allList.get(i).getColor(), allList.get(i).getPattern(),
                        allList.get(i).getType()));
            }
        }

        return responseList;
    }

    public void saveClothHistory(ClothHistoryRequest request) {
        ClothHistory clothHistory = new ClothHistory(request.getColor(), request.getPattern(), request.getType());
        clothHistoryRepository.save(clothHistory);
    }

    public void saveClothRecommendHistory(ClothRecommendHistoryRequest request) {
        RecommendHistory history = new RecommendHistory(request.getResult());
        recommendHistoryRepository.save(history);
    }
}
