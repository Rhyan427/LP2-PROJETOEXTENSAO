package com.projetoextensao.lp2projetoextensaospring.Controller;


import com.projetoextensao.lp2projetoextensaospring.dataTransfer.CoordData;
import com.projetoextensao.lp2projetoextensaospring.dataTransfer.OportData;
import com.projetoextensao.lp2projetoextensaospring.entity.*;
import com.projetoextensao.lp2projetoextensaospring.service.CoordenadorService;
import com.projetoextensao.lp2projetoextensaospring.service.DocenteService;
import com.projetoextensao.lp2projetoextensaospring.service.OportunidadeService;
import com.projetoextensao.lp2projetoextensaospring.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/coordenador")
public class CoordenadorController {

    @Autowired
    private CoordenadorService coordenadorService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private DocenteService docenteService;

    @Autowired
    private OportunidadeService oportunidadeService;

    /**
     *
     * @param data recebe o corpo e transforma no objeto DTO
     * @return o status 201 (Created)
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Coordenador criarCoordenador(@RequestBody CoordData data){
        return coordenadorService.criarCoordenador(data);
    }

    /**
     *
     * @param idUsuario pega o id do usuario autor da oportunidade pela URL
     * @param idDocente pega o id do docente responsavel pela oportunidade pela URL
     * @param idCoordenador pega o id do coordenador fazendo a requisicao pela URL
     * @param data requisita o corpo e transforma num DTO
     * @return 201 (CREATED) se conseguir criar
     */
    @PostMapping("/{idCoordenador}/oportunidade/usuario/{idUsuario}/docente/{idDocente}")
    @ResponseStatus(HttpStatus.CREATED)
    public Oportunidade criarOportunidade(@PathVariable Integer idUsuario, @PathVariable Integer idDocente,
                                          @PathVariable Integer idCoordenador, @RequestBody OportData data){

        coordenadorService.buscarPorId(idCoordenador)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "nao foi possivel achar o coordenador"));

        Usuario usuario = buscarUsuario(idUsuario);

        Docente docente = docenteService.buscarPorId(idDocente)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Docente não encontrado"));

        return coordenadorService.criarOportunidade(usuario, docente, data);
    }

    /**
     * @param idOportunidade recebe o id para buscar por uma oportunidade
     * @param idUsuario recebe o id para buscar por um usuario que serve como autorizante
     * @param status extrai os parametros da requisicao e vincula ao parametro status
     */
    @PatchMapping("/oportunidade/{idOportunidade}/publicar/usuario/{idUsuario}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void publicar(@PathVariable Integer idOportunidade, @PathVariable Integer idUsuario,
                         @RequestParam StatusOportunidade status){

        Oportunidade oportunidade = buscarOportunidade(idOportunidade);

        Usuario usuario = buscarUsuario(idUsuario);

        boolean sucesso = coordenadorService.publicar(oportunidade, usuario, status);

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

        boolean sucesso = coordenadorService.fecharInscricoes(oportunidade, usuario);
        if(!sucesso){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Nao foi possivel fechar as inscricoes");
        }
    }

    /**
     * @param idOportunidade pega o id da Oportunidade na URL
     * @param idUsuario pega o id do Usuario na URL
     */
    @PatchMapping("/oportunidade/{idOportunidade}/encerrar/usuario/{idUsuario}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void encerrarOportunidade(@PathVariable Integer idOportunidade, @PathVariable Integer idUsuario){
        Oportunidade oportunidade = buscarOportunidade(idOportunidade);

        Usuario usuario = buscarUsuario(idUsuario);

        boolean sucesso = coordenadorService.encerrarOportunidade(oportunidade, usuario);
        if(!sucesso){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Nao foi possivel encerrar a Oportunidade.");
        }
    }

    /**
     * @param idOportunidade pega o id da Oportunidade na URL
     * @param idUsuario pega o id do Usuario na URL
     * @param novoPlano pega por meio do corpo o novo plano que o usuario deseja salvar
     */
    @PatchMapping("/oportunidade/{idOportunidade}/plano/usuario/{idUsuario}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editarPlano(@PathVariable Integer idOportunidade, @PathVariable Integer idUsuario,
                            @RequestBody String novoPlano){

        Oportunidade oportunidade = buscarOportunidade(idOportunidade);

        Usuario usuario = buscarUsuario(idUsuario);

        boolean sucesso = coordenadorService.editarPlano(oportunidade, usuario, novoPlano);
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
