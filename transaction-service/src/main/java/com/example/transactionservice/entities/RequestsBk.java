package com.example.transactionservice.entities;

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
@Table(name = "requests")
public class RequestsBk {
    @Id
    @Column(name = "request_id")
    private Integer requestId;

    @Column(name = "account_no")
    private Integer accountNo;

    @Column(name = "to_account")
    private Integer toAccount;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "message")
    private String message;

    @Column(name = "hasViewed")
    private Boolean hasViewed;

    @Column(name = "status")
    private String status;

    @Column(name = "request_date")
    private Date requestDate;

}
