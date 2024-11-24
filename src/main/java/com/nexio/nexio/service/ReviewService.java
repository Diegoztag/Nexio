package com.nexio.nexio.service;

import com.nexio.nexio.domain.dto.ReviewDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface ReviewService {
    ReviewDto createReview(ReviewDto reviewDto);
    ReviewDto updateReview(ReviewDto reviewDto);
    void deleteReview(Long reviewId);
    ReviewDto getReviewById(Long reviewId);
    List<ReviewDto> getReviewsByBusinessId(Long businessId, String filter);
    List<ReviewDto> getReviewsByUserId(Long userId, String filter);
}
