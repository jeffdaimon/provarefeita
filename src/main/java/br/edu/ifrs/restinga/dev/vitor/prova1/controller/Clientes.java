/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.restinga.dev.vitor.prova1.controller;

import br.edu.ifrs.restinga.dev.vitor.prova1.dao.ClienteDAO;
import br.edu.ifrs.restinga.dev.vitor.prova1.dao.ContatoDAO;
import br.edu.ifrs.restinga.dev.vitor.prova1.entidade.Cliente;
import br.edu.ifrs.restinga.dev.vitor.prova1.error.ExcecaoProvaNaoEncontrado;
import br.edu.ifrs.restinga.dev.vitor.prova1.error.ExcecaoProvaRN;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 10070235
 */
@RestController
@RequestMapping("/api")
public class Clientes {

    @Autowired
    ClienteDAO clienteDAO;
    @Autowired
    ContatoDAO contatoDAO;

    public void validaCliente(Cliente cliente) {
        if (cliente.getNome() == null || cliente.getNome().isEmpty() ) {
            throw new ExcecaoProvaRN(1001, "Campo nome é obrigatório");
        }
        if ( cliente.getCnpj().length() != 14) {
            throw new ExcecaoProvaRN(1002, "Campo cnpj deve ter 14 caracteres");
        }
        
        if (cliente.getContato() == null) {
            throw new ExcecaoProvaRN(1001, "Campo contato é obrigatório");
        }

        if (cliente.getContato().getNome()== null ||cliente.getContato().getNome().isEmpty()) {
            throw new ExcecaoProvaRN(2001, "Campo nome do contato é obrigatório");
        }

        if (cliente.getContato().getEmail()== null ||cliente.getContato().getEmail().isEmpty()) {
            throw new ExcecaoProvaRN(2001, "Campo email do contato é obrigatório");
        }

        if (!cliente.getContato().getEmail().contains("@")) {
            throw new ExcecaoProvaRN(2002, "Deve conter @ no email");
        }

        
    }
    
    

    @RequestMapping(path = "/clientes/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Cliente> listar() {
        return clienteDAO.findAll();
    }

    @RequestMapping(path = "/clientes/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Cliente buscar(@PathVariable int id) {
        final Optional<Cliente> findById = clienteDAO.findById(id);
        if (findById.isPresent()) {
            return findById.get();
        } else {
            throw new ExcecaoProvaNaoEncontrado("Cliente não encontrado");
        }
    }

    @RequestMapping(path = "/clientes/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente cadastrar(@RequestBody Cliente cliente) {
        validaCliente(cliente);
        Cliente clienteBanco = clienteDAO.save(cliente);
        return clienteBanco;
    }


    @RequestMapping(path = "/clientes/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable int id, @RequestBody Cliente cliente) {
        validaCliente(cliente);
        final Cliente clienteBanco = this.buscar(id);
        clienteBanco.setNome(cliente.getNome());
        clienteBanco.setCnpj(cliente.getCnpj());
        clienteBanco.setContato(cliente.getContato());
        clienteDAO.save(clienteBanco);
    }

    @RequestMapping(path = "/clientes/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apagar(@PathVariable int id) {
        if (clienteDAO.existsById(id)) {
            clienteDAO.deleteById(id);
        } else {
            throw new ExcecaoProvaNaoEncontrado("ID não encontrada!");
        }

    }
}
