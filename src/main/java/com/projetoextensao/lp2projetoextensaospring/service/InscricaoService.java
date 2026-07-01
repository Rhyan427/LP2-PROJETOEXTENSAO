package com.projetoextensao.lp2projetoextensaospring.service;

import com.projetoextensao.lp2projetoextensaospring.dataTransfer.InscricaoData;
import com.projetoextensao.lp2projetoextensaospring.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.projetoextensao.lp2projetoextensaospring.repository.InscricaoRepo;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class InscricaoService {

    @Autowired
    private InscricaoRepo repository;

    public void verInscricoes() {
        List<Inscricao> inscricoes = repository.findAll();
        for (Inscricao ins : inscricoes) {
            System.out.printf("%s %s, solicitada por %s. Status: %s", ins.getOportunidade().getTipo(), ins.getOportunidade().getTitulo(), ins.getDiscente().getNome(), ins.getStatus());
        }
    }

    public List<Inscricao> verInscricoesPorDiscente(Discente di) {
        List<Inscricao> inscricoes = repository.findByDiscente(di);
        for (Inscricao ins : inscricoes) {
            if (Objects.equals(ins.getDiscente(), di)) {
                System.out.printf("- %s %s. Status: %s\n", ins.getOportunidade().getTipo(), ins.getOportunidade().getTitulo(), ins.getStatus());
            }
        }
        return inscricoes;
    }

    public void verInscricoesPendentes() {
        List<Inscricao> pendentes = repository.findByStatus(StatusInscricao.PENDENTE);
        for (Inscricao ins : pendentes) {
            if (ins.getStatus() == StatusInscricao.PENDENTE) {
                System.out.printf("- %s %s. Status: %s. Autor: %s\n", ins.getOportunidade().getTipo(), ins.getOportunidade().getTitulo(), ins.getStatus(), ins.getDiscente().getNome());
            }
        }
    }

    public Inscricao fazerInscricao(Discente di, Oportunidade oportunidade) {
        if (!inscricaoExiste(di, oportunidade) && oportunidade.getStatus() == StatusOportunidade.PUBLICADA) {
            Inscricao inscricao = new Inscricao(di, oportunidade, StatusInscricao.PENDENTE, LocalDate.now());

            return repository.save(inscricao);
        }
        return null;
    }

    public boolean inscricaoExiste(Discente di, Oportunidade oportunidade) {
        return repository.existsByDiscenteAndOportunidade(di, oportunidade);
    }

    public boolean analisarInscricao(Inscricao ins, StatusInscricao status) {
        if (ins != null) {
            ins.setStatus(status);
            repository.save(ins);
            return true;
        }
        return false;
    }

    public boolean cancelarInscricao(Discente di, String titulo) {
        Optional<Inscricao> optionalInscricao = repository.findByOportunidade_TituloAndDiscente(titulo, di);
        if(optionalInscricao.isPresent()){
            Inscricao inscricao = optionalInscricao.get();
            if(inscricao.getOportunidade().getStatus() == StatusOportunidade.PUBLICADA &&
                    (inscricao.getStatus() == StatusInscricao.APROVADO || inscricao.getStatus() == StatusInscricao.PENDENTE)){
                return true;
            }
        }
        return false;
    }


    public Optional<Inscricao> buscarPorId(Integer idInscricao) {
        return repository.findById(idInscricao);
    }
}
