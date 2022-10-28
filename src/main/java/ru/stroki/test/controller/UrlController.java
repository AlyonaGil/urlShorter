package ru.stroki.test.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.stroki.test.dto.AddUrlDto;
import ru.stroki.test.dto.UrlDto;
import ru.stroki.test.dto.UrlInfoDto;
import ru.stroki.test.entity.User;
import ru.stroki.test.services.impl.UrlServiceImpl;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/urlShorter")
@Tag(
        name = "Ссылки",
        description = "Методы для работы с ссылками"
)
public class UrlController {
    private final UrlService urlService;

    @PostMapping("/url")
    @Operation(summary = "Создание новой короткой ссылки")
    public ResponseEntity<UrlDto> addUrl(@RequestAttribute("user") User user, @RequestBody AddUrlDto addUrlDto){
        return ResponseEntity.ok(urlService.addUrl(addUrlDto, user));
    }

    @GetMapping("/urls")
    @Operation(summary = "Получение краткой информации о всех ссылках текущего пользователя")
    public ResponseEntity<List<UrlDto>> getAllUrls(@RequestAttribute("user") User user){
        return ResponseEntity.ok(urlService.getAllUrls(user));
    }

    @GetMapping("/url/{id}")
    @Operation(summary = "Получение полной информации о ссылке")
    public ResponseEntity<UrlInfoDto> getInfoUrl(@RequestAttribute("user") User user, @PathVariable @Parameter(description = "Идентификатор ссылки") Integer id){
        return ResponseEntity.ok(urlService.getById(id, user));
    }

    @DeleteMapping("/url/{id}")
    @Operation(summary = "Удаление ссылки")
    public ResponseEntity<?> deleteUrl(@RequestAttribute("user") User user, @PathVariable @Parameter(description = "Идентификатор ссылки") Integer id){
        urlService.deleteUrl(id, user);
        return ResponseEntity.noContent().build();
    }
}
