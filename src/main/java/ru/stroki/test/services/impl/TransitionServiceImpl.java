package ru.stroki.test.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.stroki.test.dto.DateDto;
import ru.stroki.test.dto.StatisticsDto;
import ru.stroki.test.entity.Transition;
import ru.stroki.test.entity.Url;
import ru.stroki.test.entity.User;
import ru.stroki.test.repository.TransitionRepository;
import ru.stroki.test.repository.UrlRepository;
import ru.stroki.test.services.TransitionService;
import ru.stroki.test.mapper.DtoMapper;
import ru.stroki.test.validation.ValidationException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class TransitionServiceImpl implements TransitionService {

    private final TransitionRepository transitionRepository;

    private final UrlRepository urlRepository;

    private final DtoMapper dtoMapper;

    @Override
    public List<StatisticsDto> getCountViewsUrls(User user, DateDto dateDto) {
        LocalDateTime start;
        LocalDateTime end;
        try {
            start = LocalDateTime.parse(dateDto.getStartDate());
            end = LocalDateTime.parse(dateDto.getEndDate());
        } catch (DateTimeParseException e) {
            throw new ValidationException("Неправильный формат даты (yyyy-MM-dd'T'HH:mm:ss)");
        }

        //List<Url> urls = urlRepository.getUrlsByUser(user);
        List<Url> urls = urlRepository.getUrlsByUserAndCreateDateAfter(user, start);
        return urls.stream()
                .map(url -> {
                    int count = transitionRepository.getTransitionByUrlAndCreateDateBetween(url, start, end).size();
                    return dtoMapper.getStatisticsDto(url.getShortUrl(), count);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getReferersTop(User user) {
        Integer userId = user.getId();
        return transitionRepository.getReferersTop(userId);
    }

    @Override
    public String getRedirect(String shortUrl, String referer) {
        return urlRepository.findByShortUrl(shortUrl)
                .map(url -> {
                    Transition transition = Transition.builder()
                            .url(url)
                            .referer(referer)
                            .build();
                    transitionRepository.save(transition);
                    return url.getLongUrl();
                })
                .orElseThrow(() -> new ValidationException("Данный url не найден"));
    }
}
