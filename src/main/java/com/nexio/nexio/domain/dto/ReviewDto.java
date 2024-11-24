package com.nexio.nexio.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReviewDto {
    private Long id;

    @NotNull(message = "El ID del negocio no puede ser nulo")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long businessId;

    @NotNull(message = "El ID del usuario no puede ser nulo")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long userId;

    @NotNull(message = "La calificación es obligatoria")
    @Min(value = 1, message = "La calificación mínima es 1")
    @Max(value = 5, message = "La calificación máxima es 5")
    private Integer rating;

    @NotNull(message = "El comentario no puede ser nulo")
    @NotBlank(message = "El comentario es obligatorio")
    @Size(min = 5, max = 500, message = "El comentario debe tener entre 5 y 500 caracteres")
    private String comment;

    @NotNull(message = "El nombre del negocio no puede ser nulo")
    @NotBlank(message = "El nombre del negocio es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre del negocio debe tener entre 2 y 100 caracteres")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String businessName;

    @NotNull(message = "El nombre del usuario no puede ser nulo")
    @NotBlank(message = "El nombre del usuario es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre del usuario debe tener entre 2 y 50 caracteres")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String userName;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;
}
