package com.poc.pucmg.clientesespeciais.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.pucmg.clientesespeciais.integracoes.IntegracaoServicos;
import com.poc.pucmg.clientesespeciais.modelo.ItemDoOrcamento;
import com.poc.pucmg.clientesespeciais.modelo.Orcamento;
import com.poc.pucmg.clientesespeciais.modelo.Produto;

@Service
public class ClientesEspeciaisService {

	@Autowired
	private IntegracaoServicos integracaoServicos;

	public Produto consultarProdutoPorIdentificador(Long identificador) {
		return integracaoServicos.obterProdutoPorIdentificador(identificador);
	}

	public Orcamento solicitarOrcamento(List<ItemDoOrcamento> itensOrcamento) {
		return integracaoServicos.solicitarOrcamento(itensOrcamento);
	}

}
