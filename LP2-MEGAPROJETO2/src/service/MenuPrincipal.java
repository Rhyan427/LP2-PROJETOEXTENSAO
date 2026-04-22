package service;

import entity.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class MenuPrincipal {
    UsuarioService usuarioService = new UsuarioService();
    DiscenteService discenteService = new DiscenteService();
    DiscenteDiretorService discenteDiretorService = new DiscenteDiretorService();
    DocenteService docenteService = new DocenteService();
    CoordenadorService coordenadorService = new CoordenadorService();
    OportunidadeService oportunidadeService = new OportunidadeService();
    InscricaoService inscricaoService = new InscricaoService();
    GrupoService grupoService = new GrupoService();
    CursoService cursoService = new CursoService();
    CertificadoService certificadoService = new CertificadoService();
    AproveitamentoService aproveitamentoService = new AproveitamentoService();

    //Funções de usuário
    public boolean criarUsuario(String nome, String email, String senha) {
        return usuarioService.criarConta(nome, email, senha);
    }

    public boolean trocarSenha(Usuario u, String senha) {
        return usuarioService.trocarSenha(u, senha);
    }

    public void verOportunidades() {
        oportunidadeService.verOportunidades();
    }

    public void verGrupos() {
        grupoService.verGrupos();
    }

    //Funções de discente
    public boolean criarDiscente(String nome, String email, String senha, String matricula, int semestre, Curso curso) {
        return discenteService.criarDiscente(nome, email, senha, matricula, semestre, curso);
    }

    public boolean loginDiscente(Discente in, String nome, String senha) {
        return discenteService.loginDiscente(in, nome, senha);
    }

    public boolean fazerInscricao(Discente di, String titulo) {
        Oportunidade op = pegarOportunidade(titulo);
        if (op != null) {
            return inscricaoService.fazerInscricao(di, op);
        }
        return false;
    }

    public void verInscricoesPorDiscente(Discente di) {
        inscricaoService.verInscricoesPorDiscente(di);
    }

    public boolean cancelarInscricao(Discente di, String titulo) {
        return inscricaoService.cancelarInscricao(di, titulo);
    }

    public void verCertificadosPorDiscente(Discente di) {
        certificadoService.verCertificadosPorDiscente(di);
    }

    public void verCurso(Discente di) {
        discenteService.verCurso(di);
    }

    public boolean mudarCurso(Discente di, String nomeCurso) {
        Curso curso = pegarCurso(nomeCurso);
        if (curso == null) {
            return false;
        }
        discenteService.mudarCurso(di, curso);
        return true;
    }

    public boolean criarAproveitamento(Discente di, String uuid) {
        Certificado cert = pegarCertificado(di.getNome(), uuid);
        if (cert != null) {
            return aproveitamentoService.criarAproveitamento(di, cert);
        }
        return false;
    }
    public void verAproveitamentoPorDiscente(Discente di) {
        aproveitamentoService.verAproveitamentoPorDiscente(di);
    }

    //Funções do diretor
    public boolean criarDiretor(String nomeDir, String nomeGp) {
        Discente di = pegarDiscente(nomeDir);
        Grupo gp = pegarGrupo(nomeGp);
        if (di == null || gp == null) {
            return false;
        }
        return discenteDiretorService.criarDiretor(nomeDir, nomeGp, di, gp);
    }

    public boolean loginDiretor(DiscenteDiretor in, String nome, String senha) {
        return discenteDiretorService.loginDiretor(in, nome, senha);
    }

    public Oportunidade criarOportunidadeDir(String nomeDoc, String titulo, String descricao, TipoOportunidade tipo, Modalidade modalidade, int cargaHoraria, int vagas, LocalDate inicio, LocalDate fim) {
        Docente doc = pegarDocente(nomeDoc);
        return oportunidadeService.criarOportunidadeDir(doc, titulo, descricao, tipo, modalidade, cargaHoraria, vagas, inicio, fim);
    }

    //Funções do docente
    public boolean loginDocente(Docente in, String nome, String senha) {
        return docenteService.loginDocente(in, nome, senha);
    }

    public Oportunidade criarOportunidade(Docente autor, String titulo, String descricao, TipoOportunidade tipo, Modalidade modalidade, int cargaHoraria, int vagas, LocalDate inicio, LocalDate fim) {
        return oportunidadeService.criarOportunidade(autor, titulo, descricao, tipo, modalidade, cargaHoraria, vagas, inicio, fim);
    }
    public void verOportunidadesPendentes() {
        oportunidadeService.verOportunidadesPendentes();
    }
    public boolean publicar(String titulo, StatusOportunidade status) {
        Oportunidade op = pegarOportunidade(titulo);
        return oportunidadeService.publicar(op, status);
    }
    public boolean fecharInscricoes(String titulo) {
        Oportunidade op = pegarOportunidade(titulo);
        return oportunidadeService.fecharInscricoes(op);
    }
    public boolean encerrarOportunidade(String titulo) {
        Oportunidade op = pegarOportunidade(titulo);
        ArrayList<Inscricao> completos = pegarInscricoesPorOportunidade(op);
        if (op == null || completos == null) {
            return false;
        }

        boolean sucesso = oportunidadeService.encerrarOportunidade(op);
        for (Inscricao ins : completos) {
            if (ins.getStatus() == StatusInscricao.APROVADO && op.getStatus() == StatusOportunidade.ENCERRADA) {
                certificadoService.criarCertificado(ins.getDiscente(), ins.getOportunidade());
            }
        }
        return sucesso;
    }

    public void verCertificados() {
        certificadoService.verCertificados();
    }
    public boolean aceitarCertificado(Docente autor, String nomeDi, String nomeOp) {
        Certificado cert = certificadoService.pegarCertificado(nomeDi, nomeOp);
        if (cert == null) {
            return false;
        }
        return docenteService.aprovarCertificado(autor, cert);
    }

    public boolean recusarCertificado(Docente autor, String nomeDi, String nomeOp) {
        Certificado cert = certificadoService.pegarCertificado(nomeDi, nomeOp);
        if (cert == null) {
            return false;
        }
        return docenteService.recusarCertificado(autor, cert);
    }

    public boolean editarPlano(String tituloOp, String planoNovo) {
        Oportunidade op = pegarOportunidade(tituloOp);
        if (op == null) {
            return false;
        }
        return oportunidadeService.editarPlano(op, planoNovo);
    }

    public void verInscricoes() {
        inscricaoService.verInscricoes();
    }
    public void verInscricoesPendentes() {
        inscricaoService.verInscricoesPendentes();
    }
    public boolean analisarInscricoes(String tituloOp, String nomeDi, StatusInscricao status) {
        return inscricaoService.analisarInscricao(tituloOp, nomeDi, status);
    }

    //Funções do coordenador
    public boolean loginCoordenador(Coordenador in, String nome, String senha) {
        return coordenadorService.loginCoordenador(in, nome, senha);
    }

    public boolean criarGrupo(String nome, String descricao, String email, String nomeDoc) {
        Docente responsavel = pegarDocente(nomeDoc);
        return grupoService.criarGrupo(nome, descricao, email, responsavel);
    }

    public boolean adicionarMembros(String nomeDi, String nomeGp) {
        Discente di = pegarDiscente(nomeDi);
        return grupoService.adicionarMembros(di, nomeGp);
    }
    public boolean removerMembros(String nomeDi, String nomeGp) {
        Discente di = pegarDiscente(nomeDi);
        return grupoService.removerMembros(di, nomeGp);
    }

    public Oportunidade criarOportunidadeCoord(String nomeDoc, String titulo, String descricao, TipoOportunidade tipo, Modalidade modalidade, int cargaHoraria, int vagas, LocalDate inicio, LocalDate fim) {
        Docente doc = pegarDocente(nomeDoc);
        return oportunidadeService.criarOportunidade(doc, titulo, descricao, tipo, modalidade, cargaHoraria, vagas, inicio, fim);
    }

    public boolean atualizarPPC(String nomeCurso, int novoCodigo, int novaCargaHoraria, String novaVersao, Coordenador autor) {
        Curso curso = pegarCurso(nomeCurso);
        return cursoService.atualizarPPC(curso, novoCodigo, novaCargaHoraria, novaVersao, autor);
    }

    public void verAproveitamento() {
        aproveitamentoService.verAproveitamento();
    }

    public boolean editarAproveitamento(String nome, String id, StatusAproveitamento status) {
        return aproveitamentoService.editarAproveitamento(nome, id, status);
    }

    //Funções do admin
    public boolean criarDocente(String nome, String email, String senha, String siape, String departamento) {
        return docenteService.criarDocente(nome, email, senha, siape, departamento);
    }

    public boolean criarCoordenador(String nome, String email, String senha, String siape, String departamento) {
        return coordenadorService.criarCoordenador(nome, email, senha, siape, departamento);
    }

    //Funções auxiliares
    public Oportunidade pegarOportunidade(String titulo) {
        return oportunidadeService.pegarOportunidade(titulo);
    }
    public ArrayList<Inscricao> pegarInscricoesPorOportunidade(Oportunidade op) {
        return inscricaoService.pegarInscricoesPorOportunidade(op);
    }
    public Curso pegarCurso(String nome) {
        return cursoService.pegarCurso(nome);
    }
    public Grupo pegarGrupo(String nome) {
        return grupoService.pegarGrupo(nome);
    }
    public Discente pegarDiscente(String nome) {
        return discenteService.pegarDiscente(nome);
    }
    public Docente pegarDocente(String nome) {
        return docenteService.pegarDocente(nome);
    }
    public Certificado pegarCertificado(String nomeDi, String uuid) {
        return certificadoService.pegarCertificado(nomeDi, uuid);
    }
    public boolean verificarAutenticidade(String nomeDi, String uuid) {
        return certificadoService.verificarAutenticidade(nomeDi, uuid);
    }
}
