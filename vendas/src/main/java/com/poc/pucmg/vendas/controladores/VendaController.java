package com.poc.pucmg.vendas.controladores;

import java.util.List;

import com.poc.pucmg.vendas.modelo.ItemDaVenda;
import com.poc.pucmg.vendas.modelo.Venda;
import com.poc.pucmg.vendas.servicos.VendaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @RequestMapping(value = "/vendas", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Venda> recuperarTodos() {
        return vendaService.recuperarTodos();
    }

    @RequestMapping(value = "/vendas", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public Venda salvar(@RequestBody List<ItemDaVenda> itensDaVenda) {
        return vendaService.salvar(itensDaVenda);
    }

}