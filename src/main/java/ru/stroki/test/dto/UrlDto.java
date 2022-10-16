package ru.stroki.test.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.stroki.test.entity.Url;

import java.time.LocalDateTime;
import java.util.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UrlDto {
    private Integer id;
    private String longUrl;
    private String shortUrl;
    private LocalDateTime createDate;

    public UrlDto(Url url){
        this.setId(url.getId());
        this.setLongUrl(url.getLongUrl());
        this.setShortUrl(url.getShortUrl());
        this.setCreateDate(url.getCreateDate());
    }
}
