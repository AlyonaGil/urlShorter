package ru.stroki.test.services.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.stereotype.Service;
import ru.stroki.test.dto.AddUrlDto;
import ru.stroki.test.dto.UrlDto;
import ru.stroki.test.dto.UrlInfoDto;
import ru.stroki.test.entity.Url;
import ru.stroki.test.entity.User;
import ru.stroki.test.repository.UrlRepository;
import ru.stroki.test.services.UrlService;
import ru.stroki.test.mapper.DtoMapper;
import ru.stroki.test.utils.UrlConverter;
import ru.stroki.test.validation.ValidationException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class UrlServiceImpl implements UrlService {

    private final UrlRepository urlRepository;

    private final DtoMapper dtoMapper;

    @Override
    public UrlDto addUrl(AddUrlDto addUrlDto, User user) {
        String longUrl = addUrlDto.getLongUrl();
        if (longUrl.length() > 1000){
            throw new ValidationException("Длина url не должна превышать 1000 символов");
        }
        UrlValidator urlValidator = new UrlValidator();
        if (!urlValidator.isValid(longUrl)) {
            throw new ValidationException("Url не валидный");
        }
        Integer fromSeq = urlRepository.getIntFromSeq();
        String shortUrl = UrlConverter.getShortUrl(fromSeq);
        Url url = Url.builder()
                .longUrl(longUrl)
                .shortUrl(shortUrl)
                .user(user)
                .build();
        Url urlSaved = urlRepository.saveAndFlush(url);
        log.info("Добавлен новый url");
        return dtoMapper.getUrlDto(urlSaved);
    }

    @Override
    public List<UrlDto> getAllUrls(User user) {
        return urlRepository.getUrlsByUser(user)
                .stream()
                .map(dtoMapper::getUrlDto)
                .collect(Collectors.toList());
    }

    @Override
    public UrlInfoDto getById(Integer id, User user) {
        return urlRepository.findByIdAndUser(id, user)
                .map(dtoMapper::getUrlInfoDto)
                .orElseThrow(() -> new ValidationException("Url с  заданным id не существует"));
    }

    @Override
    public void deleteUrl(Integer id, User user) {
        urlRepository.findByIdAndUser(id, user)
                .map(u -> {
                    u.setDeleteDate(LocalDateTime.now());
                    return urlRepository.save(u);
                }).orElseThrow(() -> new ValidationException("Url с  заданным id не существует"));
    }
}
