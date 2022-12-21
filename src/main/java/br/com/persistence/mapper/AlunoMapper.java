package br.com.persistence.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import br.com.persistence.dto.AlunoRequest;
import br.com.persistence.dto.AlunoResponse;
import br.com.persistence.models.Aluno;

@ApplicationScoped
public class AlunoMapper {
    public List<AlunoResponse> toResponse(List<Aluno> listaDeAlunos) {

        if (Objects.isNull(listaDeAlunos)) return new ArrayList<>();

        return listaDeAlunos.stream()
        .map(this::toResponse)
        .collect(Collectors.toList());
    }

    public AlunoResponse toResponse(Aluno entity) {

        if (Objects.isNull(entity)) return null;

        return  AlunoResponse.builder()
                    .id(entity.getId())
                    .nome(entity.getNome())
                    .build();
    }

    public Aluno toEntity(AlunoRequest request) {
         if (Objects.isNull(request)) {
             return null;
         } else {
             return Aluno.builder()
                     .nome(request.getNome())
                     .build();
         }
    }
}
