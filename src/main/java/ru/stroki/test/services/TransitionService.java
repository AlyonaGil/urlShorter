package ru.stroki.test.services;

import org.springframework.validation.annotation.Validated;
import ru.stroki.test.dto.StatisticsDto;
import ru.stroki.test.entity.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
public interface TransitionService {
    List<StatisticsDto> getCountViewsUrls(
            User user,
            @NotNull String startDate,
            @NotNull String endDate);
    List<String> getReferersTop(User user);
    String getRedirect(@NotNull @NotBlank String url, String referer);
}
