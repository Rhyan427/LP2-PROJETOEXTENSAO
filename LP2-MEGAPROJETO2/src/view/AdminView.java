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
        System.out.println("0. Sair");
        System.out.print("Sua escolha: ");
        int act = scan.nextInt();
        if (act == 0) {
            System.out.println("Saindo.\n");
        }
        return act;
    }
    public DocenteData criarDocenteData(DocenteData data) {
        scan.nextLine();
        System.out.print("Nome do docente: ");
        data.setNome(scan.nextLine());
        System.out.print("Email do docente: ");
        data.setEmail(scan.nextLine());
        System.out.print("Senha de acesso do docente: ");
        data.setSenha(scan.nextLine());
        System.out.print("Informe o SIAPE do docente: ");
        data.setSiape(scan.nextLine());
        System.out.print("Informe o departamento do docente: ");
        data.setDepartamento(scan.nextLine());
        return data;
    }
    public void criarDocenteResult(boolean result) {
        if (result) System.out.println("Docente cadastrado com sucesso.\n");
        else System.out.println("Falha ao cadastrar novo docente.\n");
    }

    public CoordData criarCoordenadorData(CoordData data) {
        scan.nextLine();
        System.out.print("Nome do coordenador: ");
        data.setNome(scan.nextLine());
        System.out.print("Email do coordenador: ");
        data.setEmail(scan.nextLine());
        System.out.print("Senha de acesso do coordenador: ");
        data.setSenha(scan.nextLine());
        System.out.print("Informe o SIAPE do coordenador: ");
        data.setSiape(scan.nextLine());
        System.out.print("Informe o departamento do coordenador: ");
        data.setDepartamento(scan.nextLine());
        return data;
    }

    public void criarCoordenadorResult(boolean result) {
        if (result) System.out.println("Coordenador cadastrado com sucesso.\n");
        else System.out.println("Falha ao cadastrar novo coordenador.\n");
    }

    public void gerenciarVinculos(Scanner scanner, UsuarioService usuarioService, ArrayList<Usuario> ListaUsuarios){
        System.out.println("Gerenciar vinculos");
        System.out.print("Digite o email do usuario");
        String emailBusca = scanner.nextLine();

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

        int opcao = scanner.nextInt();
        scanner.nextLine();

        if(opcao == 1){
            usuarioService.desativarConta(usuarioAlvo);
            System.out.println("Conta desativada com sucesso");
        } else if (opcao == 2){
            usuarioService.anonimizarConta(usuarioAlvo);
            System.out.println("Conta anonimizada com sucesso");
        } else {
            System.out.println("Escolha uma opcao valida");
        }
    }

    public void avaliarSolicitacoesGrupo(Scanner scanner, GrupoService grupoService, HashMap<String, Grupo> mapaGrupos){
        System.out.println("Avaliacao de solicitacao de grupos estudantis");

        ArrayList<Grupo> pendentes = grupoService.listarGruposPendentes(mapaGrupos);

        if(pendentes.isEmpty()){
            System.out.println("Nao existem solicitacoes pendentes no momento");
            return;
        }

        for(int i = 0; i < pendentes.size(); i++){
            Grupo g = pendentes.get(i);
            System.out.println(i + " - " + g.getNome() + " (Docente responsavel: " + g.getResponsavel().getNome() + ")");
        }

        System.out.println("Digite o numero do grupo que deseja avaliar (ou -1 para cancelar): ");
        int escolha = scanner.nextInt();
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
            int decisao = scanner.nextInt();
            scanner.nextLine();

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
}