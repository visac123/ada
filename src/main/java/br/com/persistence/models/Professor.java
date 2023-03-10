package br.com.persistence.models;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.arjuna.ats.internal.jdbc.drivers.modifiers.list;

import lombok.AllArgsConstructor;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table(name="adaProfessor", schema="t99t54")
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

   // @NotBlank(message = "Name must be not empty or null")
    @Column(name = "nome")
    private String nome;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tutor")
    private List<Aluno> alunos;

    public void addAluno(Aluno aluno){
        alunos.add(aluno);
    }
}
