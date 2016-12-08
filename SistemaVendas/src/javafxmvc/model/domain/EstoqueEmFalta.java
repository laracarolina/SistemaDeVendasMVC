/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmvc.model.domain;

import javafx.scene.control.Alert;

public class EstoqueEmFalta extends EstadoState {
	
	public EstoqueEmFalta(Produto produto){
		super(produto);
	}
	
	public void fazerCompra(int quantidade)
	{
     	}
	
	public void verificarAlteracaoEstado()
	{
		if(this.getProduto().getQuantidade() > 10)
		{
			this.getProduto().setEstado(new EstoqueNormal(this.getProduto()));
		} else if(this.getProduto().getQuantidade() > 0)
		{
			this.getProduto().setEstado(new EstoqueCritico(this.getProduto()));
		}
	}
}
