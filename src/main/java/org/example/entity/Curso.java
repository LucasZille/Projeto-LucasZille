package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SqlResultSetMapping(
        name="AlunosCursosMapping",
        classes={
                @ConstructorResult(
                        targetClass=Aluno.class,
                        columns={
                                @ColumnResult(name="id", type=Long.class),
                                @ColumnResult(name="nome")})})
public class Curso implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer duracao;
    private String area;

}