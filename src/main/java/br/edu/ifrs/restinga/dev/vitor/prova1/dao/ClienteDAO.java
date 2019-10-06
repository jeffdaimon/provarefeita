/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.restinga.dev.vitor.prova1.dao;

import br.edu.ifrs.restinga.dev.vitor.prova1.entidade.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author 10070235
 */
@Repository
public interface ClienteDAO extends CrudRepository<Cliente, Integer>{
    
}
