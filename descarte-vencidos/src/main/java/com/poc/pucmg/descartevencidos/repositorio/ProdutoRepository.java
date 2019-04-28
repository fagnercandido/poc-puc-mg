package com.poc.pucmg.descartevencidos.repositorio;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.poc.pucmg.descartevencidos.modelo.Produto;
import com.poc.pucmg.descartevencidos.modelo.SituacaoProduto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	@Query("select p from Produto p where p.dataVencimento <= :dataVencimento and p.situacaoProduto = :situacaoProduto")
	List<Produto> findAllWithDataVencimentoAndNotActive(@Param("dataVencimento") Date dataVencimento,
			@Param("situacaoProduto") SituacaoProduto situacaoProduto);

	@Query("select p from Produto p where p.situacaoProduto = :situacaoProduto")
	List<Produto> findAllDue(@Param("situacaoProduto") SituacaoProduto vencido);

}