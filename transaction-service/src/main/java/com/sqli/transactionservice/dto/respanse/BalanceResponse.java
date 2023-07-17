package com.sqli.transactionservice.dto.respanse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BalanceResponse {
    private Integer accountNo;

    private String accountType;

    private Double balance;
}
