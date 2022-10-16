package ru.stroki.test.dto;

import lombok.*;
import ru.stroki.test.entity.Url;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UrlInfoDto {
    private String longUrl;
    private String shortUrl;
    private UserDto user;
    private LocalDateTime createDate;
    private LocalDateTime deleteDate;
    private Integer countOfViews;
}
