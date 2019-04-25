package com.poc.pucmg.estoque.repositorio;

import com.poc.pucmg.estoque.modelo.Fornecedor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

}