/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmvc.model.domain;

import javafx.scene.control.Alert;

public class EstoqueNormal extends EstadoState {
	public EstoqueNormal(Produto produto) {
		super(produto);
	}
	//public void fazerCompra(int quantidade)
	//{
    // Alert alert = new Alert(Alert.AlertType.ERROR);
      //          alert.setHeaderText("Problema!");
        //        alert.setContentText("Não existe a quantidade de produtos disponíveis no estoque");
          //      alert.show();	}
            
            
	public void verificarAlteracaoEstado()
	{
		if(this.getProduto().getQuantidade() < 0)
		{
			this.getProduto().setEstado(new EstoqueEmFalta(this.getProduto()));
		} else if(this.getProduto().getQuantidade() <= 10)
		{
			this.getProduto().setEstado(new EstoqueCritico(this.getProduto()));
		}
	}
}