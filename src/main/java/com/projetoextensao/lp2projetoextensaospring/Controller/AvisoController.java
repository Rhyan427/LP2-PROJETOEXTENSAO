package com.projetoextensao.lp2projetoextensaospring.Controller;

import com.projetoextensao.lp2projetoextensaospring.dataTransfer.AvisoData;
import com.projetoextensao.lp2projetoextensaospring.entity.Aviso;
import com.projetoextensao.lp2projetoextensaospring.entity.Usuario;
import com.projetoextensao.lp2projetoextensaospring.service.AvisoService;
import com.projetoextensao.lp2projetoextensaospring.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/aviso")
public class AvisoController {
    @Autowired
    private AvisoService avisoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Aviso> listarAvisos (){
        return avisoService.listarAvisos();
    };

    @PostMapping("/usuario/{idUsuario}/aviso")
    @ResponseStatus(HttpStatus.OK)
    public void publicarAviso(@RequestBody AvisoData data, @PathVariable Integer idUsuario){
        Usuario user = usuarioService.buscarPorId(idUsuario)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario nao encontrado"));

        Boolean sucesso = avisoService.publicarAviso(user, data);
        if (!sucesso) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Nao foi possivel publicar o aviso, verifique as permissoes");
        }
    }
}
