package com.yeongbin.stock.repository;

import com.yeongbin.stock.entity.AccountTransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AccountTransactionDetailRepository extends JpaRepository<AccountTransactionDetail, Long> {
    @Query("select ad from AccountTransactionDetail as ad " +
            "where YEAR(ad.depositDate) = :year")
    List<AccountTransactionDetail> findByYear(@Param("year")int year);

    @Query("select ad from AccountTransactionDetail as ad " +
            "where ad.depositDate >= :start and ad.depositDate <= :end")
    List<AccountTransactionDetail> findByDateDuringRange(@Param("start")LocalDate start, @Param("end") LocalDate end);
}
