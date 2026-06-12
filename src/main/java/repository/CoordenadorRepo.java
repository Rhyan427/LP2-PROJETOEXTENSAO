package repository;


import entity.Coordenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordenadorRepo extends JpaRepository<Coordenador, Integer> {
}
