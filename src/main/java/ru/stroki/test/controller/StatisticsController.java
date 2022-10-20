package ru.stroki.test.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.stroki.test.dto.StatisticsDto;
import ru.stroki.test.entity.User;
import ru.stroki.test.services.impl.TransitionServiceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/urlShorter/statistics")
public class StatisticsController {
    private final TransitionServiceImpl transitionService;

    @GetMapping("/countOfViews")
    public ResponseEntity<List<StatisticsDto>> getCountViewsUrls(@RequestAttribute("user") User user, @RequestBody Map<String, String> params){
        return ResponseEntity.ok(transitionService.getCountViewsUrls(user, params.get("startDate"), params.get("endDate")));
    }

    @GetMapping("/top")
    public ResponseEntity<List<String>> getReferersTop(@RequestAttribute("user") User user){
        return ResponseEntity.ok(transitionService.getReferersTop(user));
    }
}
