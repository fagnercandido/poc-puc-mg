package com.poc.pucmg.estoque.servicos;

import java.util.List;
import java.util.Optional;

import com.poc.pucmg.estoque.modelo.Produto;
import com.poc.pucmg.estoque.repositorio.EstoqueRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    public List<Produto> recuperarTodos() {
        return estoqueRepository.findAll();
    }

    public Produto salvar(Produto produto) {
        return estoqueRepository.save(produto);
    }

    public Produto atualizar(List<Produto> produtos) {
        produtos.stream().forEach(item -> {
            Optional<Produto> optionalProduto = estoqueRepository.findById(item.getId());
            if(optionalProduto.isPresent()) {
                Produto produto = optionalProduto.get();
                produto.setQuantidade(possuiQuantidadeEmEstoqueSuficiente(item, produto));
                estoqueRepository.save(produto);
            }
        });
        return null;
    }

    private int possuiQuantidadeEmEstoqueSuficiente(Produto item, Produto produto) {
        Integer quantidade = produto.getQuantidade() - item.getQuantidade();
        if(quantidade < 0) {
            throw new IllegalArgumentException("Quantidade insuficiente em estoque.");
        }
        return quantidade;
    }

}