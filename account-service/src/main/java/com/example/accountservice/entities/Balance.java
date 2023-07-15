package com.example.accountservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "balance")
public class Balance {
    @Id
    @Column(name = "account_no")
    private Integer accountNo;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "balance")
    private Integer balance;

}