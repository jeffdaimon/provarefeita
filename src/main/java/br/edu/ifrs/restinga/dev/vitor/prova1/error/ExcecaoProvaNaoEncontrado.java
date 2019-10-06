/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.restinga.dev.vitor.prova1.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author 10070235
 */
@ResponseStatus(HttpStatus.NOT_FOUND)

public class ExcecaoProvaNaoEncontrado extends RuntimeException {

    public ExcecaoProvaNaoEncontrado(String mensagem) {

        super("1404-:-"+mensagem);

    }

    

}
