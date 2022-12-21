package br.com.services;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.persistence.dao.AlunoDao;
import br.com.persistence.dto.AlunoRequest;
import br.com.persistence.dto.AlunoResponse;
import br.com.persistence.models.Aluno;


@RequestScoped
public class AlunoService {

    @Inject
    AlunoDao dao;

    @Transactional(rollbackOn = Exception.class)
    public AlunoResponse inserirAluno(AlunoRequest aluno) throws Exception{
        return dao.inserirAluno(aluno);
    }    
}
