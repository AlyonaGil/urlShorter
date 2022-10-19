package ru.stroki.test.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.stroki.test.dto.UrlDto;
import ru.stroki.test.dto.UrlInfoDto;
import ru.stroki.test.entity.Url;
import ru.stroki.test.entity.User;
import ru.stroki.test.repository.UrlRepository;
import ru.stroki.test.services.UrlService;
import ru.stroki.test.mapper.DtoMapper;
import ru.stroki.test.utils.UrlConverter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UrlServiceImpl implements UrlService {

    private final UrlRepository urlRepository;

    private final DtoMapper dtoMapper;


    @Override
    public UrlDto addUrl(String longUrl, User user) {
        //todo проверка на валидность longUrl
        Integer fromSeq = urlRepository.getIntFromSeq();
        //todo проверка что пришло число
        String shortUrl = UrlConverter.getShortUrl(fromSeq);
        Url url = Url.builder()
                .longUrl(longUrl)
                .shortUrl(shortUrl)
                .user(user)
                .build();
        Url urlSaved = urlRepository.saveAndFlush(url);
        return dtoMapper.getDto(urlSaved);
    }

    @Override
    public List<UrlDto> getAllUrls(User user) {
        return urlRepository.getUrlsByUser(user)
                .stream()
                .map(dtoMapper::getDto)
                .collect(Collectors.toList());
    }

    @Override
    public UrlInfoDto getById(Integer id, User user) {
        //todo вренуть ошибку вместо null
        return urlRepository.findByIdAndUser(id, user)
                .map(dtoMapper::getInfoDto)
                .orElse(null);
    }

    @Override
    public void deleteUrl(Integer id, User user) {
        //todo вренуть ошибку вместо null
        urlRepository.findByIdAndUser(id, user)
                .map(u -> {
                    u.setDeleteDate(LocalDateTime.now());
                    return urlRepository.save(u);
                }).orElse(null);
    }
}
