package com.nexio.nexio.controller;

import com.nexio.nexio.domain.dto.ApiResponseDto;
import com.nexio.nexio.domain.dto.ReviewDto;
import com.nexio.nexio.service.ReviewService;
import com.nexio.nexio.utils.ApiResponseHelper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // Crear una reseña
    @PostMapping
    public ResponseEntity<ApiResponseDto<ReviewDto>> createReview(@Valid @RequestBody ReviewDto reviewDto, HttpServletRequest request) {
        ReviewDto createdReview = reviewService.createReview(reviewDto);
        return ApiResponseHelper.success(createdReview, "Reseña creada exitosamente", request.getRequestURI());
    }

    // Obtener reseña por ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<ReviewDto>> fetReviewById(@PathVariable Long id, HttpServletRequest request) {
        ReviewDto review = reviewService.getReviewById(id);
        return ApiResponseHelper.success(review, "Reseña encontrada exitosamente", request.getRequestURI());
    }

    // Actualizar una reseña
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<ReviewDto>> updateReview(@PathVariable Long id, @Valid @RequestBody ReviewDto reviewDto, HttpServletRequest request) {
        reviewDto.setId(id);
        ReviewDto updatedReview = reviewService.updateReview(reviewDto);
        return ApiResponseHelper.success(updatedReview, "Reseña actualizada correctamente", request.getRequestURI());
    }

    // Eliminar una reseña
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Void>> deleteReview(@PathVariable Long id, HttpServletRequest request) {
        reviewService.deleteReview(id);
        return ApiResponseHelper.success(null, "Reseña eliminada exitosamente", request.getRequestURI());
    }

    // Obtener todas las reseñas de un negocio
    @GetMapping("/business/{businessId}")
    public ResponseEntity<ApiResponseDto<List<ReviewDto>>> getReviewsByBusinessId(@PathVariable Long businessId, HttpServletRequest request) {
        List<ReviewDto> reviews = reviewService.getReviewsByBusinessId(businessId);
        String message = reviews.isEmpty() ? "El negocio aún no tiene ninguna reseña" : "Reseñas encontradas exitosamente";

        return ApiResponseHelper.success(reviews, message, request.getRequestURI());
    }

    // Obtener todas las reseñas de un usuario
    @GetMapping("users/{userId}")
    public ResponseEntity<ApiResponseDto<List<ReviewDto>>> getReviewsByUserId(@PathVariable Long userId, HttpServletRequest request) {
        List<ReviewDto> reviews = reviewService.getReviewsByUserId(userId);
        String message = reviews.isEmpty() ? "El usuario aún no ha creado ninguna reseña" : "Reseñas encontradas exitosamente";

        return ApiResponseHelper.success(reviews, message, request.getRequestURI());
    }





    //Todo:
    // crear el DTO de user
    // Añadir validaciones al DTO user
    // Crear el service de users
    // Añadir filtros a los reviews por antigüedad, nuevos, mis reviews, etc.

}
