package com.everis.helloworld.controller;

import com.everis.helloworld.dto.EditarClienteRequestDTO;
import com.everis.helloworld.dto.NovoClienteRequestDTO;
import com.everis.helloworld.exception.ErroApiException;
import com.everis.helloworld.helper.ClientesHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Controller
public class HelloWorldController {

    @Autowired
    private ClientesHelper helper;

    @RequestMapping(path = {"/", "/index"}, method = RequestMethod.GET)
    public String abrirPaginaListagemClientes(Model model) {
        try {
            model.addAttribute("clientes", helper.findAllClientes());
            return "index";

        } catch (ErroApiException e) {
            e.printStackTrace();
            model.addAttribute("erro", e.getMessage());
            return "erro";
        }
    }

    @GetMapping(path = {"/", "/index"}, params = {"nome"})
    public String abrirPaginaListagemClientesComFiltros(
            Model model,
            @RequestParam String nome,
            @RequestParam(required = false, defaultValue = "1") Integer pagina,
            @RequestParam(required = false, defaultValue = "10") Integer limite) {

        try {
            model.addAttribute("nome", nome);
            model.addAttribute("clientes", helper.findAllClientes(nome, pagina, limite));
            return "index";

        } catch (ErroApiException e) {
            e.printStackTrace();
            model.addAttribute("erro", e.getMessage());
            return "erro";
        }
    }

    @GetMapping("/clientes/novo-cliente")
    public String abrirPaginaCadastro(Model model) {
        model.addAttribute("cliente", new NovoClienteRequestDTO());
        return "novo-cliente";
    }

    @PostMapping("/clientes/novo-cliente")
    public String inserirCliente(Model model, @ModelAttribute("cliente") NovoClienteRequestDTO request) {
        try {
            helper.inserir(request);

            return abrirPaginaListagemClientes(model);

        } catch (ErroApiException e) {
            e.printStackTrace();
            model.addAttribute("erro", e.getMessage());
            return "erro";
        }
    }

    @GetMapping("/clientes/editar/{id}")
    public String abrirPaginaEditarCliente(Model model, @PathVariable String id) {
        try {
            model.addAttribute("cliente", helper.findById(id));
            return "editar-cliente";

        } catch (ErroApiException e) {
            e.printStackTrace();
            model.addAttribute("erro", e.getMessage());
            return "erro";
        }
    }

    @PutMapping("/clientes/editar/{id}")
    public String editarCliente(Model model, @PathVariable String id, @ModelAttribute("cliente") EditarClienteRequestDTO request) {
        try {
            helper.editar(request);

            return abrirPaginaListagemClientes(model);

        } catch (ErroApiException e) {
            e.printStackTrace();
            model.addAttribute("erro", e.getMessage());
            return "erro";
        }
    }

    @DeleteMapping("/clientes/{id}")
    @ResponseBody
    public ResponseEntity<Object> excluirPeloId(@PathVariable String id) {
        try {
            helper.excluirPeloId(id);

            return ResponseEntity.noContent().build();

        } catch (ErroApiException e) {
            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("erro", e.getMessage()));
        }
    }


}
