package com.projetoextensao.lp2projetoextensaospring.repository;

import com.projetoextensao.lp2projetoextensaospring.entity.Discente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscenteRepo extends JpaRepository<Discente, Integer> {
    Optional<Discente> findByMatricula(String matricula);
}
