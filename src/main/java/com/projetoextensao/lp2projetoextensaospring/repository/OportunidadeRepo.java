package com.projetoextensao.lp2projetoextensaospring.repository;

import com.projetoextensao.lp2projetoextensaospring.entity.Oportunidade;
import com.projetoextensao.lp2projetoextensaospring.entity.StatusOportunidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OportunidadeRepo extends JpaRepository<Oportunidade, Integer> {
    List<Oportunidade> findByStatus(StatusOportunidade status);

}
