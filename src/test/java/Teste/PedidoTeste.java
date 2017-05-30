/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Teste;

import br.com.casadasmarmitas.dao.ClienteDao;
import br.com.casadasmarmitas.dao.FuncionarioDao;
import br.com.casadasmarmitas.dao.ItemPedidoDao;
import br.com.casadasmarmitas.dao.PedidoDao;
import br.com.casadasmarmitas.modelo.Cliente;
import br.com.casadasmarmitas.modelo.Funcionario;
import br.com.casadasmarmitas.modelo.Pedido;
import br.com.casadasmarmitas.enumeradores.Status;
import br.com.casadasmarmitas.modelo.ItemPedido;
import java.util.Date;
import java.util.List;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Tardeli
 */
public class PedidoTeste {

    @Test
    @Ignore
    public void cadastrar() {
        Pedido pedido = new Pedido();
        PedidoDao pedidoDao = new PedidoDao();
        
        //pedido.setCodigo(1l); //atualizar o campo 1
        
        ClienteDao clienteDao = new ClienteDao();
        Cliente cliente = clienteDao.buscarObjeto(1L);
        
        FuncionarioDao funcionarioDao = new FuncionarioDao();
        Funcionario funcionario = funcionarioDao.buscarObjeto(4L);
                       
        //pedido.setCliente(null);
//        pedido.setCliente(cliente);
        System.out.println(pedido.getCliente().getClass().getName());
        pedido.setData(new Date());
        pedido.setTotal(200.00);
                       
        pedidoDao.salvarOuAtualizarObjeto(pedido);
        
       ///////////////////////////////////////////////////////////////////////////
       
       
       
       
//        ItemPedidoDao itemPedidoDao = new ItemPedidoDao();
//        List<ItemPedido> itens = new ArrayList<>();
//        itens = itemPedidoDao.listarObjetos();
//        
//        for (ItemPedido iten : itens) {
//            ItemPedido itemPedido = new ItemPedido();
//            itemPedido.getPedido().setCodigo(codigoPedido);
//            itemPedido.getProduto().setCodigo(iten.getProduto().getCodigo());
//            itemPedido.setQuantidade(iten.getQuantidade());
//            itemPedido.setTotal(iten.getTotal());
//            
//            itemPedidoDao.salvarOuAtualizarObjeto(itemPedido);
//                    
//        }
        
    }
    
    @Test
    public void buscarPedido(){
        PedidoDao dao = new PedidoDao();
        Pedido pedido = dao.buscarObjeto(1L);
        System.out.println("-------------------------------------------");
        System.out.println(pedido.toString());
        System.out.println("-------------------------------------------"); 
        
        ItemPedidoDao itemPedidoDao = new ItemPedidoDao();
        List<ItemPedido> itensPedido = itemPedidoDao.buscarItensPedido(11L);
        
        for (ItemPedido itemPedido : itensPedido) {
            System.out.println(itemPedido.toString());
        }
        
        
        
    }
    
    @Test
    @Ignore
    public void listar(){
        PedidoDao dao = new PedidoDao();
        List<Pedido> pedidos = dao.listarObjetos();
        System.out.println("-------------------------------------------");
        for (Pedido pedido : pedidos) {
            System.out.println(pedido.toString());
            System.out.println("-------------------------------------------");
        }
    }
    
}
