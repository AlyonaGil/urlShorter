package ru.stroki.test.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatisticsDto {
    private String shortUrl;
    private Integer countTransition;
}
