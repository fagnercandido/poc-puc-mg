package com.poc.pucmg.estoque.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "orcamento")
public class Orcamento implements Serializable {

	private static final long serialVersionUID = 8787542744746392972L;

	public Orcamento() {
		this.itensOrcamento = new LinkedList<>();
		this.valorOrcamento = BigDecimal.ZERO;
	}

	@Id
	@GeneratedValue(generator = "orcamento_generator")
	@SequenceGenerator(name = "orcamento_generator", sequenceName = "orcamento_sequence", initialValue = 1)
	private Long id;

	@Enumerated(EnumType.STRING)
	private TipoOrcamento tipoOrcamento;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, mappedBy = "orcamento")
	private List<ItemDoOrcamento> itensOrcamento;

	private BigDecimal valorOrcamento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoOrcamento getTipoOrcamento() {
		return tipoOrcamento;
	}

	public void setTipoOrcamento(TipoOrcamento tipoOrcamento) {
		this.tipoOrcamento = tipoOrcamento;
	}

	public List<ItemDoOrcamento> getItensOrcamento() {
		return itensOrcamento;
	}

	public void setItensOrcamento(List<ItemDoOrcamento> itensOrcamento) {
		this.itensOrcamento = itensOrcamento;
	}

	public BigDecimal getValorOrcamento() {
		return valorOrcamento;
	}

	public void setValorOrcamento(BigDecimal valorOrcamento) {
		this.valorOrcamento = valorOrcamento;
	}

}
