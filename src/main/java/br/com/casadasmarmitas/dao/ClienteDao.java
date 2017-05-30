package br.com.casadasmarmitas.dao;

import br.com.casadasmarmitas.modelo.Cliente;
import br.com.casadasmarmitas.util.HibernateUtil;
import java.io.Serializable;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Tardeli
 */
public class ClienteDao extends Generic_Dao<Cliente> implements Serializable{
    private Session sessao;   
    
    public Boolean pesquisarCpf(String Cpf){
        sessao = (Session) HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria criteria = sessao.createCriteria(Cliente.class);
            criteria.add(Restrictions.ilike("cpf", Cpf));
            Cliente cliente = (Cliente) criteria.uniqueResult();
            if(cliente==null){
                return false;
            }else{
                return true;
            }         
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally{
            sessao.close();
        } 
    }
}
