package org.example.service;

import org.example.entity.Aluno;
import org.example.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository repository;

    public List<Aluno> listaTodos(){
        return repository.findAll();
    }

    public List<Aluno> listaPorNome(String nome){

        return repository.findByNomeContainsIgnoreCase(nome);
    }

    public List<Aluno> listaAlunoDiciplina(Long idDiciplina){
        return repository.listaAlunoDiciplina(idDiciplina);
    }

    public Aluno create(Aluno aluno) {

        return repository.save(aluno);
    }

    public Aluno update(Aluno aluno) {
        return repository.save(aluno);
    }

    public List<Aluno> findAll() {
        return repository.findAll();
    }

    public Optional<Aluno> findById(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
