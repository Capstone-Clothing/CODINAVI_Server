package com.example.CODINAVI.service.cloth;

import com.example.CODINAVI.domain.ClothHistory;
import com.example.CODINAVI.domain.ClothHistoryRepository;
import com.example.CODINAVI.dto.request.cloth.ClothHistoryRequest;
import com.example.CODINAVI.dto.response.cloth.ClothHistoryResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClothHistoryService {

    ClothHistoryRepository clothHistoryRepository;

    public ClothHistoryService(ClothHistoryRepository clothHistoryRepository) {
        this.clothHistoryRepository = clothHistoryRepository;
    }

    public List<ClothHistoryResponse> getClothHistory() {
        List<ClothHistory> allList = clothHistoryRepository.findAll();
        ArrayList<ClothHistoryResponse> responseList = new ArrayList<>();
        for (int i = 0; i < allList.size(); i++) {
            if (allList.get(i) != null) {
                responseList.add(new ClothHistoryResponse(allList.get(i).getColor(), allList.get(i).getPattern(),
                        allList.get(i).getType()));
            }
        }

        return responseList;
    }

    public void saveClothHistory(ClothHistoryRequest request) {
        ClothHistory clothHistory = new ClothHistory(request.getColor(), request.getPattern(), request.getType());
        clothHistoryRepository.save(clothHistory);
    }
}
