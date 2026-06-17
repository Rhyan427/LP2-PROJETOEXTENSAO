package com.projetoextensao.lp2projetoextensaospring.repository;

import com.projetoextensao.lp2projetoextensaospring.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepo extends JpaRepository<Curso, Integer> {

}
