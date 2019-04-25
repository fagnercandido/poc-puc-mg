package com.poc.pucmg.estoque.repositorio;

import com.poc.pucmg.estoque.modelo.Produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstoqueRepository extends JpaRepository<Produto, Long> {

}