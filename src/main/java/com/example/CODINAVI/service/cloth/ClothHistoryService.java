package com.example.CODINAVI.service.cloth;

import com.example.CODINAVI.domain.cloth.ClothHistory;
import com.example.CODINAVI.domain.cloth.ClothHistoryRepository;
import com.example.CODINAVI.domain.cloth.RecommendHistory;
import com.example.CODINAVI.domain.cloth.RecommendHistoryRepository;
import com.example.CODINAVI.domain.color.ColorRecommendHistory;
import com.example.CODINAVI.domain.color.ColorRecommendHistoryRepository;
import com.example.CODINAVI.dto.request.cloth.ClothHistoryRequest;
import com.example.CODINAVI.dto.request.cloth.ClothRecommendHistoryRequest;
import com.example.CODINAVI.dto.request.cloth.ColorRecommendHistoryRequest;
import com.example.CODINAVI.dto.response.cloth.ClothHistoryResponse;
import com.example.CODINAVI.dto.response.cloth.ClothRecommendHistoryResponse;
import com.example.CODINAVI.dto.response.cloth.ColorRecommendHistoryResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClothHistoryService {

    ClothHistoryRepository clothHistoryRepository;
    RecommendHistoryRepository recommendHistoryRepository;
    ColorRecommendHistoryRepository colorRecommendHistoryRepository;

    public ClothHistoryService(ClothHistoryRepository clothHistoryRepository, RecommendHistoryRepository recommendHistoryRepository, ColorRecommendHistoryRepository colorRecommendHistoryRepository) {
        this.clothHistoryRepository = clothHistoryRepository;
        this.recommendHistoryRepository = recommendHistoryRepository;
        this.colorRecommendHistoryRepository = colorRecommendHistoryRepository;
    }

    public List<ClothHistoryResponse> getClothHistory(String userId) {
        List<ClothHistory> allList = clothHistoryRepository.findAllByUserId(userId);
        ArrayList<ClothHistoryResponse> responseList = new ArrayList<>();
        for (int i = 0; i < allList.size(); i++) {
            if (allList.get(i) != null) {
                responseList.add(new ClothHistoryResponse(allList.get(i).getId(), allList.get(i).getColor(), allList.get(i).getPattern(),
                        allList.get(i).getType()));
            }
        }

        return responseList;
    }

    public ClothRecommendHistoryResponse getRecommendClothHistory(Long id) {
        RecommendHistory history = recommendHistoryRepository.findByParentId(id);
        return new ClothRecommendHistoryResponse(history.getResult());
    }

    public ColorRecommendHistoryResponse getRecommendColorHistory(Long id) {
        ColorRecommendHistory history = colorRecommendHistoryRepository.findByParentId(id);
        return new ColorRecommendHistoryResponse(history.getResult());
    }
    public ClothHistory saveClothHistory(ClothHistoryRequest request) {
        ClothHistory clothHistory = new ClothHistory(request.getUserId(), request.getColor(), request.getPattern(), request.getType());
        clothHistoryRepository.save(clothHistory);
        return clothHistory;
    }

    public void saveColorRecommendHistory(ColorRecommendHistoryRequest request) {
        ColorRecommendHistory history = new ColorRecommendHistory(request.getParentId(), request.getResult());
        colorRecommendHistoryRepository.save(history);
    }

    public void saveClothRecommendHistory(ClothRecommendHistoryRequest request) {
        RecommendHistory history = new RecommendHistory(request.getParentId(), request.getResult());
        recommendHistoryRepository.save(history);
    }
}
