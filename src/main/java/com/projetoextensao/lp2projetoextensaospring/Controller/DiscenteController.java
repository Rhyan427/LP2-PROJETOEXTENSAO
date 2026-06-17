package com.projetoextensao.lp2projetoextensaospring.Controller;

import com.projetoextensao.lp2projetoextensaospring.dataTransfer.DiscenteData;
import com.projetoextensao.lp2projetoextensaospring.entity.Discente;
import com.projetoextensao.lp2projetoextensaospring.service.DiscenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/discente")
public class DiscenteController {

    @Autowired
    private DiscenteService discenteService;

    /**
     *
     * @param dt converte o JSON para o objeto DiscenteData para passar para o método de criar um Discente
     * @return o discente salvo
     */
    @PostMapping
    public ResponseEntity<Discente> criarDiscente(@RequestBody DiscenteData dt){
        Discente novoDiscente = discenteService.criarDiscente(dt);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoDiscente);
    }


    /**
     *
     * @return o métod o de listar todos os discentes no banco de dados com a mensagem de ok
     */
    @GetMapping
    public ResponseEntity<List<Discente>> listarTodos(){
        return ResponseEntity.ok(discenteService.listarTodos());
    }

    /**
     *
     * @param id pega o ID da URL e converte para um id útil para a busca
     * @return o métod o de buscar por ID e, se achar, retorna a resposta ok e, se não achar, retorna o erro 404
     */
    @GetMapping("/{id}")
    public ResponseEntity<Discente> buscarPorId(@PathVariable Integer id){
        return discenteService.buscarPorId(id)
                .map(d -> ResponseEntity.ok().body(d))
                .orElse(ResponseEntity.notFound().build());
    }

}
