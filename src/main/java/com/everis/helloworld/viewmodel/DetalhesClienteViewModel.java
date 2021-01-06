package com.everis.helloworld.viewmodel;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DetalhesClienteViewModel {

    private String id;
    private String nome;
    private String cargo;
    private Boolean ativo;
    private List<ContaViewModel> contas;

}
