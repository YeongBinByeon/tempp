package com.yeongbin.stock.repository;

import com.yeongbin.stock.entity.AccountTransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTransactionDetailRepository extends JpaRepository<AccountTransactionDetail, Long> {
}
