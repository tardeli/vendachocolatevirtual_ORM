package br.com.casadasmarmitas.controle;

import br.com.casadasmarmitas.dao.ClienteDao;
import br.com.casadasmarmitas.dao.FuncionarioDao;
import br.com.casadasmarmitas.dao.ItemPedidoDao;
import br.com.casadasmarmitas.dao.PedidoDao;
import br.com.casadasmarmitas.dao.ProdutoDao;
import br.com.casadasmarmitas.modelo.Cliente;
import br.com.casadasmarmitas.modelo.Funcionario;
import br.com.casadasmarmitas.modelo.ItemPedido;
import br.com.casadasmarmitas.modelo.Pedido;
import br.com.casadasmarmitas.modelo.Produto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
public class PedidoBean implements Serializable {

    private List<Produto> listaProdutos;
    private List<ItemPedido> listaItens;
    private List<Cliente> listaCliente;
    private List<Pedido> listaPedidos;
   
    private ProdutoDao produtoDao = new ProdutoDao();
    @ManagedProperty(value = "#{funcionarioBean}")
    private FuncionarioBean funcionarioBean;

    @ManagedProperty(value = "#{entregaBean}")
    private EntregaBean entregaBean;
    private Pedido pedido;
    private PedidoDao pedidoDao = new PedidoDao();
    private ItemPedidoDao itemPedidoDao = new ItemPedidoDao();
    private Boolean listaItensVazia;

    private Funcionario funcionario;
    private FuncionarioDao funcionarioDao = new FuncionarioDao();

    private ClienteDao clienteDao = new ClienteDao();

    private Long codigoPedidoSalvo;

    private boolean cadastroEntrega;

    public PedidoBean() {
        this.getListaProdutos();
    }

    public void limpar() {
        this.listaItens = new ArrayList<>();
        this.pedido = new Pedido();
        this.cadastroEntrega = false;
    }

    public void adicionar(Produto produto) {
        ItemPedido itemPedido = new ItemPedido();
        int produtoAtual = -1;
        for (int posicao = 0; posicao < listaItens.size() && produtoAtual < 0; posicao++) {
            ItemPedido item = listaItens.get(posicao);
            if (item.getProduto().equals(produto)) {
                produtoAtual = posicao;
            }
        }
        itemPedido.setProduto(produto);

        if (produtoAtual < 0) {
            itemPedido.setQuantidade(1);
            itemPedido.getProduto().getPreco();
            itemPedido.setTotal(itemPedido.getProduto().getPreco());
            listaItens.add(itemPedido);
        } else {
            ItemPedido item = listaItens.get(produtoAtual);
            itemPedido.setQuantidade(item.getQuantidade() + 1);
            itemPedido.setTotal(item.getProduto().getPreco() * itemPedido.getQuantidade());
            listaItens.set(produtoAtual, itemPedido);
        }
        calcular();
        this.getListaItensVazia();
    }

    public void calcular() {
        double valor = 0.0;
        for (ItemPedido listaItens : listaItens) {
            valor += listaItens.getTotal();
        }
        pedido.setTotal(valor);

    }

    public void excluir(ItemPedido itemPedido) {
        listaItens.remove(itemPedido);
        calcular();
        this.getListaItensVazia();

    }
    

    public void inserirPedido() {
        try {
            if (pedido.getTotal() == 0.0) {
                Messages.addGlobalError("Adicione itens ao pedido!");
            } else {
                PedidoDao pedidoDao = new PedidoDao();
                FuncionarioDao funcionarioDao = new FuncionarioDao();

                this.funcionario = funcionarioDao.autenticarLogin(funcionarioBean.getFuncionario().getUsuario(), funcionarioBean.getFuncionario().getSenha());

                
                codigoPedidoSalvo = pedidoDao.salvar(pedido, listaItens, entregaBean.getEntrega(), cadastroEntrega);

                Messages.addGlobalInfo("Salvo com sucesso! Codigo: " + codigoPedidoSalvo);
            }

        } catch (Exception e) {
            Messages.addGlobalError("Não foi salvo: " + e.getMessage());
        }

        limpar();
        this.getListaItensVazia();
    }
    
    public void editarPedido(Pedido pedido){
        
        this.listaItens = itemPedidoDao.buscarItensPedido(pedido.getCodigo());
        calcular();
        this.getListaItensVazia();
    
    }

    public List<Produto> getListaProdutos() {
        return listaProdutos = produtoDao.listarObjetos();
    }

    public void setListaProdutos(List<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    public List<ItemPedido> getListaItens() {
        if (listaItens == null) {
            this.listaItens = new ArrayList<>();
        }
        return listaItens;
    }

    public void setListaItens(List<ItemPedido> listaItens) {
        this.listaItens = listaItens;
    }

    public ProdutoDao getProdutoDao() {
        return produtoDao;
    }

    public void setProdutoDao(ProdutoDao produtoDao) {
        this.produtoDao = produtoDao;
    }

    public Pedido getPedido() {
        if (pedido == null) {
            this.pedido = new Pedido();
        }
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public List<Cliente> getListaCliente() {
        return listaCliente = clienteDao.listarObjetos();
    }

    public void setListaCliente(List<Cliente> listaCliente) {
        this.listaCliente = listaCliente;
    }

    public List<Pedido> getListaPedidos() {
        return listaPedidos = pedidoDao.listarObjetos();
    }

    public void setListaPedidos(List<Pedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }
    
    public Boolean getListaItensVazia() {
        if (listaItens == null || listaItens.isEmpty()) {
            return listaItensVazia = false;
        }
        return listaItensVazia = true;
    }

    public void setListaItensVazia(Boolean listaItensVazia) {
        this.listaItensVazia = listaItensVazia;
    }

    public Funcionario getFuncionario() {
        if (funcionario == null) {
            return funcionario = new Funcionario();
        }
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public FuncionarioBean getFuncionarioBean() {
        return funcionarioBean;
    }

    public void setFuncionarioBean(FuncionarioBean funcionarioBean) {
        this.funcionarioBean = funcionarioBean;
    }

    public Long getCodigoPedidoSalvo() {
        return codigoPedidoSalvo;
    }

    public void setCodigoPedidoSalvo(Long codigoPedidoSalvo) {
        this.codigoPedidoSalvo = codigoPedidoSalvo;
    }

    public boolean getCadastroEntrega() {
        return cadastroEntrega;
    }

    public void setCadastroEntrega(boolean cadastroEntrega) {
        this.cadastroEntrega = cadastroEntrega;
    }

    public EntregaBean getEntregaBean() {
        return entregaBean;
    }

    public void setEntregaBean(EntregaBean entregaBean) {
        this.entregaBean = entregaBean;
    }

}
