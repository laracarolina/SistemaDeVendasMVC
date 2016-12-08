/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmvc.model.domain;

import javafx.scene.control.Alert;

public class EstoqueCritico extends EstadoState {
	
	public EstoqueCritico(Produto produto)
	{
		super(produto);
	}
	
	public void fazerCompra(int quantidade)
	{
		if(quantidade > 2)
		{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Estoque crítico!");
                alert.setContentText("Não se pode comprar mais de 2 unidades");
                alert.show();
			
		} else{
			super.fazerCompra(quantidade);
		}
	}
	
	public void verificarAlteracaoEstado()
	{
		if(this.getProduto().getQuantidade() > 10)
		{
			this.getProduto().setEstado(new EstoqueNormal(this.getProduto()));
		} else if(this.getProduto().getQuantidade() <= 0)
		{
			this.getProduto().setEstado(new EstoqueEmFalta(this.getProduto()));
		}
	}
}