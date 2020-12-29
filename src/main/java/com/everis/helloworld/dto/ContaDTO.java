package com.everis.helloworld.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContaDTO {

    private String id;
    @JsonProperty("clienteId")
    private String cliente;
    private String criadoEm;
    private String agencia;
    private String conta;
    private String dac;

    private BigDecimal saldo;

}
