package javafxmvc.model.domain;

import java.io.Serializable;

public class Produto implements Serializable {

    private int cdProduto;
    private String nome;
    private double preco;
    private int quantidade;
    private EstadoState estado;
    private Categoria categoria;

    public Produto() {
    }

    public Produto(int cdProduto, String nome, double preco, int quantidade) {
        this.cdProduto = cdProduto;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        if(quantidade > 10)
		{
                            estado = new EstoqueNormal(this);

		} else if(quantidade > 0 )
		{
        estado = new EstoqueCritico(this);
		}else if(quantidade == 0){
                            estado = new EstoqueEmFalta(this);
                }

    }
    public void reestocar(int quantidade)
	{
		estado.reestocar(quantidade);
	}
	
	public void fazerCompra(int quantidade)
	{
		estado.fazerCompra(quantidade);
	}
    	
	public void setEstado(EstadoState estado)
	{
		this.estado = estado;
	}

    public int getCdProduto() {
        return cdProduto;
    }

    public void setCdProduto(int cdProduto) {
        this.cdProduto = cdProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return this.nome;
    }

    public EstadoState getEstado() {
       return this.estado;
    }
    
}
