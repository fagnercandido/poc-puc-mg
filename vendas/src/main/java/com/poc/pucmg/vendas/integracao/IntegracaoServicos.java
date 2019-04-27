package com.poc.pucmg.vendas.integracao;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.poc.pucmg.vendas.modelo.Produto;

@Service
public class IntegracaoServicos {

    @Value("${url.estoque}")
    private String urlEstoque;

    public Produto obterProdutoPorIdentificador(Long identificador) {
        RestTemplate restTemplate = new RestTemplate();
        Produto produto = restTemplate.getForObject(urlEstoque + identificador, Produto.class);
        return produto;
    }
    
    public boolean ePossivelAtualizarEstoque(List<Produto> produtos) {
    	RestTemplate restTemplate = new RestTemplate();
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	HttpEntity<List<Produto>> request = new HttpEntity<>(produtos, headers);
    	ResponseEntity response = restTemplate.exchange(urlEstoque, HttpMethod.PUT, request, String.class);
    	return response.getStatusCode().is2xxSuccessful();
    }

}