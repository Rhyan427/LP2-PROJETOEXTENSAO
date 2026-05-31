package view;

import dataTransfer.CoordData;
import dataTransfer.DocenteData;
import entity.Usuario;
import service.GrupoService;
import service.UsuarioService;
import entity.Grupo;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class AdminView {
    Scanner scan = new Scanner(System.in);

    public int view() {
        System.out.println(" === Menu Admin ===");
        System.out.println("1. Cadastrar docente");
        System.out.println("2. Cadastrar coordenador");
        System.out.println("3. Ver solicitacoes de grupo");
        System.out.println("4. Desativar ou anonimizar contas");
        System.out.println("0. Sair");
        int act = lerIntSeguro(scan, "Sua escolha: ");
        if (act == 0) {
            System.out.println("Saindo.\n");
        }
        return act;
    }
    public DocenteData criarDocenteData(DocenteData data) {
        scan.nextLine();
        System.out.print("Nome do docente: ");
        data.setNome(lerStringSegura(scan, ""));
        System.out.print("Email do docente: ");
        data.setEmail(lerStringSegura(scan, ""));
        System.out.print("Senha de acesso do docente: ");
        data.setSenha(lerStringSegura(scan, ""));
        System.out.print("Informe o SIAPE do docente: ");
        data.setSiape(lerStringSegura(scan, ""));
        System.out.print("Informe o departamento do docente: ");
        data.setDepartamento(lerStringSegura(scan, ""));
        return data;
    }
    public void criarDocenteResult(boolean result) {
        if (result) System.out.println("Docente cadastrado com sucesso.\n");
        else System.out.println("Falha ao cadastrar novo docente.\n");
    }

    public CoordData criarCoordenadorData(CoordData data) {
        scan.nextLine();
        System.out.print("Nome do coordenador: ");
        data.setNome(lerStringSegura(scan, ""));
        System.out.print("Email do coordenador: ");
        data.setEmail(lerStringSegura(scan, ""));
        System.out.print("Senha de acesso do coordenador: ");
        data.setSenha(lerStringSegura(scan, ""));
        System.out.print("Informe o SIAPE do coordenador: ");
        data.setSiape(lerStringSegura(scan, ""));
        System.out.print("Informe o departamento do coordenador: ");
        data.setDepartamento(lerStringSegura(scan, ""));
        return data;
    }

    public void criarCoordenadorResult(boolean result) {
        if (result) System.out.println("Coordenador cadastrado com sucesso.\n");
        else System.out.println("Falha ao cadastrar novo coordenador.\n");
    }

    public void gerenciarVinculos(Scanner scanner, UsuarioService usuarioService, ArrayList<Usuario> ListaUsuarios){
        System.out.println("Gerenciar vinculos");
        System.out.print("Digite o email do usuario");
        String emailBusca = lerStringSegura(scanner, "");

        Usuario usuarioAlvo = null;
        for(Usuario u: ListaUsuarios){
            if(u.getEmail().equalsIgnoreCase(emailBusca)){
                usuarioAlvo = u;
                break;
            }
        }

        if(usuarioAlvo == null){
            System.out.println("Usuario nao encontrado");
            return;
        }

        System.out.println("Usuario encontrado: " + usuarioAlvo.getNome());
        System.out.println("1. Desativar conta (bloquear acesso)");
        System.out.println("2. Anonimizar conta (apagar dados)");
        System.out.print("Escolha uma opcao: ");

        int opcao = lerIntSeguro(scanner, "");

        if(opcao == 1){
            usuarioService.desativarConta(usuarioAlvo);
            System.out.println("Conta desativada com sucesso");
        } else if (opcao == 2){
            usuarioService.anonimizarConta(usuarioAlvo);
            System.out.println("Conta anonimizada com sucesso");
        } else {
            System.out.println("Escolha uma opção válida");
        }
    }

    public void avaliarSolicitacoesGrupo(Scanner scanner, GrupoService grupoService, HashMap<String, Grupo> mapaGrupos){
        System.out.println("Avaliacao de solicitação de grupos estudantis");

        ArrayList<Grupo> pendentes = grupoService.listarGruposPendentes(mapaGrupos);

        if(pendentes.isEmpty()){
            System.out.println("Nao existem solicitações pendentes no momento");
            return;
        }

        for(int i = 0; i < pendentes.size(); i++){
            Grupo g = pendentes.get(i);
            System.out.println(i + " - " + g.getNome() + " (Docente responsavel: " + g.getResponsavel().getNome() + ")");
        }

        System.out.println("Digite o numero do grupo que deseja avaliar (ou -1 para cancelar): ");
        int escolha = lerIntSeguro(scanner, "");
        scanner.nextLine();

        if(escolha >= 0 && escolha < pendentes.size()){
            Grupo grupoAlvo = pendentes.get(escolha);

            System.out.println("Informacoes sobre o grupo");
            System.out.println("Nome: " + grupoAlvo.getNome());
            System.out.println("Descricao: " + grupoAlvo.getDescricao());
            System.out.println("Objetivos: " + grupoAlvo.getObjetivos());
            System.out.println("\n1. Aprovar grupo");
            System.out.println("2. Rejeitar grupo");
            System.out.print("Sua decisao: ");
            int decisao = lerIntSeguro(scanner, "");

            if(decisao == 1){
                grupoService.avaliarSolicitacao(grupoAlvo, true);
                System.out.println("\nO grupo foi aprovado com sucesso");
            } else if (decisao == 2){
                grupoService.avaliarSolicitacao(grupoAlvo, false);
                System.out.println("\nA solicitacao foi rejeitada");
            } else {
                System.out.println("\nOpcao invalida. A avaliacao foi cancelada");
            }
        } else if (escolha != -1){
            System.out.println("\nPosicao invalida");
        }
    }

    public int lerIntSeguro(Scanner scan, String mensagem){
        while(true){
            if(!mensagem.isBlank()) {
                System.out.print(mensagem);
            }

            try {
                return Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException e){
                System.out.println("Entrada inválida! Digite um numero válido");
            }
        }
    }

    public String lerStringSegura(Scanner scan, String mensagem){
        while(true){
            if(!mensagem.isBlank()) {
                System.out.println(mensagem);
            }
            String entrada = scan.nextLine();

            if(entrada.isBlank()){
                System.out.println("Entrada inválida! Este campo não pode ficar vazio");
            } else {
                return entrada.trim();
            }
        }
    }
}