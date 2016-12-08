/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmvc.model.domain;

public abstract class EstadoState {
	private Produto produto;
	
	public EstadoState(Produto produto)
	{
		this.produto = produto;
	}
	
	public void reestocar(int quantidade)
	{
		produto.setQuantidade(produto.getQuantidade() + quantidade);
		this.verificarAlteracaoEstado();
	}
	
	public void fazerCompra(int quantidade)
	{
		//produto.setQuantidade(produto.getQuantidade() - quantidade);
		this.verificarAlteracaoEstado();
	}
	
	public Produto getProduto()
	{
		return produto;
	}
	
	public abstract void verificarAlteracaoEstado();
}