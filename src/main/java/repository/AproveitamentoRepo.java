package repository;

import entity.Aproveitamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AproveitamentoRepo extends JpaRepository<Aproveitamento, Integer> {
}
