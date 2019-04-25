package com.poc.pucmg.estoque.modelo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "venda")
public class Venda {

    @Id
    @GeneratedValue(generator = "venda_generator")
    @SequenceGenerator(name = "venda_generator", sequenceName = "venda_sequence", initialValue = 1)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, mappedBy = "venda")
    private List<ItemDaVenda> itensDaVenda;

    @Temporal(TemporalType.DATE)
    private Date data;

    private BigDecimal desconto;

    private BigDecimal valorTotal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ItemDaVenda> getItensDaVenda() {
        return itensDaVenda;
    }

    public void setItensDaVenda(List<ItemDaVenda> itensDaVenda) {
        this.itensDaVenda = itensDaVenda;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

}