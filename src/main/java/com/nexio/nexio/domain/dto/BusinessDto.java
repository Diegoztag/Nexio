package com.nexio.nexio.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessDto {
    private Long id;

    @NotNull(message = "El nombre no puede ser nulo")
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    private String name;

    @NotNull(message = "La categoría no puede ser nula")
    @NotBlank(message = "La categoría es obligatoria")
    @Size(max = 50, message = "La categoría no puede exceder 50 caracteres")
    private String category;

    @NotNull(message = "La dirección no puede ser nula")
    @NotBlank(message = "La dirección es obligatoria")
    @Size(max = 255, message = "La dirección no puede exceder 255 caracteres")
    private String address;

    @NotNull(message = "La latitud no puede ser nula")
    @NotBlank(message = "La latitud es obligatoria")
    private BigDecimal latitude;

    @NotNull(message = "La longitud no puede ser nula")
    @NotBlank(message = "La longitud es obligatoria")
    private BigDecimal longitude;

}
