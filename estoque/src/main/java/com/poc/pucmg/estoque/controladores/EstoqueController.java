package com.poc.pucmg.estoque.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.poc.pucmg.estoque.modelo.ItemDoOrcamento;
import com.poc.pucmg.estoque.modelo.Orcamento;
import com.poc.pucmg.estoque.modelo.Produto;
import com.poc.pucmg.estoque.servicos.EstoqueService;

@RestController
public class EstoqueController {

    @Autowired
    private EstoqueService estoqueService;

    @RequestMapping(value = "/estoque", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Produto> recuperarTodos() {
        return estoqueService.recuperarTodos();
    }

    @RequestMapping(value = "/estoque/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Produto recuperarPorIdentificador(@PathVariable Long id) {
        return estoqueService.recuperarPorIdentificador(id);
    }

    @RequestMapping(value = "/estoque", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public Produto salvar(@RequestBody Produto produto) {
        return estoqueService.salvar(produto);
    }
    
    @RequestMapping(value = "/estoque/orcamento", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public Orcamento solicitarOrcamento(@RequestBody List<ItemDoOrcamento> itensOrcamento) {
        return estoqueService.solicitarOrcamento(itensOrcamento);
    }

    @RequestMapping(value = "/estoque/", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity atualizar(@RequestBody List<Produto> produtos) {
        estoqueService.atualizar(produtos);
        return ResponseEntity.ok().build();
    }

}