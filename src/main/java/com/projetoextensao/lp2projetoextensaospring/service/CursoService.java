package com.projetoextensao.lp2projetoextensaospring.service;

import com.projetoextensao.lp2projetoextensaospring.entity.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projetoextensao.lp2projetoextensaospring.repository.CursoRepo;

@Service
public class CursoService {

    @Autowired
    private CursoRepo cursoRepo;

    /**
     *
     * @param idCurso recebe o ID do curso que quer modificar o PPC
     * @param novoPPC recebe o curso que terá os novos dados do PPC
     * @return salva o novo curso no banco de dados
     */
    public Curso atualizarPPC(Integer idCurso, Curso novoPPC){
        Curso cursoAntigo = cursoRepo.findById(idCurso).orElseThrow(() -> new RuntimeException());

        cursoAntigo.setStatusPPC("DESATUALIZADO");
        cursoRepo.save(cursoAntigo);

        Curso novoCurso = new Curso();
        novoCurso.setNome(cursoAntigo.getNome());

        novoCurso.setAnoPPC(novoPPC.getAnoPPC());
        novoCurso.setCargaHorariaTotal(novoPPC.getCargaHorariaTotal());
        novoCurso.setCargaHorariaExtensao(novoPPC.getCargaHorariaExtensao());
        novoCurso.setStatusPPC("VIGENTE");

        return cursoRepo.save(novoCurso);
    }
}
