package com.nexio.nexio.repository;

import com.nexio.nexio.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
