package com.poc.pucmg.estoque.controladores;

import com.poc.pucmg.estoque.modelo.Fornecedor;
import com.poc.pucmg.estoque.modelo.Produto;
import com.poc.pucmg.estoque.servicos.FornecedorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    @RequestMapping(value = "/fornecedor", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public Fornecedor salvar(@RequestBody Fornecedor fornecedor) {
        return fornecedorService.salvar(fornecedor);
    }

    @RequestMapping(value = "/fornecedor", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public Fornecedor atualizar(@RequestBody Fornecedor fornecedor) {
        return fornecedorService.atualizar(fornecedor);
    }

}