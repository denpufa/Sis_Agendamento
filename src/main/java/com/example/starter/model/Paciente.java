package com.example.starter.model;

import javax.persistence.*;

@Entity
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomePaciente;
    @Column(unique = true)
    private String carteiraSUS;
    @Column(unique = true)
    private String cpf;

    public Paciente(String nomePaciente, String cpf, String carteiraSUS) {
        this.nomePaciente = nomePaciente;
        this.cpf = cpf;
        this.carteiraSUS = carteiraSUS;
    }

    public Paciente(Paciente paciente) {
        this.id = paciente.getId();
        this.nomePaciente = paciente.getNomePaciente();
        this.cpf = paciente.getCpf();
        this.carteiraSUS = paciente.getCarteiraSUS();
    }

    public Paciente(String cpf, String carteiraSUS) {
        this.cpf = cpf;
        this.carteiraSUS = carteiraSUS;
    }

    public Paciente() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPaciente(Paciente paciente) {
        this.id = paciente.getId();
        this.nomePaciente = paciente.getNomePaciente();
        this.cpf = paciente.getCpf();
        this.carteiraSUS = paciente.getCarteiraSUS();
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public String getCarteiraSUS() {
        return carteiraSUS;
    }

    public void setCarteiraSUS(String carteiraSUS) {
        this.carteiraSUS = carteiraSUS;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
