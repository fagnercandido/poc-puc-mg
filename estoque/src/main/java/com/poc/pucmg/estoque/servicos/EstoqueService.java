package com.poc.pucmg.estoque.servicos;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.pucmg.estoque.modelo.ItemDoOrcamento;
import com.poc.pucmg.estoque.modelo.Orcamento;
import com.poc.pucmg.estoque.modelo.Produto;
import com.poc.pucmg.estoque.modelo.TipoOrcamento;
import com.poc.pucmg.estoque.repositorio.EstoqueRepository;
import com.poc.pucmg.estoque.repositorio.OrcamentoRepository;

@Service
public class EstoqueService {

	@Autowired
	private EstoqueRepository estoqueRepository;

	@Autowired
	private OrcamentoRepository orcamentoRepository;

	public List<Produto> recuperarTodos() {
		return estoqueRepository.findAll();
	}

	public Produto salvar(Produto produto) {
		return estoqueRepository.save(produto);
	}

	public void atualizar(List<Produto> produtos) {
		produtos.stream().forEach(item -> {
			Optional<Produto> optionalProduto = estoqueRepository.findById(item.getId());
			if (optionalProduto.isPresent()) {
				Produto produto = optionalProduto.get();
				produto.setQuantidade(possuiQuantidadeEmEstoqueSuficiente(item, produto));
				estoqueRepository.save(produto);
			}
		});
	}

	private int possuiQuantidadeEmEstoqueSuficiente(Produto item, Produto produto) {
		Integer quantidade = produto.getQuantidade() - item.getQuantidade();
		if (quantidade < 0) {
			throw new IllegalArgumentException("Quantidade insuficiente em estoque.");
		}
		return quantidade;
	}

	public Produto recuperarPorIdentificador(Long id) {
		Optional<Produto> optionalProduto = estoqueRepository.findById(id);
		if (optionalProduto.isPresent()) {
			return optionalProduto.get();
		}
		return null;
	}

	public Orcamento solicitarOrcamento(List<ItemDoOrcamento> itensOrcamento) {
		Orcamento orcamento = criarOrcamento(itensOrcamento);
		orcamentoRepository.save(orcamento);
		return orcamento;
	}

	private Orcamento criarOrcamento(List<ItemDoOrcamento> itensOrcamento) {
		Orcamento orcamento = new Orcamento();
		orcamento.setTipoOrcamento(TipoOrcamento.EXTERNO);
		Iterator<ItemDoOrcamento> iteratorItemOrcamento = itensOrcamento.iterator();
		while (iteratorItemOrcamento.hasNext()) {
			ItemDoOrcamento itemOrcamento = iteratorItemOrcamento.next();
			Produto itemProduto = itemOrcamento.getProduto();
			Optional<Produto> optionalProduto = estoqueRepository.findById(itemProduto.getId());
			if (optionalProduto.isPresent()) {
				itemOrcamento.setOrcamento(orcamento);
				orcamento.getItensOrcamento().add(itemOrcamento);
				orcamento.setValorOrcamento(calcularValorProduto(orcamento, itemOrcamento));
			}
		}
		return orcamento;
	}

	private BigDecimal calcularValorProduto(Orcamento orcamento, ItemDoOrcamento itemOrcamento) {
		return orcamento.getValorOrcamento().add(itemOrcamento.getValorUnitario()
				.multiply(BigDecimal.valueOf(itemOrcamento.getQuantidade().longValue())));
	}

}