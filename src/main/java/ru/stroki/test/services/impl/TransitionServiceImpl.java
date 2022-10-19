package ru.stroki.test.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.stroki.test.dto.StatisticsDto;
import ru.stroki.test.entity.Transition;
import ru.stroki.test.entity.Url;
import ru.stroki.test.entity.User;
import ru.stroki.test.repository.TransitionRepository;
import ru.stroki.test.repository.UrlRepository;
import ru.stroki.test.services.TransitionService;
import ru.stroki.test.mapper.DtoMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TransitionServiceImpl implements TransitionService {

    private final TransitionRepository transitionRepository;

    private final UrlRepository urlRepository;

    private final DtoMapper dtoMapper;

    @Override
    public List<StatisticsDto> getCountViewsUrls(User user, LocalDateTime startDate, LocalDateTime endDate) {
        //todo проверка на корректность передаваемых дат
        //не понятно, что вернет следующий метод при отсутствии
        //данных, если null, то требуется вернуть пустой список

        List<Url> urls = urlRepository.getUrlsByDeleteDateIsNullAndUser(user);
        return urls.stream()
                .map(url -> {
                    int count = transitionRepository.getTransitionByUrlAndCreateDateBetween(url, startDate, endDate).size();
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
    public String getRedirect(String shortUrl, String referer){
        return urlRepository.findByShortUrl(shortUrl)
                .map(url -> {
                    Transition transition = Transition.builder()
                            .url(url)
                            .referer(referer)
                            .build();
                    transitionRepository.save(transition);
                    return url.getLongUrl();
                })
                .orElse(null);
    }
}
