package com.projetoextensao.lp2projetoextensaospring.repository;

import com.projetoextensao.lp2projetoextensaospring.entity.Discente;
import com.projetoextensao.lp2projetoextensaospring.entity.Inscricao;
import com.projetoextensao.lp2projetoextensaospring.entity.Oportunidade;
import com.projetoextensao.lp2projetoextensaospring.entity.StatusInscricao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InscricaoRepo extends JpaRepository<Inscricao, Integer> {
    List<Inscricao> findByDiscenteId(Integer discenteId);

    List<Inscricao> findByDiscente(Discente discente);

    List<Inscricao> findByStatus(StatusInscricao status);

    boolean existsByDiscenteAndOportunidade(Discente discente, Oportunidade oportunidade);

    Optional<Inscricao> findByOportunidade_TituloAndDiscente_Nome(String titulo, String nome);

    Optional<Inscricao> findByOportunidade_TituloAndDiscente(String titulo, Discente discente);

    Optional<Inscricao> findByOportunidadeAndDiscente(Oportunidade op, Discente discente);
}
