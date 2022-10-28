package ru.stroki.test.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.stroki.test.dto.DateDto;
import ru.stroki.test.dto.StatisticsDto;
import ru.stroki.test.entity.User;
import ru.stroki.test.services.impl.TransitionServiceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/urlShorter/statistics")
@Tag(
        name = "Статистика",
        description = "Методы для ведения статистики"
)
public class StatisticsController {
    private final TransitionService transitionService;

    @GetMapping("/countOfViews")
    @Operation(summary = "Получение информации о количестве переходов для ссылок текущего пользователя в заданном временном диапазоне")
    public ResponseEntity<List<StatisticsDto>> getCountViewsUrls(@RequestAttribute("user") User user, @RequestBody DateDto dateDto){
        return ResponseEntity.ok(transitionService.getCountViewsUrls(user, dateDto));
    }

    @GetMapping("/top")
    @Operation(summary = "Получение топа из 20 сайтов источников переходов")
    public ResponseEntity<List<String>> getReferersTop(@RequestAttribute("user") User user){
        return ResponseEntity.ok(transitionService.getReferersTop(user));
    }
}
