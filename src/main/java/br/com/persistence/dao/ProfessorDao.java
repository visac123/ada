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

import br.com.persistence.models.Professor;
import br.com.persistence.models.ProfessorRepository;

@RequestScoped
public class ProfessorDao {
    

    @PersistenceContext
    EntityManager em;

    @Inject
    ProfessorRepository ProfessorRepository;


    public List<Professor> buscaProfessor() throws Exception {
        try {
           
            return ProfessorRepository.listAll();
        } catch (NoResultException e) {
            return new ArrayList<>();
        } catch (PersistenceException e) {
            throw new Exception(e);
        }         
    }

    public List<Professor> buscaProfessoresEspecificos(String prefixo) throws Exception {
        System.out.println("AAAAAAAAAAAAAAAAAAAA" + ProfessorRepository.list("nome like ?1", prefixo + "%"));
        try {
           
            return ProfessorRepository.list("nome like ?1", prefixo + "%");
        } catch (NoResultException e) {
            return new ArrayList<>();
        } catch (PersistenceException e) {
            throw new Exception(e);
        }         
    }


    @Transactional
    public Professor inserirProfessor(Professor professor) throws Exception {
        
        ProfessorRepository.persist(professor);

        return professor;
    }


    public Professor encontrarProfessorId(Long id) throws Exception {
        Professor professor = ProfessorRepository.findById(id);
        return professor;
    }

    @Transactional
    public Professor mudarProfessor(Long id, String nome) throws Exception {
        Professor professor = ProfessorRepository.findById(id);
        professor.setNome(nome);

        return professor;
        
    }

    @Transactional
    public Professor deletarProfessor(Long id) throws Exception {
        Professor professor= ProfessorRepository.findById(id);
        ProfessorRepository.delete(professor);     
        return professor;   
    }

  

}
