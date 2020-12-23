package com.everis.helloworld.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteDTO {

    private String id;
    private String nome;
    private String cargo;

}
