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
    @Autowired private DiretorRepo diretorRepo;
    @Autowired private AproveitamentoRepo aproveitamentoRepo;
    @Autowired private AvisoRepo avisoRepo;

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
                c.setAtivo(true);
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
                d.setAtivo(true);
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
                d.setAtivo(true);
                discRepo.save(d);
            }

            // 6. Criar 3 coordenadores
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

            // 7. Criar 3 Avisos
            for (int i = 1; i <= 3; i++) {
                Aviso a = new Aviso();
                a.setTitulo("Aviso " + i);
                a.setMensagem("Mensagem do aviso " + i);
                a.setDataPublicacao(LocalDate.now());

                if(!docentes.isEmpty()){
                    a.setAutor(docentes.get(i-1));
                }

                avisoRepo.save(a); // Salvando no banco
            }

            // 8. Criar 3 Diretores
            var grupos = grupoRepo.findAll();
            Grupo grupoDoDiretor = grupos.isEmpty() ? null : grupos.get(0);

            for (int i = 1; i <= 3; i++) {
                Diretor d = new Diretor();

                d.setNome("Diretor " + i);
                d.setEmail("diretor" + i + "@ufma.br");
                d.setSenha("senha123");
                d.setMatricula("DIR2026" + i);
                d.setCurso(curso);
                d.setPapel(pDiretor);
                d.setAtivo(true);

                if (grupoDoDiretor != null) {
                    d.setGrupo(grupoDoDiretor);
                }
                d.setCargo("Diretor de Projetos " + i);
                d.setDataInicio(LocalDate.now());
                d.setDataFim(LocalDate.now().plusYears(1));

                diretorRepo.save(d);

            }

            // 9. Criar 3 Aproveitamentos
            var discentes = discRepo.findAll();

            for (int i = 1; i <= 3; i++) {
                Aproveitamento a = new Aproveitamento();
                a.setDescricao("Aproveitamento " + i);
                a.setHoras(i * 10);
                a.setStatus(StatusAproveitamento.PENDENTE);

                a.setInstituicao("UUUFMA");
                a.setMotivo_rejeicao("N/A");

                if (!discentes.isEmpty() && discentes.size() >= i) {
                    a.setDiscente(discentes.get(i - 1));
                }

                aproveitamentoRepo.save(a);
            }

            System.out.println("Dados de teste (Coordenadores, Docentes e Discentes) carregados com sucesso!");
        } else {
            System.out.println("Dados já existentes, pulando carga inicial.");
        }
    }
}