package ru.stroki.test.dto;

import lombok.*;
import ru.stroki.test.entity.Url;

import java.time.LocalDateTime;
import java.util.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UrlDto {
    private Integer id;
    private String longUrl;
    private String shortUrl;
    private String createDate;
}
