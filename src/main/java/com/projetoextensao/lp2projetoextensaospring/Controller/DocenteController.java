package com.projetoextensao.lp2projetoextensaospring.Controller;

import com.projetoextensao.lp2projetoextensaospring.dataTransfer.DocenteData;
import com.projetoextensao.lp2projetoextensaospring.dataTransfer.OportData;
import com.projetoextensao.lp2projetoextensaospring.entity.*;
import com.projetoextensao.lp2projetoextensaospring.service.DocenteService;
import com.projetoextensao.lp2projetoextensaospring.service.OportunidadeService;
import com.projetoextensao.lp2projetoextensaospring.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.micrometer.observation.autoconfigure.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/docente")
public class DocenteController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private DocenteService docenteService;

    @Autowired
    private OportunidadeService oportunidadeService;

    /**
     * @param dt cria um novo docente recendo os dados do corpo
     * @return o status 201 (Created)
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Docente criarDocente(@RequestBody @Valid DocenteData dt){
        return docenteService.criarDocente(dt);
    }

    /**
     * @param idDocente pega o id do docente responsavel pela Oportunidade na URL e usa para buscar pelo Docente
     * @param idUsuario pega o id do Usuario autor da oportunidade e usa para buscar pelo Usuario
     * @param oportData cria uma Oportunidade recebendo os dados do corpo
     * @return se achar o usuario e docente, retorna 200(OK)
     */
    @PostMapping("/{idDocente}/oportunidade/usuario/{idUsuario}")
    @ResponseStatus(HttpStatus.OK)
    public Oportunidade criarOportunidade(@PathVariable Integer idDocente, @PathVariable Integer idUsuario,
                                          @RequestBody OportData oportData){

        Docente docente = docenteService.buscarPorId(idDocente)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Docente nao encontrado"));

        Usuario usuario = buscarUsuario(idUsuario);

        return docenteService.criarOportunidade(usuario, docente, oportData);
    }


    /**
     * @param idOportunidade recebe o id para buscar por uma oportunidade
     * @param idUsuario recebe o id para buscar por um usuario que serve como autorizante
     * @param status extrai os parametros da requisicao e vincula ao parametro status
     */
    @PostMapping("/oportunidade/{idOportunidade}/publicar/usuario/{idUsuario}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void publicar(@PathVariable Integer idOportunidade, @PathVariable Integer idUsuario,
                         @RequestParam StatusOportunidade status){

        Oportunidade oportunidade = buscarOportunidade(idOportunidade);

        Usuario usuario = buscarUsuario(idUsuario);

        boolean sucesso = docenteService.publicar(oportunidade, usuario, status);

        if(!sucesso){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Nao foi possivel criar a oportunidade. Verifique as permissoes");
        }
    }

    /**
     * @param idOportunidade pega o id da oportunidade da URL
     * @param idUsuario pega o id do usuario da URL
     */
    @PatchMapping("/oportunidade/{idOportunidade}/fechar-inscricoes/usuario/{idUsuario}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void fecharInscricoes(@PathVariable Integer idOportunidade, @PathVariable Integer idUsuario){
        Oportunidade oportunidade = buscarOportunidade(idOportunidade);

        Usuario usuario = buscarUsuario(idUsuario);

        boolean sucesso = docenteService.fecharInscricoes(oportunidade, usuario);
        if(!sucesso){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Nao foi possivel fechar as inscricoes");
        }
    }

    /**
     * metodo que, se os campos forem validos, encerra a Oportunidade
     * @param idOportunidade pega o id da Oportunidade pela URL
     * @param idUsuario pega o id do usuario pela URL
     */
    @PatchMapping("/oportunidade/{idOportunidade}/encerrar/usuario/{idUsuario}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void encerrarOportunidade(@PathVariable Integer idOportunidade, @PathVariable Integer idUsuario){
        Oportunidade oportunidade = buscarOportunidade(idOportunidade);

        Usuario usuario = buscarUsuario(idUsuario);

        boolean sucesso = docenteService.encerrarOportunidade(oportunidade, usuario);
        if(!sucesso){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Nao foi possivel encerrar a Oportunidade.");
        }
    }

    /**
     *
     * @param idOportunidade pega o id da Oportunidade na URL
     * @param idUsuario pega o id do usuario na URL
     * @param novoPlano pega por meio do corpo o novo plano que o usuario deseja salvar
     */
    @PatchMapping("/oportunidade/{idOportunidade}/plano/usuario/{idUsuario}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editarPlano(@PathVariable Integer idOportunidade, @PathVariable Integer idUsuario,
                            @RequestBody String novoPlano){

        Oportunidade oportunidade = buscarOportunidade(idOportunidade);

        Usuario usuario = buscarUsuario(idUsuario);

        boolean sucesso = docenteService.editarPlano(oportunidade, usuario, novoPlano);
        if(!sucesso){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Nao foi possivel editar o plano");
        }
    }

    /**
     * metodo auxiliar para buscar por uma oportunidade atraves de um id
     * @param id recebe o id da URL
     * @return retorna o id e, se nao achar, retorna 404 (not found)
     */
    private Oportunidade buscarOportunidade(Integer id) {
        return oportunidadeService.buscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Oportunidade não encontrada"));
    }

    /**
     * metodo auxiliar para buscar um usuario atraves do id
     * @param id recebe o id da URL
     * @return retorna o id e, se nao achar, retorna 404 (not found)
     */
    private Usuario buscarUsuario(Integer id) {
        return usuarioService.buscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
    }
}
