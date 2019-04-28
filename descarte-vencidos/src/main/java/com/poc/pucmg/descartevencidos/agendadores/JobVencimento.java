package com.poc.pucmg.descartevencidos.agendadores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.poc.pucmg.descartevencidos.servicos.ProdutoService;

@Component
public class JobVencimento {
	
	@Autowired
	private ProdutoService produtoService;
	
	@Scheduled(fixedRate = 5000)
    public void avaliarVencimento() {
		produtoService.avaliarVencimento();
    }

}
