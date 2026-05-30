package service;

import dataTransfer.*;
import entity.*;
import view.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.time.LocalDate;
import java.util.Scanner;


public class ZMenuPrincipal {
    HashMap<String, Usuario> usuarios = new HashMap<>();
    HashMap<Discente, Diretor> diretores = new HashMap<>();
    HashMap<String, Grupo> grupos = new HashMap<>();
    HashMap<String, Oportunidade> oportunidades = new HashMap<>();
    HashMap<String, Inscricao> inscricoes = new HashMap<>();
    HashMap<String, Certificado> certificados = new HashMap<>();
    HashMap<String, Aproveitamento> lista = new HashMap<>();

    OportunidadeService oportunidadeService = new OportunidadeService();
    GrupoService grupoService = new GrupoService();
    InscricaoService inscricaoService = new InscricaoService();
    CertificadoService certificadoService = new CertificadoService();
    AproveitamentoService aproveitamentoService = new AproveitamentoService();

    Front front = new Front();
    DiscenteView discenteView = new DiscenteView();
    DiscenteService discenteService = new DiscenteService();
    DiretorService diretorService = new DiretorService();
    DocenteView docenteView = new DocenteView();
    DocenteService docenteService = new DocenteService();
    CoordenadorView coordenadorView = new CoordenadorView();
    CoordenadorService coordenadorService = new CoordenadorService();
    AdminView adminView = new AdminView();

    Scanner scanner = new Scanner(System.in);

    Coordenador cxz = new Coordenador("cxz", "cxz", "cxz", "cxz", "cxz");
    Docente doxz = new Docente("doxz", "doxz", "doxz", "doxz", "doxz");
    PPC ppc = new PPC(cxz, "Exemplo", 200, LocalDate.now());
    Curso curso = new Curso("Exemplo", 1234, 1000, ppc);
    Discente dixz = new Discente("dixz", "dixz", "dixz", "dixz", 0, curso);

    public void extensao() {
        usuarios.putIfAbsent(cxz.getNome(), cxz);
        usuarios.putIfAbsent(doxz.getNome(), doxz);
        usuarios.putIfAbsent(dixz.getNome(), dixz);

        Usuario u = null;
        int act;
        String log = "";
        String role;
        do {
            act = front.mainView();
            switch (act) {
                case 1 -> {
                    log = front.login();
                    String[] abc = log.split(";");
                    u = usuarios.get(abc[0]);
                    if (u != null && usuarioExiste(u)) {
                        if (Objects.equals(u.getNome(), abc[0]) && Objects.equals(u.getSenha(), abc[1])) {
                            front.loginResult(true);
                        }
                        else {
                            u = null;
                            front.loginResult(false);
                        }
                    }
                    else if (Objects.equals(log, "admin;987654")) {
                        System.out.println("Logando como administrador.\n");
                    }
                    else front.loginResult(false);
                }
                case 2 -> {
                    DiscenteData data = new DiscenteData();
                    data.setCurso(curso);
                    data.setPapel(new Papel("Discente"));
                    data.setAtivo(true);
                    data = front.criarDiscenteData(data);
                    Usuario d = discenteService.criarDiscente(data);
                    if (d != null && !usuarioExiste(d)) {
                        usuarios.putIfAbsent(d.getNome(), d);
                        front.criarDiscenteResult(true);
                    }
                    else front.criarDiscenteResult(false);
                }
                case 3 -> {
                    //"logar" como visitante
                }
                case 0 -> {}
            }
            if (Objects.equals(log, "admin;987654")) {
                adminCtrl();
            }
            if (u != null) {
                role = u.getPapel().getDescricao();
                switch (role) {
                    case "Discente", "Discente Diretor" -> discenteCtrl(u);
                    case "Docente" -> docenteCtrl(u);
                    case "Coordenador" -> coordCtrl(u);
                }
            }
            u = null;
            log = "";
        } while (act != 0);
    }

    public void discenteCtrl(Usuario u) {
        int act;
        do {
            act = discenteView.view();
            switch (act) {
                case 1 -> listOportunidades();  // Consultar oportunidades
                case 2 -> { // Inscrever-se em uma oportunidade
                    InscricaoData data = new InscricaoData();
                    String abc = discenteView.getOport();
                    Oportunidade op = oportunidades.get(abc);
                    Diretor dir = diretores.get((Discente) u);
                    if (op != null && !Objects.equals(op.getAutor(), dir)) {
                        data.setDiscente((Discente) u);
                        data.setOportunidade(op);
                        Inscricao i = inscricaoService.fazerInscricao(data.getDiscente(), data.getOportunidade());
                        if (i != null) {
                            String insKey = i.getDiscente().getNome() + ";" + i.getOportunidade().getTitulo();
                            inscricoes.putIfAbsent(insKey, i);
                            discenteView.criarInscricaoResult(true);
                        }
                        else discenteView.criarInscricaoResult(false);
                    }
                    else discenteView.criarInscricaoResult(false);
                }
                case 3 -> { // Cancelar inscrição em oportunidade
                    String abc = discenteView.getOport();
                    Inscricao i = inscricoes.get(u.getNome() + ";" + abc);
                    if (i != null) {
                        i.setStatus(StatusInscricao.CANCELADO);
                        discenteView.cancelarInscricaoResult(true);
                    }
                    else discenteView.cancelarInscricaoResult(false);
                }
                case 4 -> listAproveitamentoDiscente((Discente) u); // Ver solicitações de aproveitamento (do próprio usuário)
                case 5 -> {
                    String abc = u.getNome() + ";" + discenteView.criarAproveitamento();
                    Certificado cert = certificados.get(abc);
                    boolean result = false;
                    if (cert != null) {
                        AprovtData data = new AprovtData();
                        data.setDiscente((Discente) u);
                        data.setDescricao(cert.getOportunidade().getTitulo());
                        data.setHoras(cert.getHoras());
                        data.setCertificado(cert);
                        Aproveitamento a = aproveitamentoService.criarAproveitamento(data);
                        lista.putIfAbsent(a.getDiscente().getNome() + ";" + a.getDescricao(), a);
                        result = true;
                    }
                    discenteView.criarAproveitamentoResult(result);
                }
                case 6 -> {
                    // RF023 — reenviar solicitação indeferida dentro do prazo de 5 dias
                    String[] abc = discenteView.reenviarAproveitamento();
                    String chave = u.getNome() + ";" + abc[0]; // chave = nome;descricao
                    Aproveitamento aprov = lista.get(chave);
                    int novasHoras = Integer.parseInt(abc[2]);
                    boolean result = aproveitamentoService.reenviar(aprov, abc[1], novasHoras);
                    discenteView.reenviarAproveitamentoResult(result);
                }
                case 7 -> { // Visualizar certificados
                    for (Certificado cert : certificados.values()) {
                        if (Objects.equals(cert.getDiscente(), u)) {
                            CertData data = new CertData();
                            data.setDiscente(cert.getDiscente());
                            data.setOportunidade(cert.getOportunidade());
                            data.setStatusAssinatura(cert.getStatusAssinatura());
                            discenteView.verCertificados(data);
                        }
                    }
                }
                case 8 -> { // Criar uma oportunidade (diretor)
                    Diretor dir = diretores.get((Discente) u);
                    if (dir != null) {
                        OportData data = new OportData();
                        data.setAutor(dir);
                        data = discenteView.criarOportunidadeData(data);
                        String resp = discenteView.getResponsavel();
                        Docente d = (Docente) usuarios.get(resp);
                        if (d != null) {
                            data.setResponsavel(d);
                            Oportunidade op = oportunidadeService.criarOportunidade(data);
                            oportunidades.putIfAbsent(op.getTitulo(), op);
                            discenteView.criarOportunidadeResult(true);
                        }
                        else discenteView.criarOportunidadeResult(false);
                    }
                    else discenteView.criarOportunidadeResult(false);
                }
                case 9 -> {
                    ArrayList<Docente> listaDocentes = new ArrayList<>();

                    for(Usuario usuario : usuarios.values()){
                        if(usuario instanceof Docente){
                            listaDocentes.add((Docente) usuario);
                        }
                    }

                    discenteView.socilitarNovoGrupo((Discente) u, listaDocentes, grupoService);

                }

                case 0 -> {}
            }
        } while (act != 0);
    }


    public void docenteCtrl(Usuario u) {
        int act;
        do {
            act = docenteView.view();
            switch (act) {
                case 1 -> listOportunidades();
                case 2 -> listInscricoes();
                case 3 -> {
                    Inscricao i = getInscricaoPendenteDocente((Docente) u);
                    if (i != null) {
                        String abc = docenteView.avaliarInscricao();
                        i.setStatus(StatusInscricao.valueOf(abc));
                        docenteView.avaliarInscricaoResult(true);
                    }
                    else docenteView.avaliarInscricaoResult(false);
                }
                case 4 -> {
                    OportData data = new OportData();
                    data.setResponsavel((Docente) u);
                    data.setAutor(u);
                    data = docenteView.criarOportunidadeData(data);
                    Oportunidade op = oportunidadeService.criarOportunidade(data);
                    if (op != null) {
                        oportunidades.putIfAbsent(op.getTitulo(), op);
                        docenteView.criarOportunidadeResult(true);
                    }
                    else docenteView.criarOportunidadeResult(false);
                }
                case 5 -> listOportunidadesPendentes();
                case 6 -> {
                    String[] abc = docenteView.avaliarOportunidadesPendentes().split(";");
                    Oportunidade op = oportunidades.get(abc[0]);
                    StatusOportunidade status = StatusOportunidade.valueOf(abc[1]);
                    boolean result = docenteService.publicar(op, u, status);
                    docenteView.avaliarOportunidadesResult(result);
                }
                case 7 -> {} //TODO: Criar plano de atividades de uma oportunidade (fase 3? a lógica tá aí pra quem quiser aplicar)
                case 8 -> {
                    String abc = docenteView.fecharInscricoes();
                    Oportunidade op = oportunidades.get(abc);
                    boolean result = docenteService.fecharInscricoes(op, u);
                    docenteView.fecharInscricoesResult(result);
                }
                case 9 -> {
                    String abc = docenteView.encerrarOportunidade();
                    Oportunidade op = oportunidades.get(abc);

                    ArrayList<Inscricao> completos = pegarInscricoesPorOportunidade(op);
                    boolean result = docenteService.encerrarOportunidade(op, u);
                    for (Inscricao i : completos) {
                        if (i.getStatus() == StatusInscricao.APROVADO && op.getStatus() == StatusOportunidade.ENCERRADA) {
                            CertData data = new CertData();
                            data.setDiscente(i.getDiscente());
                            data.setOportunidade(op);
                            Certificado cert = certificadoService.criarCertificado(data);
                            certificados.putIfAbsent(i.getDiscente().getNome() + ";" + i.getOportunidade().getTitulo(), cert);
                        }
                    }
                    docenteView.encerrarOportunidadeResult(result);
                }
                case 10 -> {
                    String[] abc = docenteView.promoverDiscente().split(";");
                    Discente d = (Discente) usuarios.get(abc[0]);
                    Grupo g = grupos.get(abc[1]);
                    if (d != null && g != null && Objects.equals(g.getResponsavel(), u) ) {
                        Discente membro = g.getMembros().get(d.getNome());
                        if (membro != null && Objects.equals(d, membro)) {
                            DiretorData data = new DiretorData();
                            data.setNome(membro.getNome());
                            data.setEmail(membro.getEmail());
                            data.setSenha(membro.getSenha());
                            data.setMatricula(membro.getMatricula());
                            data.setSemestre(membro.getSemestre());
                            data.setCurso(membro.getCurso());
                            data.setGrupo(g);
                            Diretor dir = diretorService.criarDiretor(data);
                            diretores.putIfAbsent(d, dir);
                            docenteView.promoverDiscenteResult(true);
                        }
                        else docenteView.promoverDiscenteResult(false);
                    }
                    else docenteView.promoverDiscenteResult(false);
                }
                case 11 -> {
                    for (Certificado cert : certificados.values()) {
                        CertData data = new CertData();
                        data.setDiscente(cert.getDiscente());
                        data.setOportunidade(cert.getOportunidade());
                        data.setStatusAssinatura(cert.getStatusAssinatura());
                        discenteView.verCertificados(data);
                    }
                }
                case 12 -> {
                    for (Certificado cert : certificados.values()) {
                        if (Objects.equals(cert.getOportunidade().getResponsavel(), u) && cert.getStatusAssinatura() == StatusAssinatura.PENDENTE) {
                            StatusAssinatura status = StatusAssinatura.valueOf(docenteView.avaliarCertificadoPendente());
                            cert.setStatusAssinatura(status);
                            docenteView.avaliarCertificadoResult(true);
                            break;
                        }
                    }
                    docenteView.avaliarCertificadoResult(false);
                }


                case 13 -> {
                    String[] abc = docenteView.atribuirCargo().split(";");
                    Grupo g = grupos.get(abc[0]);
                    Usuario ds = usuarios.get(abc[1]);

                    if (g != null && ds != null && ds instanceof Discente) {
                        try {
                            CargoGrupo cargo = CargoGrupo.valueOf(abc[2]);
                            boolean result = grupoService.atribuirCargo((Docente) u, g, (Discente) ds, cargo);
                            docenteView.gerenciarCargoResult(result);
                        } catch (IllegalArgumentException e) {

                            docenteView.gerenciarCargoResult(false);
                        }
                    } else {
                        docenteView.gerenciarCargoResult(false);
                    }
                }
                case 14 -> {
                    String[] abc = docenteView.removerCargo().split(";");
                    Grupo g = grupos.get(abc[0]);
                    Usuario ds = usuarios.get(abc[1]);

                    if (g != null && ds != null && ds instanceof Discente) {
                        boolean result = grupoService.removerCargo((Docente) u, g, (Discente) ds);
                        docenteView.gerenciarCargoResult(result);
                    } else {
                        docenteView.gerenciarCargoResult(false);
                    }
                }
                case 15 -> {
                    String[] abc = docenteView.substituirParticipante().split(";");
                    Oportunidade op = oportunidades.get(abc[0]);
                    Usuario saindo = usuarios.get(abc[1]);
                    String justificativa = abc[2];
                    Usuario entrando = usuarios.get(abc[3]);

                    if (op != null && saindo instanceof Discente && entrando instanceof Discente) {
                        boolean result = inscricaoService.substituirParticipante(op, (Discente) saindo, (Discente) entrando, justificativa);
                        docenteView.substituirParticipanteResult(result);
                    } else {
                        docenteView.substituirParticipanteResult(false);
                    }
                }

                case 0 -> {}
            }
        } while (act != 0);
    }


    public void coordCtrl(Usuario u) {
        int act;
        do {
            act = coordenadorView.view();
            switch (act) {
                case 1 -> listGrupos();
                case 2 -> {
                    GrupoData data = new GrupoData();
                    data = coordenadorView.criarGrupoData(data);
                    String abc = coordenadorView.getResponsavel();
                    Docente d = (Docente) usuarios.get(abc);
                    if (d != null) {
                        data.setResponsavel(d);
                        Grupo g = grupoService.criarGrupo(data.getNome(), data.getDescricao(), data.getEmail(), d);
                        grupos.putIfAbsent(g.getNome(), g);
                        coordenadorView.criarGrupoResult(true);
                    }
                    else coordenadorView.criarGrupoResult(false);
                }
                case 3 -> {
                    String[] abc = coordenadorView.getMembroGrupo().split(";");
                    Discente d = (Discente) usuarios.get(abc[0]);
                    Grupo g = grupos.get(abc[1]);
                    if (d != null && g != null) {
                        g.getMembros().putIfAbsent(d.getNome(), d);
                        coordenadorView.addMembroResult(true);
                    }
                    else coordenadorView.addMembroResult(false);
                }
                case 4 -> {
                    String[] abc = coordenadorView.getMembroGrupo().split(";");
                    Discente d = (Discente) usuarios.get(abc[0]);
                    Grupo g = grupos.get(abc[1]);
                    if (d != null && g != null) {
                        Discente membro = g.getMembros().get(d.getNome());
                        if (membro != null) {
                            g.getMembros().remove(d.getNome());
                            coordenadorView.removeMembroResult(true);
                        }
                        else coordenadorView.removeMembroResult(false);
                    }
                    else coordenadorView.removeMembroResult(false);
                }
                case 5 -> listOportunidades();
                case 6 -> {
                    OportData data = new OportData();
                    data.setAutor(u);
                    data = coordenadorView.criarOportunidadeData(data);
                    String resp = coordenadorView.getResponsavel();
                    Docente d = (Docente) usuarios.get(resp);
                    data.setResponsavel(d);
                    Oportunidade op = oportunidadeService.criarOportunidade(data);
                    if (op != null) {
                        oportunidades.putIfAbsent(op.getTitulo(), op);
                        coordenadorView.criarOportunidadeResult(true);
                    }
                    else coordenadorView.criarOportunidadeResult(false);
                }
                case 7 -> listOportunidadesPendentes();
                case 8 -> {
                    String[] abc = coordenadorView.avaliarOportunidadesPendentes().split(";");
                    Oportunidade op = oportunidades.get(abc[0]);
                    StatusOportunidade status = StatusOportunidade.valueOf(abc[1]);
                    boolean result = coordenadorService.publicar(op, u, status);
                    coordenadorView.avaliarOportunidadesResult(result);
                }
                case 9 -> listAproveitamento();
                case 10 -> {
                    boolean result = false;
                    for (Aproveitamento a : lista.values()) {
                        if (a.getStatus() == StatusAproveitamento.PENDENTE) {
                            System.out.printf("- %s, solicitada por %s. Status: %s\n", a.getDescricao(), a.getDiscente().getNome(), a.getStatus());
                            StatusAproveitamento status = StatusAproveitamento.valueOf(coordenadorView.analisarAproveitamento());
                            a.setStatus(status);
                            if (status == StatusAproveitamento.REJEITADO) {
                                String motivo = coordenadorView.parecerRecusa();
                                a.setMotivo_rejeicao(motivo);
                            }
                            result = true;
                            break;
                        }
                    }
                    coordenadorView.analisarAproveitamentoResult(result);
                }
                case 11 -> {
                    // RF022 — indeferir solicitação: registra motivo e define prazo de 5 dias para reenvio
                    String[] abc = coordenadorView.indeferirAproveitamento();
                    String chave = abc[0] + ";" + abc[1];
                    Aproveitamento aprov = lista.get(chave);
                    boolean result = aproveitamentoService.indeferir(aprov, abc[2]);
                    // atualiza o AprovtData de exibição com o prazo
                    coordenadorView.indeferirAproveitamentoResult(result);
                }
                case 12 -> System.out.printf("%s. %d horas.\n", curso.getPpcAtual().getDescricao(), curso.getPpcAtual().getHorasExtensao());
                case 13 -> {
                    String abc = coordenadorView.criarPPC();
                    int horas = coordenadorView.criarPPCHoras();
                    PPC ppc = new PPC(u, abc, horas, LocalDate.now());
                    curso.getHistorico().add(curso.getPpcAtual());
                    curso.setPpcAtual(ppc);
                }
                case 14 -> coordenadorView.avaliarSolicitacoesGrupo(scanner, grupoService, grupos);
                case 0 -> {}
            }
        } while (act != 0);
    }


    public void adminCtrl() {
        int act;
        do {
            act = adminView.view();
            switch (act) {
                case 1 -> {
                    DocenteData data = new DocenteData();
                    data = adminView.criarDocenteData(data);
                    Usuario d = docenteService.criarDocente(data);
                    if (d != null && !usuarioExiste(d)) {
                        usuarios.putIfAbsent(d.getNome(), d);
                        adminView.criarDocenteResult(true);
                    }
                    else adminView.criarDocenteResult(false);
                }
                case 2 -> {
                    CoordData data = new CoordData();
                    data = adminView.criarCoordenadorData(data);
                    Usuario c = coordenadorService.criarCoordenador(data);
                    if (c != null && !usuarioExiste(c)) {
                        usuarios.putIfAbsent(c.getNome(), c);
                        adminView.criarCoordenadorResult(true);
                    }
                    else adminView.criarCoordenadorResult(false);
                }
                case 3 -> adminView.avaliarSolicitacoesGrupo(scanner, grupoService, grupos);
                case 0 -> {}
            }
        } while (act != 0);
    }

    public boolean usuarioExiste(Usuario u) {
        String teste = u.getNome();
        Usuario abc = usuarios.get(teste);
        if (abc == null) return false;
        return Objects.equals(u.getNome(), abc.getNome()) && Objects.equals(u.getEmail(), abc.getEmail());
    }

    public void listOportunidades() {
        for (Oportunidade op : oportunidades.values()) {
            if (op.getStatus() != StatusOportunidade.AGUARDANDO_APROVACAO || op.getStatus() != StatusOportunidade.RASCUNHO) {
                OportData data = new OportData(op.getTitulo(), op.getTipo(), op.getModalidade(), op.getVagas(), op.getCargaHoraria(), op.getStatus());
                front.verOportunidades(data);
            }
        }
    }
    public void listOportunidadesPendentes() {
        for (Oportunidade op : oportunidades.values()) {
            if (op.getStatus() == StatusOportunidade.AGUARDANDO_APROVACAO || op.getStatus() == StatusOportunidade.RASCUNHO) {
                OportData data = new OportData(op.getTitulo(), op.getTipo(), op.getModalidade(), op.getVagas(), op.getCargaHoraria(), op.getStatus());
                front.verOportunidadesPendentes(data);
            }
        }
    }

    public void listGrupos() {
        for (Grupo g : grupos.values()) {
            GrupoData data = new GrupoData(g.getNome(), g.getResponsavel(), g.getMembros());
            coordenadorView.verGrupos(data);
        }
    }

    public void listInscricoes() {
        for (Inscricao i : inscricoes.values()) {
            InscricaoData data = new InscricaoData(i.getDiscente(), i.getOportunidade(), i.getStatus());
            docenteView.verInscricoes(data);
        }
    }

    public Inscricao getInscricaoPendenteDocente(Docente d) {
        for (Inscricao i : inscricoes.values()) {
            if (i.getStatus() == StatusInscricao.PENDENTE && Objects.equals(i.getOportunidade().getResponsavel(), d)) {
                InscricaoData data = new InscricaoData(i.getDiscente(), i.getOportunidade(), i.getStatus());
                docenteView.verInscricoesPendentes(data);
                return i;
            }
        }
        return null;
    }

    public ArrayList<Inscricao> pegarInscricoesPorOportunidade(Oportunidade op) {
        ArrayList<Inscricao> recuperadas = new ArrayList<>();
        for (Inscricao ins : inscricoes.values()) {
            if (Objects.equals(ins.getOportunidade(), op)) {
                recuperadas.add(ins);
            }
        }
        return recuperadas;
    }

    public void listAproveitamento() {
        AprovtData data = new AprovtData();
        for (Aproveitamento a : lista.values()) {
            data.setDescricao(a.getDescricao());
            data.setDiscente(a.getDiscente());
            data.setStatus(a.getStatus());
            coordenadorView.verAproveitamento(data);
        }
    }

    public void listAproveitamentoDiscente(Discente d) {
        AprovtData data = new AprovtData();
        for (Aproveitamento a : lista.values()) {
            if (Objects.equals(a.getDiscente(), d)) {
                data.setDescricao(a.getDescricao());
                data.setStatus(a.getStatus());
                data.setMotivo_rejeicao(a.getMotivo_rejeicao());
                data.setDataLimiteDecisao(a.getDataLimiteDecisao());   // RF022
                data.setDataLimiteReenvio(a.getDataLimiteReenvio());   // RF022/RF023
                discenteView.verAproveitamentoDiscente(data);
            }
        }
    }
}