package com.everis.helloworld.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
