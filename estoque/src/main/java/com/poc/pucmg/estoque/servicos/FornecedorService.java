package com.poc.pucmg.estoque.servicos;

import com.poc.pucmg.estoque.modelo.Fornecedor;
import com.poc.pucmg.estoque.modelo.Produto;
import com.poc.pucmg.estoque.repositorio.FornecedorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

	public Fornecedor salvar(Fornecedor fornecedor) {
		return fornecedorRepository.save(fornecedor);
	}

}