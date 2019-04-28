package com.poc.pucmg.descartevencidos.servicos;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.pucmg.descartevencidos.modelo.Produto;
import com.poc.pucmg.descartevencidos.modelo.SituacaoProduto;
import com.poc.pucmg.descartevencidos.repositorio.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public void avaliarVencimento() {
		List<Produto> produtos = produtoRepository.findAllWithDataVencimentoAndNotActive(new Date(), SituacaoProduto.ATIVO);
		Iterator<Produto> iteratorProdutos = produtos.iterator();
		while (iteratorProdutos.hasNext()) {
			Produto produto = iteratorProdutos.next();
			produto.setSituacaoProduto(SituacaoProduto.VENCIDO);
			produtoRepository.save(produto);
		}
	}

}