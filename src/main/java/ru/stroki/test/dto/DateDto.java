package ru.stroki.test.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Даты диапазона")
public class DateDto {
    @NotNull
    @NotBlank
    @Schema(description = "Начальная дата")
    private String startDate;
    @NotNull
    @NotBlank
    @Schema(description = "Конечная дата")
    private String endDate;
}
