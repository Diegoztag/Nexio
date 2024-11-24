package com.nexio.nexio.service;

import com.nexio.nexio.domain.dto.ReviewDto;
import com.nexio.nexio.domain.entity.Business;
import com.nexio.nexio.domain.entity.Review;
import com.nexio.nexio.domain.entity.User;
import com.nexio.nexio.exceptions.ResourceNotFoundException;
import com.nexio.nexio.repository.BusinessRepository;
import com.nexio.nexio.repository.ReviewRepository;
import com.nexio.nexio.repository.UserRepository;
import com.nexio.nexio.utils.ApiResponseHelper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final BusinessRepository businessRepository;
    private final UserRepository userRepository;

    public ReviewServiceImpl(
            ReviewRepository reviewRepository,
            BusinessRepository businessRepository,
            UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.businessRepository = businessRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ReviewDto createReview(ReviewDto reviewDto) {
        reviewDto.setCreatedAt(LocalDateTime.now());
        Review review = mapToEntity(reviewDto);
        Review savedReview = reviewRepository.save(review);
        return mapToDto(savedReview);
    }

    @Override
    public ReviewDto updateReview(ReviewDto reviewDto) {
        Review existingReview = reviewRepository.findById(reviewDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Reseña no encontrada"));

        existingReview.setRating(reviewDto.getRating());
        existingReview.setComment(reviewDto.getComment());

        Review updatedReview = reviewRepository.save(existingReview);
        return mapToDto(updatedReview);
    }

    @Override
    public void deleteReview(Long reviewId) {
        Review existindReview = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Reseña no encontrada"));

        reviewRepository.deleteById(existindReview.getId());
    }

    @Override
    public ReviewDto getReviewById(Long reviewId) {
        Review existingReview = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Reseña no encontrada"));
        return mapToDto(existingReview);
    }

    @Override
    public List<ReviewDto> getReviewsByBusinessId(Long businessId, String filter) {
        List<Review> reviews = null;

        //filtros
        switch (filter) {
            case "newest":
                reviews = reviewRepository.findByBusinessIdOrderByCreatedAtDesc(businessId);
                break;
            case "oldest":
                reviews = reviewRepository.findByBusinessIdOrderByCreatedAtAsc(businessId);
                break;
            default:
                reviews = reviewRepository.findByBusinessId(businessId);
                break;
        }

        return reviews.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<ReviewDto> getReviewsByUserId(Long userId, String filter) {
        List<Review> reviews = null;

        // filtros
        switch(filter) {
            case "newest":
                reviews = reviewRepository.findByUserIdOrderByCreatedAtDesc(userId);
                break;
            case "oldest":
                reviews = reviewRepository.findByUserIdOrderByCreatedAtAsc(userId);
                break;
            default:
                reviews = reviewRepository.findByUserId(userId);
        }

        return reviews.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    //Métodos auxiliares
    private Review mapToEntity(ReviewDto reviewDto) {
        Business business = businessRepository.findById(reviewDto.getBusinessId())
                .orElseThrow(() -> new ResourceNotFoundException("Negocio no encontrado"));
        User user = userRepository.findById(reviewDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        return Review.builder()
                .business(business)
                .user(user)
                .rating(reviewDto.getRating())
                .comment(reviewDto.getComment())
                .createdAt(reviewDto.getCreatedAt())
                .build();
    }

    private ReviewDto mapToDto(Review review) {
        return ReviewDto.builder()
                .id(review.getId())
                .businessId(review.getBusiness().getId())
                .userId(review.getUser().getId())
                .businessName(review.getBusiness().getName())
                .userName(review.getUser().getUsername())
                .rating(review.getRating())
                .comment(review.getComment())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
