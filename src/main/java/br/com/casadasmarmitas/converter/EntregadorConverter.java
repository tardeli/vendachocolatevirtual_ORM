/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.casadasmarmitas.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.faces.convert.Converter;
import br.com.casadasmarmitas.dao.EntregadorDao;
import br.com.casadasmarmitas.modelo.Entregador;

/**
 *
 * @author Tardeli
 */
@FacesConverter("entregadorConverter")
public class EntregadorConverter implements Converter{

    //usando quando é clicado na caixa de seleção- Monta objeto apartir do ID selecionado
    @Override 
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            Long codigo = Long.parseLong(value);
            EntregadorDao entregadorDao = new EntregadorDao();
            Entregador objeto = entregadorDao.buscarObjeto(codigo);
            return objeto;
        } catch (RuntimeException ex) {
            return null;
        }    
    }

    //recebe o objeto e retorna o Id em string
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        try {
            Entregador entregador = (Entregador) value;
            Long codigo = entregador.getCodigo();
            return codigo.toString();
        } catch (RuntimeException ex) {
            return null;
        }
    }
    
}
