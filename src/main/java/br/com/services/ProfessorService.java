package br.com.services;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.persistence.dao.ProfessorDao;
import br.com.persistence.dto.ProfessorRequest;
import br.com.persistence.dto.ProfessorResponse;
import br.com.persistence.models.Professor;


@RequestScoped
public class ProfessorService {

    @Inject
    ProfessorDao dao;

    @Transactional(rollbackOn = Exception.class)
    public ProfessorResponse inserirProfessor(ProfessorRequest professor) throws Exception{
        return dao.inserirProfessor(professor);
    }    
}
