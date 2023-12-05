package org.example.service;

import org.example.entity.Disciplina;
import org.example.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaService {

    @Autowired
    DisciplinaRepository repository;

    public Disciplina create(Disciplina disciplina) {
        return repository.save(disciplina);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
    public List<Disciplina> getByProfessorId(Long professorId) {

        return repository.findByProfessorId(professorId);

    }
}