package repository;

import entity.Certificado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificadoRepo extends JpaRepository<Certificado, String> {

}
