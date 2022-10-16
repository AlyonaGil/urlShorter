package ru.stroki.test.services;

import ru.stroki.test.dto.UrlDto;
import ru.stroki.test.dto.UrlInfoDto;
import ru.stroki.test.entity.Url;
import ru.stroki.test.entity.User;

import java.util.List;

public interface UrlService {
    UrlDto addUrl(String longUrl, User user);
    List<UrlDto> getAllUrlsByUserId(Integer UserId);
    UrlInfoDto getById(Integer id);
    void deleteUrl(Integer id);

}
