package com.projetoextensao.lp2projetoextensaospring.Controller;

import com.projetoextensao.lp2projetoextensaospring.entity.Curso;
import com.projetoextensao.lp2projetoextensaospring.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    /**
     *
     * @param id pega o ID da URL e transforma em uma variavel Java
     * @param novoPPC pega o corpo dos atributos em JSON e converte para o objeto Curso
     * @return pede para o Service atualizar o PPC
     */
    @PostMapping("/{id}/atualizar_ppc")
    public Curso atualizarPPC(@PathVariable Integer id, @RequestBody Curso novoPPC){
        return cursoService.atualizarPPC(id, novoPPC);
    }
}
