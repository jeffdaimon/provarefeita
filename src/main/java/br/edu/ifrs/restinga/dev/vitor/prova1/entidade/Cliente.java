/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.restinga.dev.vitor.prova1.entidade;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 *
 * @author 10070235
 */
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String cnpj;
    
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(unique = true)
    private Contato contato;

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
     * @return the cnpj
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * @param cnpj the cnpj to set
     */
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    /**
     * @return the contato
     */
    public Contato getContato() {
        return contato;
    }

    /**
     * @param contato the contato to set
     */
    public void setContato(Contato contato) {
        this.contato = contato;
    }
}
    

