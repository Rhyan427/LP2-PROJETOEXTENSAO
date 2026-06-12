package repository;

import entity.Diretor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiretorRepo extends JpaRepository<Diretor, Integer> {
}
