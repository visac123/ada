package br.com.services;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.persistence.dao.DisciplinaDao;
import br.com.persistence.models.Disciplina;


@RequestScoped
public class DisciplinaService {

    @Inject
    DisciplinaDao dao;

    @Transactional(rollbackOn = Exception.class)
    public Disciplina inserirDisciplina(Disciplina disciplina) throws Exception{
        return dao.inserirDisciplina(disciplina);
    }    
}
