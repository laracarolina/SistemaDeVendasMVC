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
public abstract class Desconto {
    private Desconto sucessor;
    public Desconto(){
        
    }
    public void setSucessor(Desconto sucessor){
        this.sucessor = sucessor;
    }
    public Desconto getSucessor(){
        return this.sucessor;
    }
    public abstract double processaDesconto(double valor,int qtd);
    
}
