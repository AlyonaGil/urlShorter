package ru.stroki.test.utils;

import lombok.experimental.UtilityClass;
import ru.stroki.test.dto.UrlDto;
import ru.stroki.test.dto.UrlInfoDto;
import ru.stroki.test.dto.UserDto;
import ru.stroki.test.entity.Url;
import ru.stroki.test.entity.User;

@UtilityClass
public final class DtoMapper {
    public UserDto getDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .login(user.getLogin())
                .registrationDate(user.getCreateDate())
                .build();
    }

    public UrlDto getDto(Url url) {
        return UrlDto.builder()
                .id(url.getId())
                .longUrl(url.getLongUrl())
                .shortUrl(url.getShortUrl())
                .createDate(url.getCreateDate())
                .build();
    }

    public UrlInfoDto getInfoDto(Url url) {
        return UrlInfoDto.builder()
                .longUrl(url.getLongUrl())
                .shortUrl(url.getShortUrl())
                .user(DtoMapper.getDto(url.getUser()))
                .createDate(url.getCreateDate())
                .countOfViews(url.getTransitions().size())
                .deleteDate(url.getDeleteDate())
                .build();
    }
}
