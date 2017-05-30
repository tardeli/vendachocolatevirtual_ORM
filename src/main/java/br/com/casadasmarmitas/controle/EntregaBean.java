/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.casadasmarmitas.controle;

import br.com.casadasmarmitas.dao.EntregaDao;
import br.com.casadasmarmitas.dao.EntregadorDao;
import br.com.casadasmarmitas.dao.PedidoDao;
import br.com.casadasmarmitas.modelo.Entrega;
import br.com.casadasmarmitas.modelo.Entregador;
import br.com.casadasmarmitas.modelo.Pedido;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.omnifaces.util.Messages;

/**
 *
 * @author Tardeli
 */
@ManagedBean
@SessionScoped
public class EntregaBean {

    private Entrega entrega;
    private List<Entregador> listaEntregadores;
    private EntregadorDao entregadorDao = new EntregadorDao();

    public List<Entregador> getListaEntregadores() {
        return listaEntregadores = entregadorDao.listarObjetos();
    }

    public void setListaEntregadores(List<Entregador> listaEntregadores) {
        this.listaEntregadores = listaEntregadores;
    }

    public Entrega getEntrega() {
        if (entrega == null) {
            entrega = new Entrega();
        }
        return entrega;
    }

    public void setEntrega(Entrega entrega) {
        this.entrega = entrega;
    }

}
