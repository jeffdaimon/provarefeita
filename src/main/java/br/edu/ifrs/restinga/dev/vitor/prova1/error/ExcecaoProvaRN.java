package br.edu.ifrs.restinga.dev.vitor.prova1.error;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 10070235
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)

public class ExcecaoProvaRN extends RuntimeException {

    public ExcecaoProvaRN(int codigo, String erro) {

        super(codigo+"-:-"+erro);

    }

}
