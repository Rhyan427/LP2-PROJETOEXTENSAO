package com.projetoextensao.lp2projetoextensaospring.repository;

import com.projetoextensao.lp2projetoextensaospring.entity.Grupo;
import com.projetoextensao.lp2projetoextensaospring.entity.StatusGrupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrupoRepo extends JpaRepository<Grupo, Integer> {

    boolean existsByNomeOrEmail(String nome, String email);

    List<Grupo> findByStatus(StatusGrupo status);
}
