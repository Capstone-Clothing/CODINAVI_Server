package com.example.CODINAVI.service.color;

import com.example.CODINAVI.dto.request.ColorRecRequest;
import com.example.CODINAVI.dto.response.color.ColorRecResponse;
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
        Color color = colorRecRepository.findByColorForApp(request.getColor());
        return new ColorRecResponse(color.getMatchColor());
    }

}



