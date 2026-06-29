package com.projetoextensao.lp2projetoextensaospring.Controller;

import com.projetoextensao.lp2projetoextensaospring.dataTransfer.AprovtData;
import com.projetoextensao.lp2projetoextensaospring.entity.Aproveitamento;
import com.projetoextensao.lp2projetoextensaospring.entity.Discente;
import com.projetoextensao.lp2projetoextensaospring.service.AproveitamentoService;
import com.projetoextensao.lp2projetoextensaospring.service.DiscenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/aproveitamento")
public class AproveitamentoController {
    @Autowired
    private AproveitamentoService aproveitamentoService;

    @Autowired
    private DiscenteService discenteService;

    @PostMapping("/criar")
    @ResponseStatus(HttpStatus.OK)
    public Aproveitamento criarAproveitamento(@RequestBody AprovtData aproveitamento){
        return aproveitamentoService.criarAproveitamento(aproveitamento);
    }

    @GetMapping("/{idDiscente}")
    @ResponseStatus(HttpStatus.OK)
    public Integer calcularHorasAprovadas(@PathVariable Integer idDiscente){
        List<Aproveitamento> todosOsAproveitamentos = aproveitamentoService.listarTodos();
        Discente discente = discenteService.buscarPorId(idDiscente)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nao foi possivel encontrar o discente"));

        return aproveitamentoService.calcularHorasAprovadas(discente, todosOsAproveitamentos);
    }

    @PatchMapping("/{idAproveitamento}/indeferir")
    @ResponseStatus(HttpStatus.OK)
    public void indeferir (@PathVariable Integer idAproveitamento, @RequestBody String motivo){
        Aproveitamento aproveitamento = aproveitamentoService.buscarPorId(idAproveitamento)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nao foi possivel achar o aproveitamento"));

        Boolean sucesso = aproveitamentoService.indeferir(aproveitamento, motivo);
        if (!sucesso){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Permissao negada");
        }
    }

    @PatchMapping("/{idAproveitamento}/reenviar")
    @ResponseStatus(HttpStatus.OK)
    public void reenviar(@PathVariable Integer idAproveitamento, @RequestBody String novaDescricao, @RequestBody Integer novasHoras){
        Aproveitamento aproveitamento = aproveitamentoService.buscarPorId(idAproveitamento)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nao foi possivel achar o aproveitamento"));

        Boolean sucesso = aproveitamentoService.reenviar(aproveitamento, novaDescricao, novasHoras);

        if (!sucesso){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Permissao negada");
        }
    }

}
