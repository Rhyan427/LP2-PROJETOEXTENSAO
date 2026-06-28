package com.projetoextensao.lp2projetoextensaospring.config;

import com.projetoextensao.lp2projetoextensaospring.entity.*;
import com.projetoextensao.lp2projetoextensaospring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class TestDataConfig implements CommandLineRunner {

    @Autowired
    private DiscenteRepo discenteRepo;

    @Autowired
    private CursoRepo cursoRepo;

    @Autowired
    private PapelRepo papelRepo;

    @Override
    public void run(String... args) throws Exception {
        if (discenteRepo.count() == 0) {

            // 1. Criar dados básicos necessários para as FKs (Curso e Papel)
            Curso curso = new Curso();
            curso.setNome("Ciência da Computação");
            curso = cursoRepo.save(curso);

            Papel papel = new Papel();
            papel.setDescricao("DISCENTE");
            papel = papelRepo.save(papel);

            // 2. Criar Discentes
            Discente d1 = new Discente();
            d1.setNome("Ana Silva");
            d1.setEmail("ana@ufma.br");
            d1.setSenha("senha123");
            d1.setAtivo(true);
            d1.setMatricula("20251001");
            d1.setCurso(curso);
            d1.setPapel(papel);

            Discente d2 = new Discente();
            d2.setNome("Carlos Eduardo");
            d2.setEmail("carlos@ufma.br");
            d2.setSenha("senha456");
            d2.setAtivo(true);
            d2.setMatricula("20251002");
            d2.setCurso(curso);
            d2.setPapel(papel);

            discenteRepo.saveAll(Arrays.asList(d1, d2));

            System.out.println("Dados de teste carregados com sucesso!");
        }
    }
}
