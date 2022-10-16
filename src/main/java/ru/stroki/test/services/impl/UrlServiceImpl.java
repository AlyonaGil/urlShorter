package ru.stroki.test.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stroki.test.dto.UrlDto;
import ru.stroki.test.dto.UrlInfoDto;
import ru.stroki.test.entity.Url;
import ru.stroki.test.entity.User;
import ru.stroki.test.repository.UrlRepository;
import ru.stroki.test.repository.UserRepository;
import ru.stroki.test.services.UrlService;
import ru.stroki.test.utils.DtoMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UrlServiceImpl implements UrlService {
    @Autowired
    private UrlRepository urlRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public UrlDto addUrl(String longUrl, User user) {
//        Url url = Url.builder().build();
//        url.setLongUrl(longUrl);
//        url.setUser(user);
//        //...
//        urlRepository.save(url);
        return null;
    }

    @Override
    public List<UrlDto> getAllUrlsByUserId(Integer UserId) {
        Optional<User> user = userRepository.findById(UserId);
        return user.map(User::getUrls)
                .map(urls -> urls
                        .stream()
                        .map(DtoMapper::getDto)
                        .collect(Collectors.toList())).orElse(null);
    }

    @Override
    public UrlInfoDto getById(Integer id) {
        Optional<Url> urlOptional = urlRepository.findById(id);
        return urlOptional.map(DtoMapper::getInfoDto).orElse(null);
    }

    @Override
    public void deleteUrl(Integer id) {
        Optional<Url> url = urlRepository.findById(id);
        url.map(u -> {
            u.setDeleteDate(LocalDateTime.now());
            return urlRepository.save(u);
        }).orElse(null);
    }
}
