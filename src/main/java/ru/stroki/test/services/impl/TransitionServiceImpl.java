package ru.stroki.test.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.stroki.test.dto.TransitionDto;
import ru.stroki.test.entity.Url;
import ru.stroki.test.repository.TransitionRepository;
import ru.stroki.test.repository.UrlRepository;
import ru.stroki.test.services.TransitionService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransitionServiceImpl implements TransitionService {

    @Autowired
    private TransitionRepository transitionRepository;

    @Autowired
    private UrlRepository urlRepository;

    @Override
    public List<TransitionDto> getCountViewsUrls(LocalDateTime startDate, LocalDateTime endDate) {
        List<Url> urls = urlRepository.getUrlsByDeleteDateIsNotNull();
        return urls.stream()
                .map(url -> {
                    int count = transitionRepository.getTransitionByUrlAndCreateDateBetween(url, startDate, endDate).size();
                    return TransitionDto.builder()
                            .shortUrl(url.getShortUrl())
                            .countTransition(count)
                            .build();
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getReferersTop() {
        return transitionRepository.getReferersTop(Pageable.ofSize(20));
    }
}
