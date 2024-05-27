package com.example.CODINAVI.service.color;

import com.example.CODINAVI.dto.request.ColorCreateRequest;
import com.example.CODINAVI.dto.request.ColorRecRequest;
import com.example.CODINAVI.dto.response.ColorRecResponse;
import com.example.CODINAVI.domain.Color;
import com.example.CODINAVI.domain.ColorRecRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
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

    public void insertColor(ColorCreateRequest request) {
        Color color = new Color(request.getColor(), request.getRgb(), request.getPantoneRgb(), request.getHsv(), request.getTcx(), request.getColorForApp(), request.getMatchColor());
        colorRecRepository.save(color);
    }
}