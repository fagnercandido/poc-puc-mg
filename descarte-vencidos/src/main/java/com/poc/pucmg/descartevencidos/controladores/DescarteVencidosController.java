package com.poc.pucmg.descartevencidos.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.poc.pucmg.descartevencidos.servicos.ProdutoService;

@RestController
public class DescarteVencidosController {

	@Autowired
	private ProdutoService produtoService;

	@RequestMapping(value = "/descartevencidos/", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity solicitarRecolhimento() {
		produtoService.solicitarRecolhimento();
		return ResponseEntity.ok().build();
	}

}
