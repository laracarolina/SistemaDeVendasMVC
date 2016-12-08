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
public abstract class ItemDeVendaDecorator implements ComponentItemDeVenda{
  protected ComponentItemDeVenda cVenda;
  protected double valor;
  public ItemDeVendaDecorator(double valor,ComponentItemDeVenda cVenda){
  	this.valor = valor;
    this.cVenda = cVenda;
  }
public abstract double getValor();

}
