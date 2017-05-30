/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.casadasmarmitas.dao;

import br.com.casadasmarmitas.modelo.Entrega;
import br.com.casadasmarmitas.modelo.ItemPedido;
import br.com.casadasmarmitas.modelo.Pedido;
import br.com.casadasmarmitas.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Tardeli
 */
public class PedidoDao extends Generic_Dao<Pedido> {

    private Session sessao;
    private Transaction transacao;

    public Long salvar(Pedido pedido, List<ItemPedido> itens, Entrega entrega, boolean cadastroEntrega) {
        Long codigo = null;
        try {
            sessao = (Session) HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();
            codigo = (Long) sessao.save(pedido);
            transacao.commit();
            Pedido p = this.buscarObjeto(codigo);

            for (ItemPedido item : itens) {
                item.setPedido(p);
                ItemPedidoDao itemPedidoDao = new ItemPedidoDao();
                itemPedidoDao.salvarOuAtualizarObjeto(item);
            }

            if (cadastroEntrega == true) {

                entrega.setPedido(pedido);
                EntregaDao entregaDao = new EntregaDao();
                entregaDao.salvarOuAtualizarObjeto(entrega);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessao.close();
        }
        return codigo;
    }
}
