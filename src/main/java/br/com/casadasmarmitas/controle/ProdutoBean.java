/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.casadasmarmitas.controle;

import br.com.casadasmarmitas.dao.ProdutoDao;
import br.com.casadasmarmitas.modelo.Produto;
import br.com.casadasmarmitas.enumeradores.Tamanho;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.omnifaces.util.Messages;

/**
 *
 * @author Tardeli
 */
@ManagedBean
@SessionScoped
public class ProdutoBean implements Serializable {

    private Produto produto = new Produto();
    private List<Produto> listaObjetos;
    private ProdutoDao clienteDao = new ProdutoDao();
    
    private Tamanho[] tamanho;

    public Tamanho[] getTamanho() {
        return Tamanho.values();
    }

    public void setTamanho(Tamanho[] tamanho) {
        this.tamanho = tamanho;
    }
    
    public ProdutoBean() {
        listaObjetos = new ArrayList<>();
        getListaObjetos();
    }

    public void novo() {
        this.produto = new Produto();
    }

    public String salvar() {
        if (this.produto.getCodigo() == null) {  
            clienteDao.salvarOuAtualizarObjeto(this.produto);
            getListaObjetos();
            Messages.addGlobalInfo("Salvo com sucesso!");
            this.produto = new Produto();
            return "erro";
        } else {
            clienteDao.salvarOuAtualizarObjeto(produto);
            getListaObjetos();
            Messages.addGlobalInfo("Atualizado com Sucesso!");
            this.produto = new Produto();
            return "erro.xhtml";
        }
    }

    public void excluir(Produto c) {
        this.produto = c;
        Messages.addGlobalInfo("Excluido com Sucesso!");
        clienteDao.deletarObjeto(produto);
        getListaObjetos();
        this.produto = new Produto();
    }

    public void carregarDadosEditar(Produto c) {
        this.produto = c;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public ProdutoDao getClienteDao() {
        return clienteDao;
    }

    public void setClienteDao(ProdutoDao clienteDao) {
        this.clienteDao = clienteDao;
    }

    public List<Produto> getListaObjetos() {
        return listaObjetos = clienteDao.listarObjetos();
    }

    public void setListaObjetos(List<Produto> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }
   
    
    
}
