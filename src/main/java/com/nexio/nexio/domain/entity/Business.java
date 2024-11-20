package com.nexio.nexio.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data // Genera getters, setters, toString, equals, y hashCode
@NoArgsConstructor // Genera constructor vacío
@AllArgsConstructor // Genera constructor con todos los campos
@Table(name="businesses")
public class Business {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "El nombre no puede ser nulo")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    @Column(nullable = false, length = 100)
    private String name;
    @NotNull(message = "La categoría no puede ser nula") // Valida que la categoría no sea nula
    @Size(max = 50, message = "La categoría no puede exceder 50 caracteres") // Limita el tamaño máximo a 50 caracteres
    @Column(nullable = false, length = 50)
    private String category;
    @NotNull(message = "La dirección no puede ser nula") // Valida que la dirección no sea nula
    @Size(max = 255, message = "La dirección no puede exceder 255 caracteres") // Limita el tamaño máximo a 255 caracteres
    @Column(nullable = false) // Refuerza la restricción a nivel de base de datos
    private String address;
    @NotNull(message = "La latitud no puede ser nula") // Valida que la latitud no sea nula
    @Digits(integer = 3, fraction = 6, message = "La latitud debe tener un máximo de 3 dígitos enteros y 6 decimales")
    // Valida el formato de la latitud (ejemplo: 123.456789)
    @Column(nullable = false, precision = 9, scale = 6) // Refuerza la precisión y escala a nivel de base de datos
    private BigDecimal latitude;
    @NotNull(message = "La longitud no puede ser nula") // Valida que la longitud no sea nula
    @Digits(integer = 3, fraction = 6, message = "La longitud debe tener un máximo de 3 dígitos enteros y 6 decimales")
    // Valida el formato de la longitud (ejemplo: 123.456789)
    @Column(nullable = false, precision = 9, scale = 6) // Refuerza la precisión y escala a nivel de base de datos
    private BigDecimal longitude;
}
