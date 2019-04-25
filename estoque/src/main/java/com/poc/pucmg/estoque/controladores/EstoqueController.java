package com.poc.pucmg.estoque.controladores;

import java.util.List;

import com.poc.pucmg.estoque.modelo.Produto;
import com.poc.pucmg.estoque.servicos.EstoqueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EstoqueController {

    @Autowired
    private EstoqueService estoqueService;

    @RequestMapping(value = "/estoque", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Produto> recuperarTodos() {
        return estoqueService.recuperarTodos();
    }

    @RequestMapping(value = "/estoque", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public Produto salvar(@RequestBody Produto produto) {
        return estoqueService.salvar(produto);
    }

}