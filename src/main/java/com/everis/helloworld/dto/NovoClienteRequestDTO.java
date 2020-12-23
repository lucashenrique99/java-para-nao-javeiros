package com.everis.helloworld.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NovoClienteRequestDTO {

    private String nome;
    private String cargo;

}
