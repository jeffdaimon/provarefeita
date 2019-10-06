/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.restinga.dev.vitor.prova1.entidade;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author 10070235
 */
@Entity
public class Projeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String linguagem;
    private int horasPrevistas;
    private int horasExecutadas;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Atividade> atividade;

    @ManyToOne
    private Cliente cliente;
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the linguagem
     */
    public String getLinguagem() {
        return linguagem;
    }

    /**
     * @param linguagem the linguagem to set
     */
    public void setLinguagem(String linguagem) {
        this.linguagem = linguagem;
    }

    /**
     * @return the horasPrevistas
     */
    public int getHorasPrevistas() {
        return horasPrevistas;
    }

    /**
     * @param horasPrevistas the horasPrevistas to set
     */
    public void setHorasPrevistas(int horasPrevistas) {
        this.horasPrevistas = horasPrevistas;
    }

    /**
     * @return the horasExecutadas
     */
    public int getHorasExecutadas() {
        return horasExecutadas;
    }

    /**
     * @param horasExecutadas the horasExecutadas to set
     */
    public void setHorasExecutadas(int horasExecutadas) {
        this.horasExecutadas = horasExecutadas;
    }

    /**
     * @return the atividade
     */
    public List<Atividade> getAtividade() {
        return atividade;
    }

    /**
     * @param atividade the atividade to set
     */
    public void setAtividade(List<Atividade> atividade) {
        this.atividade = atividade;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
