/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Teste;

import br.com.casadasmarmitas.dao.EntregaDao;
import br.com.casadasmarmitas.dao.EntregadorDao;
import br.com.casadasmarmitas.dao.PedidoDao;
import br.com.casadasmarmitas.modelo.Entrega;
import br.com.casadasmarmitas.modelo.Entregador;
import br.com.casadasmarmitas.modelo.Pedido;
import org.junit.Test;

/**
 *
 * @author Tardeli
 */
public class EntregaTeste {
    @Test
    public void cadastrar(){
        Entrega entrega = new Entrega();
        EntregaDao entregaDao = new EntregaDao();
        
        PedidoDao pedidoDao = new PedidoDao();
        Pedido pedido = pedidoDao.buscarObjeto(4L);
        
        EntregadorDao entregadorDao = new EntregadorDao();
        Entregador entregador = entregadorDao.buscarObjeto(1L);
        
        entrega.setPedido(pedido);
        entrega.setEntregador(entregador);
        entrega.setTaxa(4.50);
        entrega.getHora();
        entregaDao.salvarOuAtualizarObjeto(entrega);
        
    }
}
