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
        act = lerIntSeguro(scan, "Sua escolha: ");
        if (act == 0) {
            System.out.println("Saindo.\n");
        }
        return act;
    }

    public void visitorView() {}

    public String login() {
        System.out.print("Informe seu nome: ");
        String nome = lerStringSegura(scan, "");
        System.out.print("Informe sua senha: ");
        String senha = lerStringSegura(scan, "");
        return nome + ';' + senha;
    }

    public void loginResult(boolean result) {
        if (result) System.out.println("Login bem-sucedido.\n");
        else System.out.println("Falha ao logar.\n");
    }

    public DiscenteData criarDiscenteData(DiscenteData data) {
        scan.nextLine();
        System.out.print("Novo nome: ");
        data.setNome(lerStringSegura(scan, ""));
        System.out.print("Novo email: ");
        data.setEmail(lerStringSegura(scan, ""));
        System.out.print("Nova senha: ");
        data.setSenha(lerStringSegura(scan, ""));
        System.out.print("Informe seu no. de matrícula: ");
        data.setMatricula(lerStringSegura(scan, ""));
        System.out.print("Informe o seu período atual: ");
        data.setSemestre(lerIntSeguro(scan, ""));
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
