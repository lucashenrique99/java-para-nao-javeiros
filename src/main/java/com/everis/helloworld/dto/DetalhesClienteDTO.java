package com.everis.helloworld.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalhesClienteDTO {

    private String id;
    private String nome;
    private String cargo;
    private Boolean ativo;
    private List<ContaDTO> contas;

}
