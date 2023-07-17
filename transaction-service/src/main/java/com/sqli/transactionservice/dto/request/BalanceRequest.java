package com.sqli.transactionservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BalanceRequest {

    private Integer accountNo;

    private String accountType;

    private Double balance;
}
