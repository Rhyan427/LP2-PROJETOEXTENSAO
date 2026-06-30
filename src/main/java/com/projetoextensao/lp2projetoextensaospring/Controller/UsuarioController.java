package com.projetoextensao.lp2projetoextensaospring.Controller;

import com.projetoextensao.lp2projetoextensaospring.dataTransfer.LoginData;
import com.projetoextensao.lp2projetoextensaospring.entity.Usuario;
import com.projetoextensao.lp2projetoextensaospring.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PatchMapping("/{idUsuario}/desativar")
    @ResponseStatus(HttpStatus.OK)
    public void desativarConta(@PathVariable Integer idUsuario) {
        Usuario usuario = usuarioService.buscarPorId(idUsuario)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario nao encontrado."));
        usuarioService.desativarConta(usuario);
    }

    @PatchMapping("/{idUsuario}/anonimizar")
    @ResponseStatus(HttpStatus.OK)
    public void anonimizarConta(@PathVariable Integer idUsuario) {
        Usuario usuario = usuarioService.buscarPorId(idUsuario)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario nao encontrado."));
        usuarioService.anonimizarConta(usuario);
    }

    @GetMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public Usuario autenticar(@RequestBody LoginData data) {
        return usuarioService.autenticar(data.getEmail(), data.getSenha());
    }
}
