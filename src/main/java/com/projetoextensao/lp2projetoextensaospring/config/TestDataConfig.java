package com.projetoextensao.lp2projetoextensaospring.config;

import com.projetoextensao.lp2projetoextensaospring.entity.*;
import com.projetoextensao.lp2projetoextensaospring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TestDataConfig implements CommandLineRunner {

    @Autowired private CursoRepo cursoRepo;
    @Autowired private CoordenadorRepo coordRepo;
    @Autowired private DocenteRepo docenteRepo;
    @Autowired private DiscenteRepo discRepo;
    @Autowired private OportunidadeRepo oportRepo;
    @Autowired private PapelRepo papelRepo;
    @Autowired private GrupoRepo grupoRepo;

    @Override
    public void run(String... args) throws Exception {
        if (papelRepo.count() == 0) {

            // 1. Criar Papéis
            Papel pDiscente = papelRepo.save(new Papel("DISCENTE"));
            Papel pDocente = papelRepo.save(new Papel("DOCENTE"));
            Papel pCoord = papelRepo.save(new Papel("COORDENADOR"));
            Papel pDiretor = papelRepo.save(new Papel("DIRETOR"));

            // 2. Criar Curso
            Curso curso = cursoRepo.save(new Curso("Ciência da Computação", 2026, 1000, 500, "Elegivel"));

            // 3. Criar 3 Coordenadores
            for (int i = 1; i <= 3; i++) {
                Coordenador c = new Coordenador();
                c.setNome("Prof. Coord " + i);
                c.setEmail("coord" + i + "@ufma.br");
                c.setSenha("senha123");
                c.setSiape("SIAPE-C" + i);
                c.setDepartamento("Departamento de Matemática");
                c.setPapel(pCoord);
                coordRepo.save(c);
            }

            // 4. Criar 3 Docentes
            for (int i = 1; i <= 3; i++) {
                Docente d = new Docente();
                d.setNome("Prof. Docente " + i);
                d.setEmail("docente" + i + "@ufma.br");
                d.setSenha("senha123");
                d.setSiape("SIAPE-D" + i);
                d.setDepartamento("Departamento de Computação");
                d.setPapel(pDocente);
                docenteRepo.save(d);
            }

            var docentes = docenteRepo.findAll();
            Docente docenteResponsavel = docentes.isEmpty() ? null : docentes.get(0);


            if (docenteResponsavel != null) {
                for (int i = 1; i <= 3; i++) {
                    Grupo grupo = new Grupo(
                            "Grupo de Pesquisa " + i,
                            "Descrição detalhada do grupo " + i,
                            "grupo" + i + "@ufma.br",
                            docenteResponsavel
                    );
                    grupoRepo.save(grupo);
                }
            }

            // 5. Criar 3 Discentes
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

            //TODO: criar aproveitamentos predefinidos

            // 6. Criar 3 Oportunidades
            var coords = coordRepo.findAll();
            if (!coords.isEmpty()) {
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
                    o.setResponsavel(coords.get(0));
                    o.setAutor(coords.get(0));
                    o.setPlano("Plano Padrão");
                    o.setDataPlanoAtividades(LocalDate.now());
                    oportRepo.save(o);
                }
            }
            System.out.println("Dados de teste (Coordenadores, Docentes e Discentes) carregados com sucesso!");
        } else {
            System.out.println("Dados já existentes, pulando carga inicial.");
        }
    }
}