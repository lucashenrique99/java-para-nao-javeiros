package com.everis.helloworld.helper;

import com.everis.helloworld.dto.*;
import com.everis.helloworld.exception.ErroApiException;
import com.everis.helloworld.service.ClientesService;
import com.everis.helloworld.service.ContasService;
import com.everis.helloworld.viewmodel.ClienteViewModel;
import com.everis.helloworld.viewmodel.DetalhesClienteViewModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

public class ClientesHelperTests {

    @InjectMocks
    private ClientesHelper helper;

    @Mock
    private ClientesService clientesService;

    @Mock
    private ContasService contasService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void findAllClientesTests() throws Exception {
        Mockito.when(clientesService.findAll()).thenReturn(getClientes());

        List<ClienteViewModel> clientes = helper.findAllClientes();

        Assert.assertNotNull(clientes);
        Assert.assertEquals(clientes.size(), 1);
        Assert.assertEquals(clientes.get(0).getNome(), "Cliente");
    }

    @Test(expected = ErroApiException.class)
    public void findAllClientesComErroTests() throws Exception {
        String mensagemErro = "Erro durante a chamada API";
        Mockito.when(clientesService.findAll()).thenThrow(new ErroApiException(mensagemErro));

        helper.findAllClientes();
    }

    @Test
    public void findAllClientesComFiltrosTests() throws Exception {
        Mockito.when(clientesService.findAll(any(), any(), any())).thenReturn(getClientes());

        List<ClienteViewModel> clientes = helper.findAllClientes("Nome", 1, 10);

        Assert.assertNotNull(clientes);
        Assert.assertEquals(clientes.size(), 1);
        Assert.assertEquals(clientes.get(0).getNome(), "Cliente");
    }

    @Test
    public void inserirTests() throws Exception {
        Mockito.when(clientesService.inserir(any())).thenReturn(getCliente());
        Mockito.when(contasService.inserir(any())).thenReturn(getContaDTO());

        ClienteViewModel cliente = helper.inserir(getNovoClienteRequest());

        Assert.assertNotNull(cliente);
        Assert.assertEquals(cliente.getNome(), "Cliente");
    }

    @Test
    public void findByIdTests() throws Exception {
        Mockito.when(clientesService.findById(any())).thenReturn(getCliente());

        DetalhesClienteViewModel cliente = helper.findById("1");

        Assert.assertNotNull(cliente);
        Assert.assertEquals(cliente.getNome(), "Cliente");
        Assert.assertEquals(cliente.getContas().size(), 1);
    }

    @Test
    public void editarTests() throws Exception {
        Mockito.when(clientesService.editar(any())).thenReturn(getCliente());

        ClienteViewModel cliente = helper.editar(getEditarClienteRequest());

        Assert.assertNotNull(cliente);
        Assert.assertEquals(cliente.getNome(), "Cliente");
    }

    @Test
    public void excluirPeloIdTests() throws Exception {
        Mockito.doNothing().when(clientesService).excluirPeloId(any());
        helper.excluirPeloId("1");
    }

    @Test(expected = ErroApiException.class)
    public void excluirPeloIdComErroTests() throws Exception {
        String mensagemErro = "Não foi possível excluir este cliente";
        Mockito.doThrow(new ErroApiException(mensagemErro)).when(clientesService).excluirPeloId(any());
        helper.excluirPeloId("1");
    }

    private List<ClienteDTO> getClientes() {
        List<ClienteDTO> clientes = new ArrayList<>();
        clientes.add(ClienteDTO.builder()
                .id("1")
                .nome("Cliente")
                .cargo("Cargo")
                .ativo(Boolean.TRUE)
                .build());
        return clientes;
    }

    private DetalhesClienteDTO getCliente() {
        return DetalhesClienteDTO.builder()
                .id("1")
                .nome("Cliente")
                .cargo("Cargo")
                .ativo(Boolean.TRUE)
                .contas(Collections.singletonList(getContaDTO()))
                .build();
    }

    private ContaDTO getContaDTO() {
        return ContaDTO.builder()
                .id("1")
                .criadoEm(LocalDateTime.now().toString())
                .agencia("0018")
                .cliente("1")
                .conta("1234567")
                .dac("1")
                .saldo(BigDecimal.TEN)
                .build();
    }

    private EditarClienteRequestDTO getEditarClienteRequest() {
        EditarClienteRequestDTO request = new EditarClienteRequestDTO();
        request.setId("1");
        request.setNome("Cliente");
        request.setCargo("Cargo");
        request.setAtivo(Boolean.FALSE);

        return request;
    }

    private NovoClienteRequestDTO getNovoClienteRequest() {
        NovoClienteRequestDTO request = new NovoClienteRequestDTO();
        request.setNome("Cliente");
        request.setCargo("Cargo");

        return request;
    }
}
