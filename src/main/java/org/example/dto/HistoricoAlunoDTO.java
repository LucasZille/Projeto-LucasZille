package org.example.dto;

import lombok.Data;
import org.example.entity.Curso;

import java.util.List;

@Data
public class HistoricoAlunoDTO {

    private String nomeAluno;
    private Curso curso;
    private List<DisciplinasAlunoDTO> disciplinasAlunoDtos;
}
