package ru.stroki.test.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import ru.stroki.test.entity.Url;

import java.time.LocalDateTime;
import java.util.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Краткая информация об Url")
public class UrlDto {
    @Schema(description = "Идентификатор")
    private Integer id;
    @Schema(description = "Исходный url")
    private String longUrl;
    @Schema(description = "Короткий url")
    private String shortUrl;
    @Schema(description = "Дата создания")
    private String createDate;
}
