package ru.stroki.test.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.stroki.test.dto.UrlDto;
import ru.stroki.test.dto.UrlInfoDto;
import ru.stroki.test.dto.UserDto;
import ru.stroki.test.entity.Url;
import ru.stroki.test.entity.User;
@Component
public final class DtoMapper {
    @Value("${domain}")
    private String appDomain;

    public UserDto getDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .login(user.getLogin())
                .registrationDate(user.getCreateDate())
                .build();
    }

    public UrlDto getDto(Url url) {
        //todo домен взять из application.properties
        return UrlDto.builder()
                .id(url.getId())
                .longUrl(url.getLongUrl())
                .shortUrl(appDomain + url.getShortUrl())
                .createDate(url.getCreateDate())
                .build();
    }

    public UrlInfoDto getInfoDto(Url url) {
        return UrlInfoDto.builder()
                .longUrl(url.getLongUrl())
                .shortUrl(appDomain + url.getShortUrl())
                .user(getDto(url.getUser()))
                .createDate(url.getCreateDate())
                .countOfViews(url.getTransitions().size())
                .deleteDate(url.getDeleteDate())
                .build();
    }
}
