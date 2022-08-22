package com.yeongbin.stock.repository;

import com.yeongbin.stock.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
