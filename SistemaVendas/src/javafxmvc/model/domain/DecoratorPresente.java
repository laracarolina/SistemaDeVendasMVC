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
public class DecoratorPresente extends ItemDeVendaDecorator{
public DecoratorPresente(double valor,ComponentItemDeVenda cVenda){
  super(valor,cVenda);
}
@Override
public double getValor(){
	return this.valor+cVenda.getValor();
}

} 
