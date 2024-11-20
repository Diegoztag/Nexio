package com.nexio.nexio.repository;

import com.nexio.nexio.domain.entity.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Long> {
    Optional<Business> findByName(String name);
}
