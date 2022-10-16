package ru.stroki.test.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransitionDto {
    private String shortUrl;
    private Integer countTransition;
}
