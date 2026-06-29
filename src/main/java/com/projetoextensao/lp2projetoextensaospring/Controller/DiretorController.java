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

    /**
     *
     * @param diretor recebe um json contendo as informacoes do diretor novo a ser criado
     * @return retorna o diretor que foi criado
     */

    @PostMapping("/criar")
    @ResponseStatus(HttpStatus.OK)
    public Diretor criarDiretor(@RequestBody DiretorData diretor){
        return diretorService.criarDiretor(diretor);
    }

    /**
     *
     * @param idDiretor recebe o caminho relativo ao id do diretor a ser pesquisado no banco
     * @return se encontrar, retorna o diretor
     */

    @GetMapping("/{idDiretor}")
    @ResponseStatus(HttpStatus.OK)
    public Diretor buscarDiretor(@PathVariable Integer idDiretor){
        return diretorService.buscarDiretor(idDiretor)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
