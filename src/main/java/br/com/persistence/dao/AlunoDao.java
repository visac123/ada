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

import br.com.persistence.models.Aluno;
import br.com.persistence.models.AlunoRepository;

@RequestScoped
public class AlunoDao {
    

    @PersistenceContext
    EntityManager em;

    @Inject
    AlunoRepository AlunoRepository;


    public List<Aluno> buscaAluno() throws Exception {
        try {
           
            return AlunoRepository.listAll();
        } catch (NoResultException e) {
            return new ArrayList<>();
        } catch (PersistenceException e) {
            throw new Exception(e);
        }         
    }

    public List<Aluno> buscaAlunosEspecificos(String prefixo) throws Exception {
        try {
           
            return AlunoRepository.list("nome like ?1", prefixo + "%");
        } catch (NoResultException e) {
            return new ArrayList<>();
        } catch (PersistenceException e) {
            throw new Exception(e);
        }         
    }


    @Transactional
    public Aluno inserirAluno(Aluno aluno) throws Exception {
        
        AlunoRepository.persist(aluno);

        return aluno;
    }

   
    public Aluno encontrarAlunoId(Long id) throws Exception {
        Aluno aluno = AlunoRepository.findById(id);
        return aluno;
    }

    @Transactional
    public Aluno mudarAluno(Long id, String nome) throws Exception {
        Aluno aluno = AlunoRepository.findById(id);
        aluno.setNome(nome);

        return aluno;
        
    }

    @Transactional
    public Aluno deletarAluno(Long id) throws Exception {
        Aluno aluno= AlunoRepository.findById(id);
        AlunoRepository.delete(aluno);     
        return aluno;   
    }

  

}
