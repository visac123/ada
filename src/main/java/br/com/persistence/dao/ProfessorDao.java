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

import br.com.persistence.dto.ProfessorRequest;
import br.com.persistence.dto.ProfessorResponse;
import br.com.persistence.mapper.AlunoMapper;
import br.com.persistence.mapper.ProfessorMapper;
import br.com.persistence.models.Professor;
import br.com.persistence.models.ProfessorRepository;
import br.com.persistence.models.Aluno;
import br.com.persistence.models.AlunoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestScoped
@Slf4j
@RequiredArgsConstructor
public class ProfessorDao {
    
    @Inject
    AlunoRepository alunoRepository;

    @Inject
    ProfessorRepository ProfessorRepository;

    private final ProfessorMapper mapper;


    public List<ProfessorResponse> buscaProfessor() throws Exception {
        try {
           
            return mapper.toResponse(ProfessorRepository.listAll());
        } catch (NoResultException e) {
            return new ArrayList<>();
        } catch (PersistenceException e) {
            throw new Exception(e);
        }         
    }

    public List<Professor> buscaProfessoresEspecificos(String prefixo) throws Exception {
        try {
           
            return ProfessorRepository.list("nome like ?1", prefixo + "%");
        } catch (NoResultException e) {
            return new ArrayList<>();
        } catch (PersistenceException e) {
            throw new Exception(e);
        }         
    }


    @Transactional
    public ProfessorResponse inserirProfessor(ProfessorRequest professor) throws Exception {
        
        Professor entity = Professor.builder().nome(professor.getNome()).build();
        ProfessorRepository.persist(entity);

        return mapper.toResponse(entity);
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
    public Professor mudarTutorando(Long id, Long idAluno) throws Exception {
        Professor professor = ProfessorRepository.findById(id);
        Aluno aluno = alunoRepository.findById(idAluno);
        professor.addAluno(aluno);
        return professor;
        
    }

    @Transactional
    public Professor deletarProfessor(Long id) throws Exception {
        Professor professor= ProfessorRepository.findById(id);
        ProfessorRepository.delete(professor);     
        return professor;   
    }
  

}
