package com.everis.helloworld.viewmodel;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteViewModel {

    private String id;
    private String nome;
    private String cargo;

}
