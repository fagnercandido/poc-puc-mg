package com.poc.pucmg.vendas.servicos;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.pucmg.vendas.integracao.IntegracaoServicos;
import com.poc.pucmg.vendas.modelo.ItemDaVenda;
import com.poc.pucmg.vendas.modelo.Produto;
import com.poc.pucmg.vendas.modelo.Venda;
import com.poc.pucmg.vendas.repositorio.VendaRepository;

@Service
public class VendaService {

	@Autowired
	private VendaRepository vendaRepository;

	@Autowired
	private IntegracaoServicos integracaoServicos;

	public List<Venda> recuperarTodos() {
		return vendaRepository.findAll();
	}

	public Venda salvar(List<ItemDaVenda> itensDaVenda) {
		Venda venda = criarVenda(itensDaVenda);
		if (!integracaoServicos.ePossivelAtualizarEstoque(criarListaProdutos(itensDaVenda))) {
			throw new IllegalArgumentException("Itens insuficientes em estoque.");
		}
		return vendaRepository.save(venda);
	}

	private List<Produto> criarListaProdutos(List<ItemDaVenda> itensDaVenda) {
		List<Produto> produtos = new LinkedList<>();
		itensDaVenda.stream().forEach(item -> {
			Produto produto = item.getProduto();
			produto.setQuantidade(item.getQuantidade());
			produtos.add(produto);
		});
		return produtos;
	}

	private Venda criarVenda(List<ItemDaVenda> itensDaVenda) {
		Venda venda = new Venda();
		Iterator<ItemDaVenda> iteratorItemVenda = itensDaVenda.iterator();
		while (iteratorItemVenda.hasNext()) {
			ItemDaVenda itemDaVenda = iteratorItemVenda.next();
			possuiEstoqueDisponivel(itemDaVenda);
			itemDaVenda.setVenda(venda);
			venda.getItensDaVenda().add(itemDaVenda);
			venda.setValorTotal(adicionarValorItem(venda, itemDaVenda));
		}
		venda.setData(new Date());
		return venda;
	}

	private void possuiEstoqueDisponivel(ItemDaVenda itemDaVenda) {
		Long identificadorProduto = itemDaVenda.getProduto().getId();
		Produto produto = integracaoServicos.obterProdutoPorIdentificador(identificadorProduto);
		possuiQuantidadeEmEstoqueSuficiente(itemDaVenda.getQuantidade(), produto.getQuantidade());
	}

	private void possuiQuantidadeEmEstoqueSuficiente(Integer itemQuantidade, Integer produtoQuantidade) {
		Integer quantidade = produtoQuantidade - itemQuantidade;
		if (quantidade < 0) {
			throw new IllegalArgumentException("Quantidade insuficiente em estoque.");
		}
	}

	private BigDecimal adicionarValorItem(Venda venda, ItemDaVenda itemDaVenda) {
		return venda.getValorTotal()
				.add(itemDaVenda.getValorUnitario().multiply(BigDecimal.valueOf(itemDaVenda.getQuantidade())));
	}

}