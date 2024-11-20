package com.nexio.nexio.utils;

import com.nexio.nexio.domain.dto.ApiResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

public class ApiResponseHelper {
    public static <T> ResponseEntity <ApiResponseDto<T>> success(T data, String message, String path) {
        return ResponseEntity.ok(
                ApiResponseDto.<T>builder()
                        .message(message)
                        .data(data)
                        .codeHttp((long)HttpStatus.OK.value())
                        .descriptionHttp(HttpStatus.OK.getReasonPhrase())
                        .path(path)
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    public static <T> ResponseEntity <ApiResponseDto<T>> notFound(String message, List<String> errors, String path) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                        ApiResponseDto.<T>builder()
                                .message(message)
                                .codeHttp((long)HttpStatus.NOT_FOUND.value())
                                .descriptionHttp(HttpStatus.NOT_FOUND.getReasonPhrase())
                                .errors(errors)
                                .path(path)
                                .timestamp(LocalDateTime.now())
                                .build());
    }

    public static <T> ResponseEntity <ApiResponseDto<T>> error(String message, List<String> errors, String path) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ApiResponseDto.<T>builder()
                                .message(message)
                                .codeHttp((long)HttpStatus.INTERNAL_SERVER_ERROR.value())
                                .descriptionHttp(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                                .errors(errors)
                                .path(path)
                                .timestamp(LocalDateTime.now())
                                .build());
    }

    public static ResponseEntity <ApiResponseDto<Void>> duplicate(String message, List<String> errors, String path) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(
                        ApiResponseDto.<Void>builder()
                                .message(message)
                                .codeHttp((long)HttpStatus.CONFLICT.value())
                                .descriptionHttp(HttpStatus.CONFLICT.getReasonPhrase())
                                .errors(errors)
                                .path(path)
                                .timestamp(LocalDateTime.now())
                                .build());
    }
}
