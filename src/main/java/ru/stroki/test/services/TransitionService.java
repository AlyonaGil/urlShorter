package ru.stroki.test.services;

import ru.stroki.test.dto.TransitionDto;

import java.time.LocalDateTime;
import java.util.List;

public interface TransitionService {
    List<TransitionDto> getCountViewsUrls(LocalDateTime startDate, LocalDateTime endDate);
    List<String> getReferersTop();
}
