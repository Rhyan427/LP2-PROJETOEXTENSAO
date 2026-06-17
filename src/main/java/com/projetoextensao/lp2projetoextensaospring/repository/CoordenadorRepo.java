package com.projetoextensao.lp2projetoextensaospring.repository;


import com.projetoextensao.lp2projetoextensaospring.entity.Coordenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordenadorRepo extends JpaRepository<Coordenador, Integer> {
}
