package ru.stroki.test.services;

import ru.stroki.test.dto.StatisticsDto;
import ru.stroki.test.entity.User;

import java.time.LocalDateTime;
import java.util.List;

public interface TransitionService {
    List<StatisticsDto> getCountViewsUrls(User user, LocalDateTime startDate, LocalDateTime endDate);
    List<String> getReferersTop(User user);
    String getRedirect(String url, String referer);
}
