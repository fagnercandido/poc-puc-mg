package com.poc.pucmg.descartevencidos.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "produto")
public class Produto implements Serializable {

	private static final long serialVersionUID = -6513383497091507598L;

	@Id
	@GeneratedValue(generator = "produto_generator")
	@SequenceGenerator(name = "produto_generator", sequenceName = "produto_sequence", initialValue = 1)
	private Long id;

	private String descricao;

	private Integer quantidade;

	private BigDecimal valor;

	@Temporal(TemporalType.DATE)
	private Date dataVencimento;

	@Enumerated(EnumType.STRING)
	private SituacaoProduto situacaoProduto;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "fornecedor_id", nullable = false)
	private Fornecedor fornecedor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public SituacaoProduto getSituacaoProduto() {
		return situacaoProduto;
	}

	public void setSituacaoProduto(SituacaoProduto situacaoProduto) {
		this.situacaoProduto = situacaoProduto;
	}

}