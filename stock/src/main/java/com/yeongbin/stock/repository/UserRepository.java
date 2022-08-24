package com.yeongbin.stock.repository;

import com.yeongbin.stock.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User as u where u.age >= :min and u.age <= :max")
    List<User> findByRangeAge(@Param("min") int min,@Param("max") int max);
}
