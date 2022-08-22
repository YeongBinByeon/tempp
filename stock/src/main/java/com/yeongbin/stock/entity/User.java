package com.yeongbin.stock.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//@EntityListeners(value = {AuditingEntityListener.class})
@Entity
@Table(name = "user")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

//    @CreatedDate
    @Column(name="registered_date", updatable = false)
    private LocalDate registeredDate;

    @OneToMany(mappedBy = "user")
    private List<Account> accountList = new ArrayList<>();

}
