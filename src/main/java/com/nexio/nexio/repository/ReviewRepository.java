package com.nexio.nexio.repository;

import com.nexio.nexio.domain.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByBusinessId(Long businessId);

    List<Review> findByUserId(Long userId);

    Optional<Review> findById(Long id);
}
