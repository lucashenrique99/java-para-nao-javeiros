package com.everis.helloworld.viewmodel.mapper;

import com.everis.helloworld.dto.DetalhesClienteDTO;
import com.everis.helloworld.viewmodel.ContaViewModel;
import com.everis.helloworld.viewmodel.DetalhesClienteViewModel;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.stream.Collectors;

public class DetalhesClienteViewModelMapper {

    public static DetalhesClienteViewModel from(DetalhesClienteDTO dto) {
        return DetalhesClienteViewModel.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .cargo(dto.getCargo())
                .contas(dto.getContas().stream()
                        .map(conta -> ContaViewModel.builder()
                                .numeroConta(conta.getAgencia()
                                        .concat(" ")
                                        .concat(conta.getConta())
                                        .concat("-")
                                        .concat(conta.getDac()))
                                .saldo(formatarSaldo(conta.getSaldo()))
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

    private static String formatarSaldo(BigDecimal saldo) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return formatter.format(saldo);
    }


}
