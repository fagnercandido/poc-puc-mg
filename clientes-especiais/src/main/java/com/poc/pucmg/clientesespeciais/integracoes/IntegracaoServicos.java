package com.poc.pucmg.clientesespeciais.integracoes;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.poc.pucmg.clientesespeciais.modelo.ItemDoOrcamento;
import com.poc.pucmg.clientesespeciais.modelo.Orcamento;
import com.poc.pucmg.clientesespeciais.modelo.Produto;

@Service
public class IntegracaoServicos {

	@Value("${url.estoque}")
	private String urlEstoque;

	public Produto obterProdutoPorIdentificador(Long identificador) {
		RestTemplate restTemplate = new RestTemplate();
		Produto produto = restTemplate.getForObject(urlEstoque + identificador, Produto.class);
		return produto;
	}

	public Orcamento solicitarOrcamento(List<ItemDoOrcamento> itensOrcamento) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<List<ItemDoOrcamento>> request = new HttpEntity<>(itensOrcamento, headers);
		ResponseEntity<Orcamento> response = restTemplate.exchange(urlEstoque+"/orcamento", HttpMethod.POST, request,
				Orcamento.class);
		return response.getBody();
	}

}