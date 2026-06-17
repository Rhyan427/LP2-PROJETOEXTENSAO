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
     * @param dt converte o JSON para o objeto DiscenteData para passar para o métod o de criar um Discente
     * @return o discente salvo
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Discente criarDiscente(DiscenteData dt){
        return discenteService.criarDiscente((dt));
    }


    /**
     *
     * @return o métod o de listar todos os discentes no banco de dados com a mensagem de ok
     */
    //todo: Verificar se o status a retornar é OK ou ACCEPTED
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Discente> listarTodos(){
        return discenteService.listarTodos();
    }

    /**
     *
     * @param id pega o ID da URL e converte para um id útil para a busca
     * @return o métod o de buscar por ID e, se achar, retorna a resposta ok e, se não achar, retorna o erro 404
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Discente buscarPorId(@PathVariable Integer id){
        return discenteService.buscarPorId(id).orElse(null);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Discente buscarPorMatricula(String matricula){
        return discenteService.buscarPorMatricula(matricula);
    }
}
