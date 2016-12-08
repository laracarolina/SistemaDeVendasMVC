/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmvc.model.domain;

/**
 *
 * @author root
 */
public class Desconto10 extends Desconto {

    @Override
    public double processaDesconto(double valor, int qtd) {
      if(qtd==3){
          return valor*0.90;
      }  
      else if(this.getSucessor()!=null){
          getSucessor().processaDesconto(valor,qtd);
      }
      return valor;
    }
    
}
