package com.example.starter.model;

import javax.persistence.*;
@Entity
public class MobileConta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String carteiraSUS;
    private String senha;

    public MobileConta(String carteiraSUS,String senha){
        this.carteiraSUS = carteiraSUS;
        this.senha = senha;
    }

    public MobileConta(String senha){
        this.senha = senha;
    }
    
    public MobileConta(){}

    public void setMobileConta(MobileConta conta){
        
    }

    public void setCarteiraSUS(String carteiraSUS){
        this.carteiraSUS = carteiraSUS;
    }

    public void setSenha(String senha){
        this.senha = senha;
    }

    public void setMobileConta(MobileConta mobileConta){
        this.senha = mobileConta.getSenha();
        this.carteiraSUS = mobileConta.getCarteiraSUS();
    }

    public String getSenha(){
        return this.senha
    }
    
    public String getCarteiraSUS(){
        return this.carteiraSUS
    }