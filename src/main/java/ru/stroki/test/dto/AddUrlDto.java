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
@Schema(description = "Информация о передаваемом url")
public class AddUrlDto {
    @NotNull
    @NotBlank
    @Schema(description = "Исходный url")
    private String longUrl;
}
