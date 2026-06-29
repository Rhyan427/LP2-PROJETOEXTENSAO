package com.projetoextensao.lp2projetoextensaospring.Controller;

import com.projetoextensao.lp2projetoextensaospring.dataTransfer.DiretorData;
import com.projetoextensao.lp2projetoextensaospring.entity.Diretor;
import com.projetoextensao.lp2projetoextensaospring.service.DiretorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/diretor")
public class DiretorController {
    @Autowired
    private DiretorService diretorService;

    @PostMapping("/criar")
    @ResponseStatus(HttpStatus.OK)
    public Diretor criarDiretor(@RequestBody DiretorData diretor){
        return diretorService.criarDiretor(diretor);
    }

    @GetMapping("/{idDiretor}")
    @ResponseStatus(HttpStatus.OK)
    public Diretor buscarDiretor(@PathVariable Integer idDiretor){
        return diretorService.buscarDiretor(idDiretor)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
