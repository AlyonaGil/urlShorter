package ru.stroki.test.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.stroki.test.entity.Url;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UrlInfoDto {
    private String longUrl;
    private String shortUrl;
    private UserDto user;
    private LocalDateTime createDate;
    private String status;
    private Integer countOfViews;

    public UrlInfoDto(Url url){
        this.setLongUrl(url.getLongUrl());
        this.setShortUrl(url.getShortUrl());
        this.setCreateDate(url.getCreateDate());
        this.setUser(new UserDto(url.getUser()));
        this.setStatus(url.getDeleteDate() == null ? "Архивная ссылка" : "Действующая ссылка");
        this.setCountOfViews(url.getTransitions().size());
    }
}
