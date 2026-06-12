package repository;

import entity.Aviso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvisoRepo extends JpaRepository<Aviso, Integer> {

    List<Aviso> findAllByOrderByDataPublicacaoDesc();
}
