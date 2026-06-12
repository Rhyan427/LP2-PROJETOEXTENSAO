package repository;

import entity.Oportunidade;
import entity.StatusOportunidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OportunidadeRepo extends JpaRepository<Oportunidade, Integer> {
    List<Oportunidade> findByStatus(StatusOportunidade status);

}
