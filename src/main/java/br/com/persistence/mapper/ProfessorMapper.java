package br.com.persistence.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import br.com.persistence.dto.ProfessorRequest;
import br.com.persistence.dto.ProfessorResponse;
import br.com.persistence.models.Professor;

@ApplicationScoped
public class ProfessorMapper {
    public List<ProfessorResponse> toResponse(List<Professor> listaDeProfessores) {

        if (Objects.isNull(listaDeProfessores)) return new ArrayList<>();

        return listaDeProfessores.stream()
        .map(this::toResponse)
        .collect(Collectors.toList());
    }

    public ProfessorResponse toResponse(Professor entity) {

        if (Objects.isNull(entity)) return null;

        return  ProfessorResponse.builder()
                    .id(entity.getId())
                    .nome(entity.getNome())
                    .build();
    }

    public Professor toEntity(ProfessorRequest request) {
         if (Objects.isNull(request)) {
             return null;
         } else {
             return Professor.builder()
                     .nome(request.getNome())
                     .build();
         }
    }
}
