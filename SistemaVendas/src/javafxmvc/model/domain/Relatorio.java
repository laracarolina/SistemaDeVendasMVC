/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmvc.model.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lara
 */
public class Relatorio {
    private Formato formato;
    
    public void setFormato(Formato formato){
        this.formato = formato;
    }
    
    public Formato getFormato(){
        return this.formato;
    }
    public void gerarRelatorioVendas(String nome){//ArrayList<Venda> lista, String nome, LocalDate data1, LocalDate data2){
        formato.gerarRelatorioVendas(nome);//(lista, nome, data1, data2);
    }
    
    public void gerarRelatorioProdutos(String nome){
        formato.gerarRelatorioProdutos(nome);
    }
    
      public void gerarRelatorioClientes(String nome){
        formato.gerarRelatorioClientes(nome);
    }
    
   
}
