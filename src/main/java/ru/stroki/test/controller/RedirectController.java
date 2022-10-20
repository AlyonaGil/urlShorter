package ru.stroki.test.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.stroki.test.services.impl.TransitionServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;


@RestController
@AllArgsConstructor
@Tag(
        name = "Редирект",
        description = "Методы для перехода по ссылкам"
)
public class RedirectController {
    private final TransitionServiceImpl transitionService;

    @GetMapping("/urlShorter/redirect/{shortUrl}")
    @Operation(summary = "Редирект на полный url по короткой ссылке")
    public ResponseEntity<?> redirect(HttpServletRequest request, @PathVariable @Parameter(description = "Короткий сгенерированный url") String shortUrl){
        String referer = request.getHeader(HttpHeaders.REFERER);
        if (referer == null) referer = "unknown";
        String str = transitionService.getRedirect(shortUrl, referer);
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(str)).build();
    }
}
