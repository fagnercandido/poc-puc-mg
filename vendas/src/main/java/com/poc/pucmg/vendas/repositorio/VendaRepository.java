package com.poc.pucmg.vendas.repositorio;

import com.poc.pucmg.vendas.modelo.Venda;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

}