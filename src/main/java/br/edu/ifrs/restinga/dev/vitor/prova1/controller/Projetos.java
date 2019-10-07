/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.restinga.dev.vitor.prova1.controller;

import br.edu.ifrs.restinga.dev.vitor.prova1.dao.AtividadeDAO;
import br.edu.ifrs.restinga.dev.vitor.prova1.dao.ProjetoDAO;
import br.edu.ifrs.restinga.dev.vitor.prova1.entidade.Atividade;
import br.edu.ifrs.restinga.dev.vitor.prova1.entidade.Projeto;
import br.edu.ifrs.restinga.dev.vitor.prova1.error.ExcecaoProvaNaoEncontrado;
import br.edu.ifrs.restinga.dev.vitor.prova1.error.ExcecaoProvaRN;

import java.util.List;
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
public class Projetos {

     int ativSoma;
     int execSoma;
    
    @Autowired
    ProjetoDAO projetoDAO;

    @Autowired
    AtividadeDAO atividadeDAO;

    public void validaProjeto(Projeto projeto) {

        if (projeto.getNome() == null || projeto.getNome().isEmpty()) {
            throw new ExcecaoProvaRN(3001, "Campo nome é obrigatório");
        }
        if (projeto.getLinguagem() == null || projeto.getLinguagem().isEmpty()) {
            throw new ExcecaoProvaRN(3001, "Campo linguagem é obrigatório");
        }
        if (projeto.getHorasPrevistas() < 0) {
            throw new ExcecaoProvaRN(3001, "Campo horas previstas é obrigatório");
        }

        if (projeto.getHorasExecutadas() != 0) {
            throw new ExcecaoProvaRN(3002, "Campo horas executadas não pode ser modificada pelo cliente");
        }

        if (!projeto.getLinguagem().equals("java") && !projeto.getLinguagem().equals("c")
                && !projeto.getLinguagem().equals("python") && !projeto.getLinguagem().equals("javascript")
                && !projeto.getLinguagem().equals("php") && !projeto.getLinguagem().equals("objective-c")
                && !projeto.getLinguagem().equals("go") && !projeto.getLinguagem().equals("visual basic")
                && !projeto.getLinguagem().equals("kotlin")) {
            throw new ExcecaoProvaRN(3003, "Linguagem só pode aceitar os valores: java, c, python, javascript, php, objective-c, delphi, go, visual basic ou kotlin");

        }

    }

    public void validaAtividade(Atividade atividade) {
        if (atividade.getDescricao() == null || atividade.getDescricao().isEmpty()) {
            throw new ExcecaoProvaRN(4001, "Campo descrição é obrigatório");
        }

        if (atividade.getHorasPrevistas() <= 0) {
            throw new ExcecaoProvaRN(4001, "Campo horas previstas é obrigatório");
        }

    }

    @RequestMapping(path = "/projetos/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Projeto> listar() {
        return projetoDAO.findAll();
    }

    @RequestMapping(path = "/projetos/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Projeto buscar(@PathVariable int id) {
        final Optional<Projeto> findById = projetoDAO.findById(id);
        if (findById.isPresent()) {
            return findById.get();
        } else {
            throw new ExcecaoProvaNaoEncontrado("Projeto não encontrado");
        }
    }

    @RequestMapping(path = "/projetos/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Projeto cadastrar(@RequestBody Projeto projeto) {
        validaProjeto(projeto);
        Projeto projetoBanco = projetoDAO.save(projeto);
        if (!projetoBanco.getLinguagem().equals(projeto.getLinguagem())) {
            throw new ExcecaoProvaRN(3004, "Linguagem não pode ser alterada");
        }

        return projetoBanco;
    }

    @RequestMapping(path = "/projetos/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable int id, @RequestBody Projeto projeto) {
        final Projeto projetoBanco = this.buscar(id);
        validaProjeto(projeto);
        if (!projetoBanco.getLinguagem().equals(projeto.getLinguagem())) {
            throw new ExcecaoProvaRN(3004, "Linguagem não pode ser alterada");
        }
        projetoBanco.setNome(projeto.getNome());
        projetoBanco.setHorasPrevistas(projeto.getHorasPrevistas());
        projetoDAO.save(projetoBanco);

    }

    @RequestMapping(path = "/projetos/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apagar(@PathVariable int id) {
        if (projetoDAO.existsById(id)) {
            projetoDAO.deleteById(id);
        } else {
            throw new ExcecaoProvaNaoEncontrado("ID não encontrada!");
        }

    }

    @RequestMapping(path = "/projetos/{idProjeto}/atividades/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Atividade> listarAtividade(@PathVariable int idProjeto) {
        return this.buscar(idProjeto).getAtividade();
    }

    @RequestMapping(path = "/projetos/{idProjeto}/atividades/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Atividade recuperarAtividade(@PathVariable int idProjeto, @PathVariable int id) {
        Optional<Atividade> findById = atividadeDAO.findById(id);
        if (findById.isPresent()) {
            return findById.get();
        } else {
            throw new ExcecaoProvaNaoEncontrado("Não encontrado");
        }
    }

    @RequestMapping(path = "/projetos/{idProjeto}/atividades/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Atividade cadastrarAtividade(@PathVariable int idProjeto, @RequestBody Atividade atividade) {
        ativSoma = 0;
        Projeto projetoBanco = this.buscar(idProjeto);
        atividade.setId(0);
        validaAtividade(atividade);
        if (atividade.getHorasExecutadas() != 0) {
            throw new ExcecaoProvaRN(4002, "Campo horas previstas não pode ser definidas no cadastro");
        }
        for (Atividade atividadeBanco : projetoBanco.getAtividade()) {
            ativSoma = ativSoma + atividade.getHorasPrevistas();
            if (atividadeBanco.getDescricao().equals(atividade.getDescricao())) {
                throw new ExcecaoProvaRN(4004, "Não pode ter descrição repetida");
            }
        }
        ativSoma = ativSoma + atividade.getHorasPrevistas();
        if (ativSoma > projetoBanco.getHorasPrevistas()) {
            throw new ExcecaoProvaRN(4003, "A soma das horas previstas das atividades não pode exceder as horas previstas do projeto");
        }

        Atividade atividadeBanco = atividadeDAO.save(atividade);
        projetoBanco.getAtividade().add(atividadeBanco);
        projetoDAO.save(projetoBanco);
        return atividadeBanco;
    }

    @RequestMapping(path = "/projetos/{idProjeto}/atividades/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarAtividade(@PathVariable int idProjeto, @PathVariable int id, @RequestBody Atividade atividade) {
        Projeto projetoBanco = this.buscar(idProjeto);
        
        ativSoma = 0;
        execSoma = 0;
     
        if (atividadeDAO.existsById(id)) {
            atividade.setId(id);
            validaAtividade(atividade);
            for (Atividade atividadeBanco : projetoBanco.getAtividade()) {
                if (atividadeBanco.getDescricao().equals(atividade.getDescricao())) {
                    throw new ExcecaoProvaRN(4004, "Não pode ter descrição repetida");
                }
                ativSoma = ativSoma + atividadeBanco.getHorasPrevistas();
                ativSoma = ativSoma + atividade.getHorasPrevistas();
                if (ativSoma > projetoBanco.getHorasPrevistas()) {
                    throw new ExcecaoProvaRN(4003, "A soma das horas previstas das atividades não pode exceder as horas previstas do projeto");
                }
            }
            if(projetoBanco.getAtividade() != null) {
                for(Atividade atividadeBanco : projetoBanco.getAtividade()) {
                    execSoma = execSoma + atividadeBanco.getHorasExecutadas();
                    execSoma = execSoma + atividade.getHorasExecutadas();
                    projetoBanco.setHorasExecutadas(execSoma);
                }
                
            }
            atividadeDAO.save(atividade);
        } else {
            throw new ExcecaoProvaNaoEncontrado("Não encontrado");
        }

    }

    @RequestMapping(path = "/projetos/{idProjeto}/atividades/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apagarAtividade(@PathVariable int idProjeto, @PathVariable int id) {
        Projeto projetoBanco = this.buscar(idProjeto);
        List<Atividade> atividades = projetoBanco.getAtividade();
        for (Atividade atividade : atividades) {
            if (atividade.getId() == id) {
                projetoBanco.getAtividade().remove(atividade);
                projetoDAO.save(projetoBanco);
                return;
            }
        }
        throw new ExcecaoProvaNaoEncontrado("id " + id + " não foi encontrada");

    }

}
