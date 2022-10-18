package ru.stroki.test.services;

import ru.stroki.test.dto.StatisticsDto;

import java.time.LocalDateTime;
import java.util.List;

public interface StatisticsService {
    List<StatisticsDto> getCountViewsUrls(LocalDateTime startDate, LocalDateTime endDate);
    List<String> getReferersTop();
}
