package com.nexio.nexio.exceptions;

import com.nexio.nexio.domain.dto.ApiResponseDto;
import com.nexio.nexio.utils.ApiResponseHelper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDto<Object>> handleGeneralException(Exception ex, HttpServletRequest request) {
        return ApiResponseHelper.error(
                "Error interno del servidor",
                List.of(ex.getMessage()),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponseDto<Object>> handleIllegalArgumentException(IllegalArgumentException ex, HttpServletRequest request) {
        return ApiResponseHelper.error(
                "Solicitud inválida",
                List.of(ex.getMessage()),
                request.getRequestURI());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponseDto<Void>> handleResourceNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
        return ApiResponseHelper.notFound(
                ex.getMessage(),
                List.of(ex.getMessage()),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiResponseDto<Void>> handleDuplicateResource(DuplicateResourceException ex, HttpServletRequest request) {
        return ApiResponseHelper.duplicate(
                ex.getMessage(),
                List.of(ex.getMessage()),
                request.getRequestURI()
        );
    }
}
