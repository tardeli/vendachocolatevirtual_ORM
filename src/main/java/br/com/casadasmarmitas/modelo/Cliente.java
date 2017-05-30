/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.casadasmarmitas.modelo;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 *
 * @author Tardeli
 */
@Entity
@Table(name = "Cliente")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Cliente extends PessoaFisica{
    @Override
    public String toString(){
        String texto = "";
        texto+= "Codigo: "+this.getCodigo()+"\n";
        texto+= "Nome: "+this.getNome()+"\n";
        texto+= "Cpf: "+this.getCpf()+"\n";
        texto+= "Telefone: "+this.getTelefone()+"\n";
        texto+= "Email: "+this.getEmail()+"\n";
        texto+= "Logradouro: "+this.getLogradouro()+"\n";
        texto+= "Bairro: "+this.getBairro()+"\n";
        texto+= "Ponto de Referencia: "+this.getPontoReferencia()+"\n";
        texto+= "cidade: "+this.getCidade()+"\n";
        texto+= "Uf: "+this.getUf();
        return texto;
    } 
}
