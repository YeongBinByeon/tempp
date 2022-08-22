package com.yeongbin.stock.repository;

import com.yeongbin.stock.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}
