package com.poc.pucmg.clientesespeciais.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.poc.pucmg.clientesespeciais.modelo.ItemDoOrcamento;
import com.poc.pucmg.clientesespeciais.modelo.Orcamento;
import com.poc.pucmg.clientesespeciais.modelo.Produto;
import com.poc.pucmg.clientesespeciais.servicos.ClientesEspeciaisService;

@RestController
public class ClientesEspeciaisController {

	@Autowired
	private ClientesEspeciaisService clientesEspeciaisService;

	@RequestMapping(value = "/produto/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Produto consultarProdutoPorIdentificador(@PathVariable Long id) {
		return clientesEspeciaisService.consultarProdutoPorIdentificador(id);
	}

	@RequestMapping(value = "/produto/orcamento", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseBody
	public Orcamento solicitarOrcamento(@RequestBody List<ItemDoOrcamento> itensOrcamento) {
		return clientesEspeciaisService.solicitarOrcamento(itensOrcamento);
	}

}
