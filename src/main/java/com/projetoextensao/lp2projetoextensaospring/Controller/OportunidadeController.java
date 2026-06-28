package com.projetoextensao.lp2projetoextensaospring.Controller;

import com.projetoextensao.lp2projetoextensaospring.dataTransfer.OportData;
import com.projetoextensao.lp2projetoextensaospring.entity.Oportunidade;
import com.projetoextensao.lp2projetoextensaospring.service.OportunidadeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/oportunidades")
public class OportunidadeController {

    @Autowired
    private OportunidadeService oportunidadeService;

    // Rota: POST /oportunidades
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Oportunidade criarOportunidade(@RequestBody @Valid OportData dt) {

        return oportunidadeService.criarOportunidade(dt.getAutor(), dt.getResponsavel(), dt);
    }

    // Rota: GET /oportunidades/{id}
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Oportunidade> buscarPorId(@PathVariable Integer id) {
        return oportunidadeService.buscarPorId(id);
    }
}