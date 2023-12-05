package org.example.controller;

import org.example.dto.HistoricoAlunoDTO;
import org.example.dto.MatriculaAlunoNotasOnlyDTO;
import org.example.entity.RegistroDisciplina;
import org.example.service.RegistroDisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registro-disciplina")
public class RegistroDisciplinaController {

    @Autowired
    RegistroDisciplinaService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<RegistroDisciplina> create(@RequestBody RegistroDisciplina registroDisciplina) {
        RegistroDisciplina entidade = service.create(registroDisciplina);

        return ResponseEntity.status(201).body(entidade);
    }

    @PatchMapping("/update-notas/{matriculaId}/{disciplinaId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public void patchGrades(@RequestBody MatriculaAlunoNotasOnlyDTO notasOnlyDto, @PathVariable Long matriculaId,
                            @PathVariable Long disciplinaId) {
        service.updateNotas(notasOnlyDto, matriculaId, disciplinaId);
    }

    @GetMapping("/estudante-notas/{matriculaId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('DIRETOR','ADMIN')")
    public HistoricoAlunoDTO studentGrades(@PathVariable Long matriculaId) {
        return service.getHistoricoFromAluno(matriculaId);
    }
}
