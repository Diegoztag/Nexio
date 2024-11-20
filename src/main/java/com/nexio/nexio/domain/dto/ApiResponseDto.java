package com.nexio.nexio.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@ToString
public class ApiResponseDto<T> {
    private String message; // Mensaje general de la respuesta
    private T data; // Datos devueltos
    private String path; // Endpoint consumido
    private Long codeHttp; // Código HTTP
    private String descriptionHttp; // Descripción del código HTTP
    private List<String> errors; // Lista de errores, si aplica
    private LocalDateTime timestamp; // Fecha y hora de la respuesta
}
