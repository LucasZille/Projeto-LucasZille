package org.example.repository;

import org.example.entity.RegistroDisciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistroDisciplinaRepository extends JpaRepository<RegistroDisciplina, Long> {

    RegistroDisciplina findByMatriculaIdAndDisciplinaId(Long matriculaId, Long disciplinaId);

    List<RegistroDisciplina> findByMatriculaId(Long matriculaId);
}
