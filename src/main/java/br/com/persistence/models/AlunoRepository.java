package br.com.persistence.models;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class AlunoRepository implements PanacheRepository<Aluno> {
    

}
