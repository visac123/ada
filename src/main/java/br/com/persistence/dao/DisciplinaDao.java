package br.com.persistence.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

import br.com.persistence.models.Disciplina;
import br.com.persistence.models.DisciplinaRepository;

@RequestScoped
public class DisciplinaDao {
    

    @PersistenceContext
    EntityManager em;

    @Inject
    DisciplinaRepository DisciplinaRepository;


    public List<Disciplina> buscaDisciplina() throws Exception {
        try {
           
            return DisciplinaRepository.listAll();
        } catch (NoResultException e) {
            return new ArrayList<>();
        } catch (PersistenceException e) {
            throw new Exception(e);
        }         
    }

    public List<Disciplina> buscaDisciplinasEspecificos(String prefixo) throws Exception {
        try {
           
            return DisciplinaRepository.list("nome like ?1", prefixo + "%");
        } catch (NoResultException e) {
            return new ArrayList<>();
        } catch (PersistenceException e) {
            throw new Exception(e);
        }         
    }


    @Transactional
    public Disciplina inserirDisciplina(Disciplina disciplina) throws Exception {
        
        DisciplinaRepository.persistAndFlush(disciplina);

        return disciplina;
    }

    
    public Disciplina encontrarDisciplinaId(Long id) throws Exception {
        Disciplina disciplina = DisciplinaRepository.findById(id);
        return disciplina;
    }

    @Transactional
    public Disciplina mudarDisciplina(Long id, String nome) throws Exception {
        Disciplina disciplina = DisciplinaRepository.findById(id);
        disciplina.setNome(nome);

        return disciplina;
        
    }

    @Transactional
    public Disciplina deletarDisciplina(Long id) throws Exception {
        Disciplina disciplina= DisciplinaRepository.findById(id);
        DisciplinaRepository.delete(disciplina);     
        return disciplina;   
    }

  

}
