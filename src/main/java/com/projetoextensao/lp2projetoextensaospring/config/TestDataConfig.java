package com.projetoextensao.lp2projetoextensaospring.config;

import com.projetoextensao.lp2projetoextensaospring.entity.*;
import com.projetoextensao.lp2projetoextensaospring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

@Component
public class TestDataConfig implements CommandLineRunner {

    @Autowired
    private CursoRepo cursoRepo;
    @Autowired
    private UsuarioRepo usuarioRepo;
    @Autowired
    private CoordenadorRepo coordRepo;
    @Autowired
    private DiscenteRepo discRepo;
    @Autowired
    private OportunidadeRepo oportRepo;
    @Autowired
    private PapelRepo papelRepo;

    @Override
    public void run(String... args) throws Exception {
        // 1. Criar Papéis
        Papel pDiscente = papelRepo.save(new Papel("DISCENTE"));
        Papel pCoord = papelRepo.save(new Papel("COORDENADOR"));

        // 2. Criar Curso
        Curso curso = cursoRepo.save(new Curso("Ciência da Computação", 2026, 1000, 500, "Elegivel"));

        // 3. Criar 3 Coordenadores
        for (int i = 1; i <= 3; i++) {
            Coordenador c = new Coordenador();
            c.setNome("Prof. Coord " + i);
            c.setEmail("coord" + i + "@ufma.br");
            c.setSenha("senha123");
            c.setSiape("SIAPE" + i);
            c.setPapel(pCoord);
            coordRepo.save(c);
        }

        // 4. Criar 3 Discentes
        for (int i = 1; i <= 3; i++) {
            Discente d = new Discente();
            d.setNome("Aluno " + i);
            d.setEmail("aluno" + i + "@ufma.br");
            d.setSenha("senha123");
            d.setMatricula("202500" + i);
            d.setCurso(curso);
            d.setPapel(pDiscente);
            discRepo.save(d);
        }

        // 5. Criar 3 Oportunidades
        var coords = coordRepo.findAll();
        for (int i = 1; i <= 3; i++) {
            Oportunidade o = new Oportunidade();
            o.setTitulo("Oportunidade " + i);
            o.setDescricao("Descrição da Oportunidade " + i);
            o.setTipo(TipoOportunidade.PROJETO);
            o.setModalidade(Modalidade.PRESENCIAL);
            o.setCargaHoraria(20);
            o.setVagas(10);
            o.setInicio(LocalDate.now());
            o.setFim(LocalDate.now().plusMonths(3));
            o.setStatus(StatusOportunidade.RASCUNHO);
            o.setResponsavel(coords.get(0)); // Atribui ao primeiro coordenador
            o.setAutor(coords.get(0));       // Também definido como autor
            o.setPlano("Plano Padrão");
            o.setDataPlanoAtividades(LocalDate.now());
            oportRepo.save(o);
        }
    }
}
