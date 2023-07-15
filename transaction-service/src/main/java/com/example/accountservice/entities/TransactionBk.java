package com.example.accountservice.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "transaction")
public class TransactionBk {
    @Id
    @Column(name = "trans_id")
    private Integer transId;

    @Column(name = "trans_date")
    private Date transDate;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "trans_type")
    private String transType;

    @Column(name = "purpose")
    private String purpose;

    @Column(name = "to_account")
    private Integer toAccount;

    @Column(name = "account_no")
    private Integer accountNo;

    @Column(name = "account_bal")
    private Integer accountBal;

}