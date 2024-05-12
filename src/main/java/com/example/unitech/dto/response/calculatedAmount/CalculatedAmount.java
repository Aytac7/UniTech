package com.example.unitech.dto.response.calculatedAmount;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CalculatedAmount {
    Long id;
    String from;
    String to;
    BigDecimal multiplicationFactor;
    BigDecimal quantity;
    BigDecimal totalCalculatedAmount;
    int port;
}
