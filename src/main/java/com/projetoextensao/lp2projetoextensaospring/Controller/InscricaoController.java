package com.projetoextensao.lp2projetoextensaospring.Controller;

import com.projetoextensao.lp2projetoextensaospring.dataTransfer.InscricaoData;
import com.projetoextensao.lp2projetoextensaospring.entity.Discente;
import com.projetoextensao.lp2projetoextensaospring.entity.Grupo;
import com.projetoextensao.lp2projetoextensaospring.entity.Inscricao;
import com.projetoextensao.lp2projetoextensaospring.entity.StatusInscricao;
import com.projetoextensao.lp2projetoextensaospring.service.DiscenteService;
import com.projetoextensao.lp2projetoextensaospring.service.InscricaoService;
import com.projetoextensao.lp2projetoextensaospring.service.OportunidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/inscricao")
public class InscricaoController {
    @Autowired
    private InscricaoService inscricaoService;

    @Autowired
    private DiscenteService discenteService;

    @Autowired
    private OportunidadeService oportunidadeService;

    @GetMapping("/lista")
    @ResponseStatus(HttpStatus.OK)
    public void verInscricoes() {
        inscricaoService.verInscricoes();
    }

    @GetMapping("/lista/{idDiscente}")
    @ResponseStatus(HttpStatus.OK)
    public void verInscricoesPorDiscente(@PathVariable Integer idDiscente) {
        inscricaoService.verInscricoesPorDiscente(idDiscente);
    }

    @GetMapping("/lista/pendentes")
    @ResponseStatus(HttpStatus.OK)
    public void verInscricoesPendentes() {
        inscricaoService.verInscricoesPendentes();
    }

    @PostMapping("/inscrever")
    @ResponseStatus(HttpStatus.CREATED)
    public Inscricao fazerInscricao(InscricaoData data) {
        return inscricaoService.fazerInscricao(data);
    }

    @PatchMapping("/{idInscricao}/analisar_inscricao")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public boolean analisarInscricao(@PathVariable Integer idInscricao, StatusInscricao status) {
        Inscricao inscricao = inscricaoService.buscarPorId(idInscricao)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Inscricao nao encontrada."));
        return inscricaoService.analisarInscricao(inscricao, status);
    }

    @PatchMapping("/{idDiscente}/cancelar")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public boolean cancelarInscricao(@PathVariable Integer idDiscente, @RequestBody String titulo) {
        Discente discente = discenteService.buscarPorId(idDiscente)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Discente nao encontrado."));
        return inscricaoService.cancelarInscricao(discente, titulo);
    }
}
