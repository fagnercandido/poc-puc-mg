package com.poc.pucmg.vendas.servicos;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.poc.pucmg.vendas.modelo.ItemDaVenda;
import com.poc.pucmg.vendas.modelo.Venda;
import com.poc.pucmg.vendas.repositorio.VendaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    public List<Venda> recuperarTodos() {
        return vendaRepository.findAll();
    }

    public Venda salvar(List<ItemDaVenda> itensDaVenda) {
        Venda venda = criarVenda(itensDaVenda);

        return vendaRepository.save(venda);
    }

    private Venda criarVenda(List<ItemDaVenda> itensDaVenda) {
        Venda venda = new Venda();
        Iterator<ItemDaVenda> iteratorItemVenda = itensDaVenda.iterator();
        while (iteratorItemVenda.hasNext()) {
            ItemDaVenda itemDaVenda = iteratorItemVenda.next();
            itemDaVenda.setVenda(venda);
            venda.getItensDaVenda().add(itemDaVenda);
            venda.setValorTotal(adicionarValorItem(venda, itemDaVenda));
        }
        venda.setData(new Date());
        return venda;
    }

    private BigDecimal adicionarValorItem(Venda venda, ItemDaVenda itemDaVenda) {
        return venda.getValorTotal().add(itemDaVenda.getValorUnitario().multiply(BigDecimal.valueOf(itemDaVenda.getQuantidade())));
    }


}