package com.example.unitech.dto.request.transfer;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransferRequest {
    private Long senderId;
    private Long receiverId;
    private BigDecimal amount;
}
