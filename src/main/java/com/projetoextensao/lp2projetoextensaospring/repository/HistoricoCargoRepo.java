package com.projetoextensao.lp2projetoextensaospring.repository;

import com.projetoextensao.lp2projetoextensaospring.entity.CargoGrupo;
import com.projetoextensao.lp2projetoextensaospring.entity.Discente;
import com.projetoextensao.lp2projetoextensaospring.entity.Grupo;
import com.projetoextensao.lp2projetoextensaospring.entity.HistoricoCargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoricoCargoRepo extends JpaRepository<HistoricoCargo, Integer> {
    List<HistoricoCargo> findByGrupoIdAndDataFimIsNull(Integer grupoId);

    List<HistoricoCargo> findAtivosByGrupoAndDiscente(Grupo g, Discente d);

    boolean existsByDiscenteAndCargoAndDataFimIsNull(Discente d, CargoGrupo cg);

    List<HistoricoCargo> findByGrupo(Grupo grupo);

    boolean existsByDiscenteAndGrupoAndDataFimIsNull(Discente discente, Grupo grupo);
}
