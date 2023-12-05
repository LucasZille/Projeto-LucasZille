package org.example.service;

import org.example.dto.DisciplinasAlunoDTO;
import org.example.dto.HistoricoAlunoDTO;
import org.example.dto.MatriculaAlunoNotasOnlyDTO;
import org.example.entity.RegistroDisciplina;
import org.example.repository.RegistroDisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RegistroDisciplinaService {

    @Autowired
    private RegistroDisciplinaRepository repository;

    static final Double gradesAvgToApprove = 6.0;

    public RegistroDisciplina create(RegistroDisciplina registroDisciplina) {
        return repository.save(registroDisciplina);
    }

    public HistoricoAlunoDTO getHistoricoFromAluno(Long matriculaAlunoId) {
        List<RegistroDisciplina> registroDisciplina = repository.findByMatriculaId(matriculaAlunoId);

        HistoricoAlunoDTO historicoAlunoDTO = new HistoricoAlunoDTO();

        List<DisciplinasAlunoDTO> disciplinasAlunoDTOList = new ArrayList<>();

        if (!registroDisciplina.isEmpty()) {

            registroDisciplina.stream().forEach(d -> {

                historicoAlunoDTO.setCurso(d.getDisciplina().getCurso());
                historicoAlunoDTO.setNomeAluno(d.getMatricula().getAluno().getNome());

                DisciplinasAlunoDTO disciplinasAlunoDTO = new DisciplinasAlunoDTO();

                disciplinasAlunoDTO.setNome(d.getDisciplina().getNome());
                disciplinasAlunoDTO.setNota1(d.getNota1());
                disciplinasAlunoDTO.setNota2(d.getNota2());
                if ((d.getNota1() != null && d.getNota2() != null)) {
                    disciplinasAlunoDTO.setMedia(d.getNota1() + d.getNota2() / 2);
                } else {
                    disciplinasAlunoDTO.setMedia(null);
                }

                disciplinasAlunoDTO.setStatus(d.getStatus());

                disciplinasAlunoDTOList.add(disciplinasAlunoDTO);

            });

            historicoAlunoDTO.setDisciplinasAlunoDtos(disciplinasAlunoDTOList);

            return historicoAlunoDTO;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Esse aluno não tem matrícula.");
    }

    public void updateNotas(MatriculaAlunoNotasOnlyDTO notasOnlyDTO, Long matriculaAlunoId, Long disciplinaId) {
        Optional<RegistroDisciplina> registroDisciplina = Optional.ofNullable(repository.
                findByMatriculaIdAndDisciplinaId(matriculaAlunoId, disciplinaId));

        boolean needUpdate = false;

        if (StringUtils.hasLength(notasOnlyDTO.getNota1().toString())) {
            registroDisciplina.ifPresent(matriculaAluno -> matriculaAluno.setNota1(notasOnlyDTO.getNota1()));
            needUpdate = true;
        }

        if (StringUtils.hasLength(notasOnlyDTO.getNota1().toString())) {
            registroDisciplina.ifPresent(matriculaAluno -> matriculaAluno.setNota2(notasOnlyDTO.getNota2()));
            needUpdate = true;
        }

        if (needUpdate) {
            if (registroDisciplina.get().getNota1() != null && registroDisciplina.get().getNota2() != null) {
                if (registroDisciplina.get().getNota1() + registroDisciplina.get().getNota2() / 2 >= gradesAvgToApprove) {
                    registroDisciplina.ifPresent(r -> r.setStatus("APROVADO"));
                } else {
                    registroDisciplina.ifPresent(matriculaAluno -> matriculaAluno.setStatus("REPROVADO"));
                }

            }
            repository.save(registroDisciplina.get());
        }

    }
}
