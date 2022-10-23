package ru.stroki.test.mapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.stroki.test.dto.*;
import ru.stroki.test.entity.Url;
import ru.stroki.test.entity.User;
@Component
public final class DtoMapper {
    @Value("${domain}")
    private String appDomain;

    public ExceptionDto getExceptionDto(Integer status, String message){
        return ExceptionDto.builder()
                .status(status)
                .message(message)
                .build();
    }

    public StatisticsDto getStatisticsDto(String shortUrl, Integer count){
        return StatisticsDto.builder()
                .shortUrl(appDomain + "urlShorter/redirect/" + shortUrl)
                .countTransition(count)
                .build();
    }

    public UserDto getUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .login(user.getLogin())
                .registrationDate(String.valueOf(user.getCreateDate()))
                .build();
    }

    public UrlDto getUrlDto(Url url) {
        return UrlDto.builder()
                .id(url.getId())
                .longUrl(url.getLongUrl())
                .shortUrl(appDomain + "urlShorter/redirect/" + url.getShortUrl())
                .createDate(String.valueOf(url.getCreateDate()))
                .deleteDate(String.valueOf(url.getDeleteDate()))
                .build();
    }

    public UrlInfoDto getUrlInfoDto(Url url) {
        return UrlInfoDto.builder()
                .longUrl(url.getLongUrl())
                .shortUrl(appDomain + "urlShorter/redirect/" + url.getShortUrl())
                .user(getUserDto(url.getUser()))
                .createDate(String.valueOf(url.getCreateDate()))
                .countOfViews(url.getTransitions().size())
                .deleteDate(String.valueOf(url.getDeleteDate()))
                .build();
    }
}
