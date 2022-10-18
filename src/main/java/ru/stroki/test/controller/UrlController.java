package ru.stroki.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.stroki.test.dto.UrlDto;
import ru.stroki.test.dto.UrlInfoDto;
import ru.stroki.test.entity.User;
import ru.stroki.test.services.impl.UrlServiceImpl;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/urlShorter")
public class UrlController {
    @Autowired
    private UrlServiceImpl urlService;


    @PostMapping("/url")
    public ResponseEntity<UrlDto> addUrl(@RequestAttribute("user") User user, @RequestBody Map<String, String> params){
        return ResponseEntity.ok(urlService.addUrl(params.get("longUrl"), user));
    }

    @GetMapping("/urls")
    public ResponseEntity<List<UrlDto>> getAllUrls(@RequestAttribute("user") User user){
        return ResponseEntity.ok(urlService.getAllUrls(user));
    }

    @GetMapping("/url/{id}")
    public ResponseEntity<UrlInfoDto> getInfoUrl(@RequestAttribute("user") User user, @PathVariable Integer id){
        return ResponseEntity.ok(urlService.getById(id, user));
    }

    @DeleteMapping("/url/{id}")
    public ResponseEntity<?> deleteUrl(@RequestAttribute("user") User user, @PathVariable Integer id){
        urlService.deleteUrl(id, user);
        return ResponseEntity.noContent().build();
    }
}
