/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.casadasmarmitas.converter;

import br.com.casadasmarmitas.dao.ClienteDao;
import br.com.casadasmarmitas.modelo.Cliente;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.faces.convert.Converter;

/**
 *
 * @author Tardeli
 */
@FacesConverter("clienteConverter")
public class ClienteConverter implements Converter{

    //usando quando é clicado na caixa de seleção- Monta objeto apartir do ID selecionado
    @Override 
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            Long codigo = Long.parseLong(value);
            ClienteDao clienteDao = new ClienteDao();
            Cliente objeto = clienteDao.buscarObjeto(codigo);
            return objeto;
        } catch (RuntimeException ex) {
            return null;
        }    
    }

    //recebe o objeto e retorna o Id em string
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        try {
            Cliente cliente = (Cliente) value;
            Long codigo = cliente.getCodigo();
            return codigo.toString();
        } catch (RuntimeException ex) {
            return null;
        }
    }
    
}
