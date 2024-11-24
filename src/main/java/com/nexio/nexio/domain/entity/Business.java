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

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 50)
    private String category;

    @Column(nullable = false) // Refuerza la restricción en base de datos
    private String address;

    @Column(nullable = false, precision = 9, scale = 6) // Refuerza la precisión y escala en base de datos
    private BigDecimal latitude;

    @Column(nullable = false, precision = 9, scale = 6) // Refuerza la precisión y escala en base de datos
    private BigDecimal longitude;
}
