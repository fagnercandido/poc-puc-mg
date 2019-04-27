package com.poc.pucmg.vendas.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "item_da_venda")
public class ItemDaVenda implements Serializable {

    private static final long serialVersionUID = 232403413897426072L;

    @Id
    @GeneratedValue(generator = "item_venda_generator")
    @SequenceGenerator(name = "item_venda_generator", sequenceName = "item_venda_sequence", initialValue = 1)
    private Long id;

    @ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="produto_id", nullable=false)
    private Produto produto;

    private Integer quantidade;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "venda_id", nullable = false)
    @JsonIgnore
    private Venda venda;

    private BigDecimal valorUnitario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

}