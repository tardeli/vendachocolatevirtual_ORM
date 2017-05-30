/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Teste;

import br.com.casadasmarmitas.dao.ItemPedidoDao;
import br.com.casadasmarmitas.dao.PedidoDao;
import br.com.casadasmarmitas.dao.ProdutoDao;
import br.com.casadasmarmitas.modelo.ItemPedido;
import br.com.casadasmarmitas.modelo.Pedido;
import br.com.casadasmarmitas.modelo.Produto;
import org.junit.Test;

/**
 *
 * @author Tardeli
 */
public class ItemPedidoTeste {
    @Test
    public void cadastrar(){
        ItemPedido itemPedido = new ItemPedido();
        ItemPedidoDao itemPedidoDao = new ItemPedidoDao();
        
        ProdutoDao produtoDao = new ProdutoDao();
        Produto produto = produtoDao.buscarObjeto(1L);
        
        PedidoDao pedidoDao = new PedidoDao();
        Pedido pedido = pedidoDao.buscarObjeto(1L);
        
        itemPedido.setPedido(pedido);
        itemPedido.setProduto(produto);
        itemPedido.setQuantidade(1);
               
        itemPedidoDao.salvarOuAtualizarObjeto(itemPedido);
                   
    }
}
