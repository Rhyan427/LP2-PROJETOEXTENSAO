package com.projetoextensao.lp2projetoextensaospring.Controller;

import com.projetoextensao.lp2projetoextensaospring.dataTransfer.DiscenteData;
import com.projetoextensao.lp2projetoextensaospring.entity.Discente;
import com.projetoextensao.lp2projetoextensaospring.service.DiscenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import jakarta.validation.Valid;

import java.util.List;

@Controller
@RequestMapping("/discente")
public class DiscenteController {

    @Autowired
    private DiscenteService discenteService;

    /**
     *
     * @param dt converte o JSON para o objeto DiscenteData para passar para o métod o de criar um Discente
     * @return o discente salvo
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Discente criarDiscente(@RequestBody @Valid DiscenteData dt){
        return discenteService.criarDiscente(dt);
    }


    /**
     * @return o status 200 (OK)
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Discente> listarTodos(){
        return discenteService.listarTodos();
    }

    /**
     * @param id pega o ID da URL e converte para um id útil para a busca
     * @return se achar, retorna 200 (OK) e, se não achar, retorna o erro 404 (not found)
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Discente buscarPorId(@PathVariable Integer id){
        return discenteService.buscarPorId(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
        "Discente nao encontrado"));
    }

    /**
     * @param matricula
     * @return
     */
    @GetMapping("/matricula/{matricula}")
    @ResponseStatus(HttpStatus.OK)
    public Discente buscarPorMatricula(@PathVariable String matricula) {
        Discente discente = discenteService.buscarPorMatricula(matricula);
        if (discente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Discente nao encontrado");
        }
        return discente;
    }
}
