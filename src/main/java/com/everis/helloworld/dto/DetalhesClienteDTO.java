package com.everis.helloworld.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetalhesClienteDTO {

    private String id;
    private String nome;
    private String cargo;
    private List<ContaDTO> contas;

}
