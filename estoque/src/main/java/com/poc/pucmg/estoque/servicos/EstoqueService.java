package com.poc.pucmg.estoque.servicos;

import java.util.List;

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

}