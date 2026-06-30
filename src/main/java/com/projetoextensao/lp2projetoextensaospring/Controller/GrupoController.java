package com.projetoextensao.lp2projetoextensaospring.Controller;

import com.projetoextensao.lp2projetoextensaospring.dataTransfer.GrupoData;
import com.projetoextensao.lp2projetoextensaospring.entity.*;
import com.projetoextensao.lp2projetoextensaospring.service.DiretorService;
import com.projetoextensao.lp2projetoextensaospring.service.DiscenteService;
import com.projetoextensao.lp2projetoextensaospring.service.DocenteService;
import com.projetoextensao.lp2projetoextensaospring.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/grupo")
public class GrupoController {
    @Autowired
    private GrupoService grupoService;
    @Autowired
    private DiscenteService discenteService;
    @Autowired
    private DocenteService docenteService;
    @Autowired
    private DiretorService diretorService;

    @PostMapping("/criar")
    @ResponseStatus(HttpStatus.CREATED)
    public Grupo criarGrupo(GrupoData data) {
        return grupoService.criarGrupo(data);
    }

    @PostMapping("/solicitar")
    @ResponseStatus(HttpStatus.CREATED)
    public void solicitarGrupo(GrupoData data) {
        grupoService.solicitarGrupo(data);
    }

    @PatchMapping("/{idGrupo}/avaliar_solicitacao")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public boolean avaliarSolicitacao(@PathVariable Integer idGrupo, boolean pass) {
        Grupo grupo = grupoService.buscarPorId(idGrupo)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Grupo nao encontrado."));
        return grupoService.avaliarSolicitacao(grupo, pass);
    }

    @GetMapping("/lista")
    @ResponseStatus(HttpStatus.OK)
    public List<Grupo> listarGrupos() {
        return grupoService.listarGrupos();
    }

    @GetMapping("/lista_pendentes")
    @ResponseStatus(HttpStatus.OK)
    public List<Grupo> listarGruposPendentes() {
        return grupoService.listarGruposPendentes();
    }

    @PatchMapping("/{idGrupo}/adicionar/discente/{idDiscente}")
    @ResponseStatus(HttpStatus.OK)
    public boolean adicionarMembros(@PathVariable Integer idDiscente, @PathVariable Integer idGrupo) {
        Discente discente = discenteService.buscarPorId(idDiscente)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Discente nao encontrado."));
        Grupo grupo = grupoService.buscarPorId(idGrupo)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Grupo nao encontrado."));

        return grupoService.adicionarMembros(discente, grupo);
    }

    @PatchMapping("/{idGrupo}/remover/discente/{idDiscente}")
    @ResponseStatus(HttpStatus.OK)
    public boolean removerMembros(@PathVariable Integer idDiscente, @PathVariable Integer idGrupo) {
        Discente discente = discenteService.buscarPorId(idDiscente)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Discente nao encontrado."));
        Grupo grupo = grupoService.buscarPorId(idGrupo)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Grupo nao encontrado."));

        return grupoService.removerMembros(discente, grupo);
    }

    @PatchMapping("/{idGrupo}/{idDocente}/atribuir_cargo/{idDiscente}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public boolean atribuirCargos(@PathVariable Integer idDocente,
                                  @PathVariable Integer idGrupo,
                                  @PathVariable Integer idDiscente,
                                  @RequestBody CargoGrupo cargo) {
        Docente docente = docenteService.buscarPorId(idDocente)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Docente nao encontrado."));
        Discente discente = discenteService.buscarPorId(idDiscente)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Discente nao encontrado."));
        Grupo grupo = grupoService.buscarPorId(idGrupo)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Grupo nao encontrado."));
        return grupoService.atribuirCargo(docente, grupo, discente, cargo);
    }

    @GetMapping("/{idGrupo}/cargos")
    @ResponseStatus(HttpStatus.OK)
    public List<HistoricoCargo> buscarHistorico(@PathVariable Integer idGrupo) {
        Grupo grupo = grupoService.buscarPorId(idGrupo)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Grupo nao encontrado."));
        return grupoService.buscarHistorico(grupo);
    }



}
