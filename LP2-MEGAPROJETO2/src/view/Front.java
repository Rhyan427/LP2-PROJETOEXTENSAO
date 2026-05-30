package view;

import dataTransfer.DiscenteData;
import dataTransfer.OportData;

import java.util.Scanner;

public class Front {
    Scanner scan = new Scanner(System.in);

    public int mainView() {
        int act;
        System.out.println("Extensão GRAA -- Seja bem-vindo");
        System.out.println("1. Entrar no sistema");
        System.out.println("2. Cadastrar-se");
        System.out.println("3. Acessar sem login");
        System.out.println("0. Sair do sistema");
        System.out.print("Sua escolha: ");
        act = scan.nextInt();
        if (act == 0) {
            System.out.println("Saindo.\n");
        }
        return act;
    }

    public void visitorView() {}

    public String login() {
        scan.nextLine();
        System.out.print("Informe seu nome: ");
        String nome = scan.nextLine();
        System.out.print("Informe sua senha: ");
        String senha = scan.nextLine();
        return nome + ';' + senha;
    }

    public void loginResult(boolean result) {
        if (result) System.out.println("Login bem-sucedido.\n");
        else System.out.println("Falha ao logar.\n");
    }

    public void LoginResult(int status){
        if(status == 1){
            System.out.println("Login bem sucedido\n");
        } else if (status == -1 ){
            System.out.println("[ERRO] Acesso negado: Conta desativada ou anonimizada\n");
        } else {
            System.out.println("Falha ao logar. Credenciais incorretas\n");
        }
    }

    public DiscenteData criarDiscenteData(DiscenteData data) {
        scan.nextLine();
        System.out.print("Novo nome: ");
        data.setNome(scan.nextLine());
        System.out.print("Novo email: ");
        data.setEmail(scan.nextLine());
        System.out.print("Nova senha: ");
        data.setSenha(scan.nextLine());
        System.out.print("Informe seu no. de matrícula: ");
        data.setMatricula(scan.nextLine());
        System.out.print("Informe o seu período atual: ");
        data.setSemestre(scan.nextInt());
        return data;
    }

    public void criarDiscenteResult(boolean result) {
        if (result) System.out.println("Cadastro bem-sucedido.\n");
        else System.out.println("Falha ao cadastrar.\n");
    }

    public void verOportunidades(OportData data) {
        System.out.printf("- Título: %s. %s, %s, com %d vagas. %d horas ofertadas. Status: %s\n", data.getTitulo(), data.getTipo(), data.getModalidade(), data.getVagas(), data.getCargaHoraria(), data.getStatus());
    }

    public void verOportunidadesPendentes(OportData data) {
        System.out.printf("- Título: %s. %s, %s, com %d vagas. %d horas ofertadas. Status: %s\n", data.getTitulo(), data.getTipo(), data.getModalidade(), data.getVagas(), data.getCargaHoraria(), data.getStatus());
    }
}
