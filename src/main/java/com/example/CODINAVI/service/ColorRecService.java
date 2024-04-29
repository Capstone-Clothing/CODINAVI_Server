package com.example.CODINAVI.service;

import com.example.CODINAVI.dto.request.ColorRecRequest;
import com.example.CODINAVI.dto.response.ColorRecResponse;
import com.example.CODINAVI.domain.entity.Color;
import com.example.CODINAVI.domain.entity.ColorRecRepository;
import org.springframework.stereotype.Service;

@Service
public class ColorRecService {

    private final ColorRecRepository colorRecRepository;

    public ColorRecService(ColorRecRepository colorRecRepository) {
        this.colorRecRepository = colorRecRepository;
    }

    public ColorRecResponse getColorRec(ColorRecRequest request) {
        Color color = colorRecRepository.findByColor(request.getColor());
        return new ColorRecResponse(color.getMatchColor());
    }
}
