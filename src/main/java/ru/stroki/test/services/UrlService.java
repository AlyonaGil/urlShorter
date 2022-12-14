package ru.stroki.test.services;

import org.springframework.validation.annotation.Validated;
import ru.stroki.test.dto.AddUrlDto;
import ru.stroki.test.dto.UrlDto;
import ru.stroki.test.dto.UrlInfoDto;
import ru.stroki.test.entity.User;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
@Validated
public interface UrlService {
    UrlDto addUrl(@Valid AddUrlDto addUrlDto, User user);
    List<UrlDto> getAllUrls(User user);
    UrlInfoDto getById(@NotNull Integer id, User user);
    void deleteUrl(@NotNull Integer id, User user);

}
