package ru.stroki.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.stroki.test.dto.StatisticsDto;
import ru.stroki.test.services.impl.StatisticsServiceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/urlShorter/statistics")
public class StatisticsController {
    @Autowired
    private StatisticsServiceImpl transitionService;

    @GetMapping("/countOfViews")
    public ResponseEntity<List<StatisticsDto>> getCountViewsUrls(@RequestBody Map<String, LocalDateTime> params){
        return ResponseEntity.ok(transitionService.getCountViewsUrls(params.get("startDate"), params.get("endDate")));
    }

    @GetMapping("/top")
    public ResponseEntity<List<String>> getReferersTop(){
        return ResponseEntity.ok(transitionService.getReferersTop());
    }
}
