package com.everis.helloworld.controller;

import com.everis.helloworld.dto.EditarClienteRequestDTO;
import com.everis.helloworld.dto.NovoClienteRequestDTO;
import com.everis.helloworld.exception.ErroApiException;
import com.everis.helloworld.helper.ClientesHelper;
import com.everis.helloworld.viewmodel.ClienteViewModel;
import com.everis.helloworld.viewmodel.ContaViewModel;
import com.everis.helloworld.viewmodel.DetalhesClienteViewModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class HelloWorldControllerTests {

    @InjectMocks
    private HelloWorldController controller;

    @Mock
    private ClientesHelper helper;

    @Mock
    private Model model;

    private MockMvc mockMvc;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void abrirPaginaListagemClientesTest() throws Exception {
        when(helper.findAllClientes()).thenReturn(getClientes());
        String paginaEsperada = "index";
        String pagina = controller.abrirPaginaListagemClientes(model);

        assertEquals(pagina, paginaEsperada);

    }

    @Test
    public void abrirPaginaListagemClientesComErroTest() throws Exception {
        String mensagemErro = "Não foi possível executar a chamada";
        when(helper.findAllClientes()).thenThrow(new ErroApiException(mensagemErro));

        String paginaEsperada = "erro";
        String pagina = controller.abrirPaginaListagemClientes(model);

        assertEquals(pagina, paginaEsperada);

    }

    @Test
    public void abrirPaginaListagemClientesComFiltrosTest() throws Exception {
        when(helper.findAllClientes(any(), any(), any()))
                .thenReturn(getClientes());
        String paginaEsperada = "index";
        String pagina = controller.abrirPaginaListagemClientesComFiltros(model, "Nome", 1, 10);

        assertEquals(pagina, paginaEsperada);

    }

    @Test
    public void abrirPaginaListagemClientesComFiltrosComErroTest() throws Exception {
        String mensagemErro = "Não foi possível executar a chamada";
        when(helper.findAllClientes(any(), any(), any()))
                .thenThrow(new ErroApiException(mensagemErro));

        String paginaEsperada = "erro";
        String pagina = controller.abrirPaginaListagemClientesComFiltros(model, "Nome", 1, 10);

        assertEquals(pagina, paginaEsperada);

    }


    @Test
    public void abrirPaginaCadastroTest() throws Exception {
        String paginaEsperada = "novo-cliente";
        String pagina = controller.abrirPaginaCadastro(model);

        assertEquals(pagina, paginaEsperada);

    }

    @Test
    public void inserirClienteTest() throws Exception {
        when(helper.findAllClientes()).thenReturn(getClientes());
        when(helper.inserir(any())).thenReturn(getCliente());

        String paginaEsperada = "index";
        String pagina = controller.inserirCliente(model, getNovoClienteRequest());

        assertEquals(pagina, paginaEsperada);
    }


    @Test
    public void inserirClienteComErroTest() throws Exception {
        String mensagemErro = "Não foi possível executar a chamada";
        when(helper.findAllClientes()).thenThrow(new ErroApiException(mensagemErro));
        when(helper.inserir(any())).thenReturn(getCliente());

        String paginaEsperada = "erro";
        String pagina = controller.inserirCliente(model, getNovoClienteRequest());

        assertEquals(pagina, paginaEsperada);


    }

    @Test
    public void abrirPaginaEditarClienteTest() throws Exception {
        when(helper.findById(any())).thenReturn(getDetalhesCliente());

        String paginaEsperada = "editar-cliente";
        String pagina = controller.abrirPaginaEditarCliente(model, any());

        assertEquals(pagina, paginaEsperada);

    }

    @Test
    public void editarClienteTest() throws Exception {
        when(helper.editar(any())).thenReturn(getCliente());

        String paginaEsperada = "index";
        String pagina = controller.editarCliente(model, "1", getEditarClienteRequest());

        assertEquals(pagina, paginaEsperada);

    }

    @Test
    public void excluirPeloIdTest() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        doNothing().when(helper).excluirPeloId(any());

        mockMvc.perform(delete("/clientes/{id}", "1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNoContent());

    }

    private EditarClienteRequestDTO getEditarClienteRequest() {
        EditarClienteRequestDTO request = new EditarClienteRequestDTO();
        request.setId("1");
        request.setNome("Cliente");
        request.setCargo("Cargo");
        request.setAtivo(Boolean.TRUE);

        return request;
    }

    private NovoClienteRequestDTO getNovoClienteRequest() {
        NovoClienteRequestDTO request = new NovoClienteRequestDTO();
        request.setNome("Cliente");
        request.setCargo("Cargo");

        return request;
    }

    private ClienteViewModel getCliente() {
        return ClienteViewModel.builder()
                .id("1")
                .nome("Cliente")
                .cargo("Cargo")
                .build();
    }

    private DetalhesClienteViewModel getDetalhesCliente() {
        return DetalhesClienteViewModel.builder()
                .id("1")
                .nome("Cliente")
                .cargo("Cargo")
                .ativo(Boolean.TRUE)
                .contas(Collections.singletonList(
                        ContaViewModel.builder()
                                .numeroConta("0018123456-7")
                                .saldo("R$ 100,00")
                                .build()))
                .build();
    }

    private List<ClienteViewModel> getClientes() {
        List<ClienteViewModel> clientes = new ArrayList<>();
        clientes.add(ClienteViewModel.builder()
                .id("1")
                .nome("Cliente")
                .cargo("Cargo")
                .build());

        return clientes;
    }

}
