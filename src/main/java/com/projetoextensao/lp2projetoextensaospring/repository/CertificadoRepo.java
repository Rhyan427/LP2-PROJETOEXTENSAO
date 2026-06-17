package com.projetoextensao.lp2projetoextensaospring.repository;

import com.projetoextensao.lp2projetoextensaospring.entity.Certificado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificadoRepo extends JpaRepository<Certificado, String> {

}
