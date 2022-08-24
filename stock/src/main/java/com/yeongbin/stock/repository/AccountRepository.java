package com.yeongbin.stock.repository;

import com.yeongbin.stock.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, String> {
    List<Account> findAllByUserId(long id);
}
