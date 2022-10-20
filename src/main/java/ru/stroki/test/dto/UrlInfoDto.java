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
    private String createDate;
    private String deleteDate;
    private Integer countOfViews;
}
