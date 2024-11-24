package com.nexio.nexio.domain.dto;

import jakarta.validation.constraints.Digits;
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
    @NotNull(message = "El ID no puede ser nulo")
    @NotBlank(message = "El ID es obligatorio")
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
    @Digits(integer = 3, fraction = 6, message = "La latitud debe tener un máximo de 3 dígitos enteros y 6 decimales")
    // Válida el formato de la latitud (ejemplo: 123.456789)
    private BigDecimal latitude;

    @NotNull(message = "La longitud no puede ser nula")
    @NotBlank(message = "La longitud es obligatoria")
    @Digits(integer = 3, fraction = 6, message = "La longitud debe tener un máximo de 3 dígitos enteros y 6 decimales")
    // Válida el formato de la longitud (ejemplo: 123.456789)
    private BigDecimal longitude;

}
