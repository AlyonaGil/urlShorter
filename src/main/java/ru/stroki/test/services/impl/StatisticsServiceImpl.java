package ru.stroki.test.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.stroki.test.dto.StatisticsDto;
import ru.stroki.test.entity.Url;
import ru.stroki.test.repository.TransitionRepository;
import ru.stroki.test.repository.UrlRepository;
import ru.stroki.test.services.StatisticsService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private TransitionRepository transitionRepository;

    @Autowired
    private UrlRepository urlRepository;

    @Override
    public List<StatisticsDto> getCountViewsUrls(LocalDateTime startDate, LocalDateTime endDate) {
        //todo проверка на корректность передаваемых дат
        //не понятно, что вернет следующий метод при отсутствии
        //данных, если null, то требуется вернуть пустой список
        List<Url> urls = urlRepository.getUrlsByDeleteDateIsNull();
        return urls.stream()
                .map(url -> {
                    int count = transitionRepository.getTransitionByUrlAndCreateDateBetween(url, startDate, endDate).size();
                    return StatisticsDto.builder()
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
