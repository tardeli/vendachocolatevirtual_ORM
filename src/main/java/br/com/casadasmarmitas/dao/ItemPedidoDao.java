/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.casadasmarmitas.dao;

import br.com.casadasmarmitas.modelo.ItemPedido;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tardeli
 */
public class ItemPedidoDao extends Generic_Dao<ItemPedido> {

    public List<ItemPedido> buscarItensPedido(Long codigo) {

        List<ItemPedido> listaTemporaria = listarObjetos();
        List<ItemPedido> itensPedido = new ArrayList<>();

        try {
            for (ItemPedido itemPedido : listaTemporaria) {
                if (itemPedido.getPedido().getCodigo().equals(codigo)) {
                    itensPedido.add(itemPedido);
                }
            }
            return itensPedido;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
