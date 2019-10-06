/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.restinga.dev.vitor.prova1.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author 10070235
 */
@Entity
public class Atividade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String descricao;
    private int horasPrevistas;
    private int horasExecutadas;

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
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
    
}
