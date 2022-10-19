package ru.stroki.test.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExceptionDto {
    private Integer status;
    private String message;
}
