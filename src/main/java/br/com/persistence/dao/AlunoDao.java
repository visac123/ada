package br.com.persistence.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

import br.com.persistence.dto.AlunoRequest;
import br.com.persistence.dto.AlunoResponse;
import br.com.persistence.mapper.AlunoMapper;
import br.com.persistence.models.Aluno;
import br.com.persistence.models.AlunoRepository;
import br.com.persistence.models.Professor;
import br.com.persistence.models.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestScoped
@Slf4j
@RequiredArgsConstructor
public class AlunoDao {
    
    @Inject
    AlunoRepository AlunoRepository;

    @Inject
    ProfessorRepository professorRepository;

    private final AlunoMapper mapper;


    public List<AlunoResponse> buscaAluno() throws Exception {
        try {
           
            return mapper.toResponse(AlunoRepository.listAll());
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
    public AlunoResponse inserirAluno(AlunoRequest aluno) throws Exception {
        
        Aluno entity = Aluno.builder().nome(aluno.getNome()).build();
        AlunoRepository.persist(entity);

        return mapper.toResponse(entity);
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
    public Aluno mudarTutor(Long id, Long idProf) throws Exception {
        Aluno aluno = AlunoRepository.findById(id);
        Professor prof = professorRepository.findById(idProf);
        aluno.setTutor(prof);

        return aluno;
        
    }

    @Transactional
    public Aluno deletarAluno(Long id) throws Exception {
        Aluno aluno= AlunoRepository.findById(id);
        AlunoRepository.delete(aluno);     
        return aluno;   
    }

  

}
