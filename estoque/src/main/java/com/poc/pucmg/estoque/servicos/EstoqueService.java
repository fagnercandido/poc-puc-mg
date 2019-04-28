package com.poc.pucmg.estoque.servicos;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.pucmg.estoque.modelo.ItemDoOrcamento;
import com.poc.pucmg.estoque.modelo.Orcamento;
import com.poc.pucmg.estoque.modelo.Produto;
import com.poc.pucmg.estoque.modelo.SituacaoProduto;
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
		List<Produto> produtos = estoqueRepository.findAll();
		return produtos.stream().filter(produto -> isProdutoValido(produto)).collect(Collectors.toList());
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
			Produto produto = optionalProduto.get();
			return isProdutoValido(produto) ? produto : null;
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
			Produto produto = recuperarPorIdentificador(itemProduto.getId());
			if (isProdutoValido(produto)) {
				itemOrcamento.setOrcamento(orcamento);
				orcamento.getItensOrcamento().add(itemOrcamento);
				orcamento.setValorOrcamento(calcularValorProduto(orcamento, itemOrcamento));
			}
		}
		return orcamento;
	}

	private boolean isProdutoValido(Produto produto) {
		return SituacaoProduto.ATIVO.equals(produto.getSituacaoProduto());
	}

	private BigDecimal calcularValorProduto(Orcamento orcamento, ItemDoOrcamento itemOrcamento) {
		return orcamento.getValorOrcamento().add(itemOrcamento.getValorUnitario()
				.multiply(BigDecimal.valueOf(itemOrcamento.getQuantidade().longValue())));
	}

}