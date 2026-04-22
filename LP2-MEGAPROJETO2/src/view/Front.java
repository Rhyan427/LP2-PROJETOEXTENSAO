package view;

import entity.*;
import service.MenuPrincipal;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

public class Front {
    Scanner scan = new Scanner(System.in);
    MenuPrincipal menu = new MenuPrincipal();

    public void extensao() {
        int act;
        int act2;
        int act3;

        Curso exemplo = new Curso("Exemplo", 1234, 1000, "Teste");
        Curso example = new Curso("Example", 1470, 1200, "Test");

        System.out.println("Extensão GRAA -- Seja bem-vindo");
        do {
            Discente inDi = new Discente();
            DiscenteDiretor inDiDi = new DiscenteDiretor();
            Docente inDoc = new Docente();
            Coordenador inCoo = new Coordenador();
            int loginID = 0;

            System.out.println("1. Entrar no sistema");
            System.out.println("2. Cadastrar-se");
            System.out.println("0. Sair do sistema");
            System.out.print("Sua escolha: ");
            act = scan.nextInt();
            boolean sucesso;
            boolean sucessoDir;

            switch(act) {
                case 1:
                    System.out.println("Entrando no sistema...");
                    do {
                        System.out.println("1. Entrar como visitante");
                        System.out.println("2. Entrar como discente");
                        System.out.println("3. Entrar como docente");
                        System.out.println("4. Entrar como coordenador");
                        System.out.println("5. Entrar como administrador");
                        System.out.println("0. Voltar para o menu inicial");
                        System.out.print("Sua escolha: ");
                        act2 = scan.nextInt();
                        switch(act2) {
                            case 1:
                                System.out.println("Entrando como visitante...");
                                loginID = 1;
                                break;
                            case 2:
                                System.out.println("Entrando como discente...");

                                scan.nextLine();
                                System.out.print("Informe seu nome: ");
                                String nomeDi = scan.nextLine();
                                System.out.print("Informe sua senha: ");
                                String senhaDi = scan.nextLine();
                                inDi = new Discente();
                                inDiDi = new DiscenteDiretor();
                                sucessoDir = menu.loginDiretor(inDiDi, nomeDi, senhaDi);
                                if (sucessoDir) {
                                    System.out.println("Logando como diretor.\n");
                                    loginID = 2;
                                }
                                else {
                                    sucesso = menu.loginDiscente(inDi, nomeDi, senhaDi);
                                    if (sucesso) {
                                        System.out.println("Login bem-sucedido.\n");
                                        inDiDi = null;
                                        loginID = 2;
                                    }
                                    else {
                                        System.out.println("Discente não encontrado.\n");
                                    }
                                }
                                break;
                            case 3:
                                System.out.println("Entrando como docente...");

                                scan.nextLine();
                                System.out.print("Informe seu nome: ");
                                String nomeDoc = scan.nextLine();
                                System.out.print("Informe sua senha: ");
                                String senhaDoc = scan.nextLine();
                                inDoc = new Docente();
                                sucesso = menu.loginDocente(inDoc, nomeDoc, senhaDoc);
                                if (sucesso) {
                                    System.out.println("Login bem-sucedido.\n");
                                    loginID = 3;
                                }
                                else {
                                    System.out.println("Docente não encontrado.\n");
                                }
                                break;
                            case 4:
                                System.out.println("Entrando como coordenador...");

                                scan.nextLine();
                                System.out.print("Informe seu nome: ");
                                String nomeCoo = scan.nextLine();
                                System.out.print("Informe sua senha: ");
                                String senhaCoo = scan.nextLine();
                                inCoo = new Coordenador();
                                sucesso = menu.loginCoordenador(inCoo, nomeCoo, senhaCoo);
                                if (sucesso) {
                                    System.out.println("Login bem-sucedido.\n");
                                    loginID = 4;
                                }
                                else {
                                    System.out.println("Coordenador não encontrado.\n");
                                }
                                break;
                            case 5:
                                scan.nextLine();
                                System.out.print("Informe o nome de usuário de administrador: ");
                                String adminUsername = scan.nextLine();
                                System.out.print("Informe a senha de administrador: ");
                                String adminPass = scan.nextLine();
                                if (Objects.equals(adminUsername, "admin") && Objects.equals(adminPass, "987654")) {
                                    System.out.println("Entrando como administrador.\n");
                                    loginID = 5;
                                }
                                else {
                                    System.out.println("Errado.\n");
                                }
                                break;
                            case 0:
                                System.out.println("Voltando.\n");
                                break;
                            default:
                                System.out.println("Tente novamente.\n");
                                break;
                        }
                    } while (act2 != 0 && loginID == 0);
                    break;
                case 2:
                    System.out.println("Criando nova conta...");

                    scan.nextLine();
                    System.out.print("Novo nome: ");
                    String nome = scan.nextLine();
                    System.out.print("Novo email: ");
                    String email = scan.nextLine();
                    System.out.print("Nova senha: ");
                    String senha = scan.nextLine();
                    System.out.print("Informe seu no. de matrícula: ");
                    String matricula = scan.nextLine();
                    System.out.print("Informe o seu período atual: ");
                    int periodo = scan.nextInt();
                    sucesso = menu.criarDiscente(nome, email, senha, matricula, periodo, exemplo);
                    if (sucesso) {
                        System.out.println("Conta criada com sucesso.\n");
                    }
                    else {
                        System.out.println("Conta não pôde ser criada.\n");
                    }
                    break;
                case 0:
                    System.out.println("Saindo do sistema...\n");
                    break;
                default:
                    System.out.println("Tente novamente.\n");
            }
            switch (loginID) {
                case 1:
                    System.out.println(" === Menu Visitante ===");
                    do {
                        System.out.println("1. Consultar oportunidades");
                        System.out.println("2. Criar conta de visitante");
                        System.out.println("0. Sair do sistema");
                        System.out.print("Sua escolha: ");
                        act3 = scan.nextInt();
                        switch(act3) {
                            case 1:
                                System.out.println("Consultando oportunidades...");
                                menu.verOportunidades();
                                break;
                            case 2:
                                System.out.println("Criando nova conta...");

                                scan.nextLine();
                                System.out.print("Novo nome: ");
                                String nome = scan.nextLine();
                                System.out.print("Novo email: ");
                                String email = scan.nextLine();
                                System.out.print("Nova senha: ");
                                String senha = scan.nextLine();
                                sucesso = menu.criarUsuario(nome, email, senha);
                                if (sucesso) {
                                    System.out.println("Conta criada com sucesso.\n");
                                }
                                else {
                                    System.out.println("Não foi possível criar a conta.\n");
                                }
                                break;
                            case 0:
                                System.out.println("Saindo...\n");
                                break;
                            default:
                                System.out.println("Tente novamente.\n");
                        }
                    } while (act3 != 0);
                    break;
                case 2:
                    System.out.println(" === Menu Discente === ");
                    do {
                        System.out.println("1. Consultar oportunidades");
                        System.out.println("2. Inscrever-se em uma oportunidade");
                        System.out.println("3. Cancelar inscrição em oportunidade");
                        System.out.println("4. Ver solicitações de aproveitamento");
                        System.out.println("5. Fazer solicitação de aproveitamento");
                        System.out.println("6. Visualizar certificados");
                        System.out.println("7. Trocar senha de acesso");
                        System.out.println("8. Criar uma oportunidade (diretor)");
                        System.out.println("0. Sair");
                        System.out.print("Sua escolha: ");
                        act3 = scan.nextInt();
                        switch(act3) {

                            case 1:
                                System.out.println("Consultando oportunidades ativas...");
                                menu.verOportunidades();
                                break;
                            case 2:
                                System.out.println("Inscrevendo-se em uma oportunidade...");

                                scan.nextLine();
                                System.out.println("Título da oportunidade:");
                                String titulo = scan.nextLine();
                                sucesso = menu.fazerInscricao(inDi, titulo);
                                if (sucesso) {
                                    System.out.println("Inscrição bem-sucedida.\n");
                                }
                                else {
                                    System.out.println("Oportunidade não encontrada.\n");
                                }
                                break;
                            case 3:
                                System.out.println("Cancelando inscrição em oportunidade...");
                                System.out.println("Essas são as suas inscrições: ");
                                menu.verInscricoesPorDiscente(inDi);
                                System.out.print("Título da oportunidade para se desinscrever: ");
                                scan.nextLine();
                                String tituloDesinscr = scan.nextLine();
                                sucesso = menu.cancelarInscricao(inDi, tituloDesinscr);
                                if (sucesso) {
                                    System.out.println("Inscrição retirada com sucesso.\n");
                                }
                                else {
                                    System.out.println("Não foi possível cancelar a inscrição.\n");
                                }
                                break;
                            case 4:
                                System.out.println("Vendo solicitações de aproveitamento...");
                                menu.verAproveitamentoPorDiscente(inDi);
                                break;
                            case 5:
                                System.out.println("Fazendo solicitação de aproveitamento...");

                                scan.nextLine();
                                System.out.print("Informe o ID/hash da certificação: ");
                                String hash = scan.nextLine();
                                if (menu.verificarAutenticidade(inDi.getNome(), hash)) {
                                    System.out.println("Hash validado.");
                                    sucesso = menu.criarAproveitamento(inDi, hash);
                                    if (sucesso) {
                                        System.out.println("Solicitação de aproveitamento criada com sucesso.\n");
                                    }
                                    else {
                                        System.out.println("Não foi possível criar a sua solicitação de aproveitamento.\n");
                                    }
                                }
                                else {
                                    System.out.println("Certificado inválido ou hash incorreto.");
                                }
                                break;
                            case 6:
                                System.out.println("Visualizando meus certificados...");
                                menu.verCertificadosPorDiscente(inDi);
                                break;
                            case 7:
                                System.out.println("Trocando senha de acesso...");

                                scan.nextLine();
                                System.out.print("Nova senha: ");
                                String senha = scan.nextLine();
                                sucesso = menu.trocarSenha(inDi, senha);
                                if (sucesso) {
                                    System.out.println("Senha de acesso atualizada com sucesso.");
                                }
                                else {
                                    System.out.println("Não foi possível atualizar a senha de acesso.");
                                }
                                break;
                            case 8:
                                if (inDiDi != null) {
                                    System.out.println("Criando uma oportunidade...");

                                    scan.nextLine();
                                    System.out.print("Informe o título da oportunidade: ");
                                    String tituloOp = scan.nextLine();
                                    System.out.print("Informe a descrição da oportunidade: ");
                                    String descricao = scan.nextLine();
                                    System.out.print("Informe o tipo da oportunidade (projeto, curso, evento, oficina): ");
                                    TipoOportunidade tipoOportunidade = TipoOportunidade.valueOf(scan.nextLine().toUpperCase());
                                    System.out.print("Informe a modalidade da oportunidade (presencial, remoto, hibrido): ");
                                    Modalidade modalidade = Modalidade.valueOf(scan.nextLine().toUpperCase());
                                    System.out.print("Informe a carga horária da oportunidade: ");
                                    int cargaHoraria = Integer.parseInt(scan.nextLine());
                                    System.out.print("Informe o no. de vagas da oportunidade: ");
                                    int vagas = Integer.parseInt(scan.nextLine());
                                    LocalDate inicio = LocalDate.now();
                                    LocalDate fim = inicio.plusDays(30);
                                    System.out.print("Informe o nome do docente responsável: ");
                                    String nomeDocOp = scan.nextLine();
                                    Oportunidade op = menu.criarOportunidadeDir(nomeDocOp, tituloOp, descricao, tipoOportunidade, modalidade, cargaHoraria, vagas, inicio, fim);
                                    if (op != null) {
                                        System.out.println("Oportunidade " + op.getTitulo() + " criada com sucesso.\n");
                                    }
                                    else {
                                        System.out.println("Falha ao criar a oportunidade.\n");
                                    }
                                    break;
                                }
                                break;
                            case 0:
                                System.out.println("Saindo...");
                                break;
                            default:
                                System.out.println("Tente novamente.\n");
                                break;
                        }
                    } while (act3 != 0);
                    break;
                case 3:
                    System.out.println(" === Menu Docente === ");
                    do {
                        System.out.println("1. Consultar oportunidades");
                        System.out.println("2. Analisar inscrições em oportunidades");
                        System.out.println("3. Criar uma oportunidade");
                        System.out.println("4. Ver solicitações de novas oportunidades");
                        System.out.println("5. Analisar propostas de novas oportunidades");
                        System.out.println("6. Criar plano de atividades de uma oportunidade");
                        System.out.println("7. Fechar inscrições e iniciar oportunidade");
                        System.out.println("8. Encerrar oportunidade");
                        System.out.println("9. Promover discente a diretor");
                        System.out.println("10. Visualizar certificados");
                        System.out.println("11. Assinar certificado");
                        System.out.println("12. Recusar certificado");
                        System.out.println("13. Trocar senha de acesso");
                        System.out.println("0. Sair");
                        System.out.print("Sua escolha: ");
                        act3 = scan.nextInt();
                        switch(act3) {
                            case 1:
                                System.out.println("Consultando oportunidades...");
                                menu.verOportunidades();
                                break;
                            case 2:
                                System.out.println("Analisando inscrições em oportunidades...");
                                System.out.println("Lista de inscrições pendentes: ");
                                menu.verInscricoesPendentes();

                                scan.nextLine();
                                System.out.print("Digite o título da oportunidade: ");
                                String tituloOp = scan.nextLine();
                                System.out.print("Digite o nome do discente: ");
                                String nomeDi = scan.nextLine();
                                System.out.print("Digite APROVADO ou REJEITADO: ");
                                StatusInscricao status = StatusInscricao.valueOf(scan.nextLine().toUpperCase());
                                sucesso = menu.analisarInscricoes(tituloOp, nomeDi, status);
                                if (sucesso) {
                                    System.out.println("Status da inscrição alterado com sucesso.\n");
                                }
                                else {
                                    System.out.println("Não foi possível alterar o status de inscrição.\n");
                                }
                                break;
                            case 3:
                                System.out.println("Criando uma oportunidade...");

                                scan.nextLine();
                                //Docente autor = inDoc; nesse caso
                                System.out.print("Informe o título da oportunidade: ");
                                String titulo = scan.nextLine();
                                System.out.print("Informe a descrição da oportunidade: ");
                                String descricao = scan.nextLine();
                                System.out.print("Informe o tipo da oportunidade (projeto, curso, evento, oficina): ");
                                TipoOportunidade tipoOportunidade = TipoOportunidade.valueOf(scan.nextLine().toUpperCase());
                                System.out.print("Informe a modalidade da oportunidade (presencial, remoto, hibrido): ");
                                Modalidade modalidade = Modalidade.valueOf(scan.nextLine().toUpperCase());
                                System.out.print("Informe a carga horária da oportunidade: ");
                                int cargaHoraria = Integer.parseInt(scan.nextLine());
                                System.out.print("Informe o no. de vagas da oportunidade: ");
                                int vagas = Integer.parseInt(scan.nextLine());
                                LocalDate inicio = LocalDate.now();
                                LocalDate fim = inicio.plusDays(30);
                                Oportunidade op = menu.criarOportunidade(inDoc, titulo, descricao, tipoOportunidade, modalidade, cargaHoraria, vagas, inicio, fim);
                                if (op != null) {
                                    System.out.println("Oportunidade " + op.getTitulo() + " criada com sucesso.\n");
                                }
                                else {
                                    System.out.println("Falha ao criar a oportunidade.\n");
                                }
                                break;
                            case 4:
                                System.out.println("Vendo oportunidades pendentes...");
                                menu.verOportunidadesPendentes();
                                break;
                            case 5:
                                System.out.println("Analisando oportunidades pendentes...");

                                scan.nextLine();
                                System.out.print("Digite o título da oportunidade aguardando aprovação: ");
                                String tituloOpPend = scan.nextLine();
                                System.out.print("Digite PUBLICADA para publicar ou CANCELADA para cancelar: ");
                                StatusOportunidade statusOportunidade = StatusOportunidade.valueOf(scan.nextLine().toUpperCase());
                                sucesso = menu.publicar(tituloOpPend, statusOportunidade);
                                if (sucesso) {
                                    System.out.println("Status da oportunidade alterado com sucesso.\n");
                                }
                                else {
                                    System.out.println("Não foi possível alterar o status da oportunidade.\n");
                                }
                                break;
                            case 6:
                                System.out.println("Criando plano de atividade para oportunidade...");

                                scan.nextLine();
                                System.out.print("Digite o título da oportunidade para editar: ");
                                String tituloOpPlano = scan.nextLine();
                                System.out.print("Digite o plano novo da oportunidade: ");
                                String novoPlano = scan.nextLine();
                                sucesso = menu.editarPlano(tituloOpPlano, novoPlano);
                                if (sucesso) {
                                    System.out.println("Plano da oportunidade alterado com sucesso.\n");
                                }
                                else {
                                    System.out.println("Não foi possível alterar o plano da oportunidade.\n");
                                }
                                break;
                            case 7:
                                System.out.println("Analisando oportunidades publicadas...");

                                scan.nextLine();
                                System.out.print("Informe o título da oportunidade: ");
                                String tituloOpProg = scan.nextLine();
                                sucesso = menu.fecharInscricoes(tituloOpProg);
                                if (sucesso) {
                                    System.out.println("Status da oportunidade alterado com sucesso.\n");
                                }
                                else {
                                    System.out.println("Não foi possível alterar o status da oportunidade.\n");
                                }
                                break;
                            case 8:
                                System.out.println("Analisando oportunidades em progresso...");

                                scan.nextLine();
                                System.out.print("Informe o título da oportunidade: ");
                                String tituloOpEnd = scan.nextLine();
                                sucesso = menu.encerrarOportunidade(tituloOpEnd);
                                if (sucesso) {
                                    System.out.println("Status da oportunidade alterado com sucesso.\n");
                                }
                                else {
                                    System.out.println("Não foi possível alterar o status da oportunidade.\n");
                                }
                                break;
                            case 9:
                                scan.nextLine();
                                System.out.println("Digite o nome do grupo onde o discente está: ");
                                String nomeGpDir = scan.nextLine();
                                System.out.println("Digite o nome do discente que deseja promover: ");
                                String nomeDiDir = scan.nextLine();
                                sucesso = menu.criarDiretor(nomeDiDir, nomeGpDir);
                                if (sucesso) {
                                    System.out.println("Discente promovido com sucesso.\n");
                                }
                                else {
                                    System.out.println("Discente não está em um grupo ou não foi encontrado.\n");
                                }
                                break;
                            case 10:
                                menu.verCertificados();
                                break;
                            case 11:
                                System.out.println("Assinando certificado...");

                                scan.nextLine();
                                System.out.print("Informe o nome do discente: ");
                                String nomeDiAcc = scan.nextLine();
                                System.out.print("Informe o título da oportunidade: ");
                                String tituloOpAcc = scan.nextLine();
                                sucesso = menu.aceitarCertificado(inDoc, nomeDiAcc, tituloOpAcc);
                                if (sucesso) {
                                    System.out.println("Documento assinado com sucesso.\n");
                                }
                                else {
                                    System.out.println("Não foi possível assinar o documento.\n");
                                }
                                break;
                            case 12:
                                System.out.println("Recusando certificado...");

                                scan.nextLine();
                                System.out.print("Informe o nome do discente: ");
                                String nomeDiRec = scan.nextLine();
                                System.out.print("Informe o título da oportunidade: ");
                                String tituloOpRec = scan.nextLine();
                                sucesso = menu.recusarCertificado(inDoc, nomeDiRec, tituloOpRec);
                                if (sucesso) {
                                    System.out.println("Certificado recusado.\n");
                                }
                                else {
                                    System.out.println("Não foi possível interagir com o documento.\n");
                                }
                                break;
                            case 13:
                                System.out.println("Trocando senha de acesso...");

                                scan.nextLine();
                                System.out.print("Nova senha: ");
                                String senha = scan.nextLine();
                                menu.trocarSenha(inDoc, senha);
                                break;
                            case 0:
                                System.out.println("Saindo...\n");
                                break;
                            default:
                                System.out.println("Tente novamente.\n");
                        }
                    } while (act3 != 0);
                    break;
                case 4:
                    System.out.println(" === Menu Coordenador ===");
                    do {
                        System.out.println("1. Ver grupos");
                        System.out.println("2. Criar grupo");
                        System.out.println("3. Adicionar membros a um grupo");
                        System.out.println("4. Remover membros de um grupo");
                        System.out.println("5. Consultar oportunidades");
                        System.out.println("6. Criar uma oportunidade");
                        System.out.println("7. Ver solicitações de novas oportunidades");
                        System.out.println("8. Analisar propostas de novas oportunidades");
                        System.out.println("9. Consultar solicitações de aproveitamento");
                        System.out.println("10. Analisar solicitações de aproveitamento");
                        System.out.println("11. Ver PPC vigente");
                        System.out.println("12. Criar novo PPC");
                        System.out.println("0. Sair do sistema");
                        System.out.print("Sua escolha: ");
                        act3 = scan.nextInt();
                        switch(act3) {
                            case 1:
                                menu.verGrupos();
                                break;
                            case 2:
                                System.out.println("Criando novo grupo...");

                                scan.nextLine();
                                System.out.print("Nome do grupo: ");
                                String nomeGp = scan.nextLine();
                                System.out.print("Descrição do grupo: ");
                                String descGp = scan.nextLine();
                                System.out.print("Email para contato do grupo: ");
                                String emailGp = scan.nextLine();
                                System.out.print("Nome do docente responsável: ");
                                String nomeDoc = scan.nextLine();
                                sucesso = menu.criarGrupo(nomeGp, descGp, emailGp, nomeDoc);
                                if (sucesso) {
                                    System.out.println("Grupo criado com sucesso.\n");
                                }
                                else {
                                    System.out.println("Não foi possível criar o grupo.\n");
                                }
                                break;
                            case 3:
                                System.out.println("Adicionando membros a um grupo...");

                                scan.nextLine();
                                System.out.print("Nome do discente: ");
                                String nomeDiAdd = scan.nextLine();
                                System.out.print("Nome do grupo: ");
                                String nomeGpAdd = scan.nextLine();
                                sucesso = menu.adicionarMembros(nomeDiAdd, nomeGpAdd);
                                if (sucesso) {
                                    System.out.println("Membro adicionado com sucesso.\n");
                                }
                                else {
                                    System.out.println("Não foi possível adicionar este membro.\n");
                                }
                                break;
                            case 4:
                                System.out.println("Removendo membros de um grupo...");

                                scan.nextLine();
                                System.out.print("Nome do discente: ");
                                String nomeDiRmv = scan.nextLine();
                                System.out.print("Nome do grupo: ");
                                String nomeGpRmv = scan.nextLine();
                                sucesso = menu.removerMembros(nomeDiRmv, nomeGpRmv);
                                if (sucesso) {
                                    System.out.println("Membro removido com sucesso.\n");
                                }
                                else {
                                    System.out.println("Não foi possível remover este membro.\n");
                                }
                                break;
                            case 5:
                                System.out.println("Consultando oportunidades...");
                                menu.verOportunidades();
                                break;
                            case 6:
                                System.out.println("Criando uma oportunidade...");

                                scan.nextLine();
                                System.out.print("Informe o título da oportunidade: ");
                                String titulo = scan.nextLine();
                                System.out.print("Informe a descrição da oportunidade: ");
                                String descricao = scan.nextLine();
                                System.out.print("Informe o tipo da oportunidade (projeto, curso, evento, oficina): ");
                                TipoOportunidade tipoOportunidade = TipoOportunidade.valueOf(scan.nextLine().toUpperCase());
                                System.out.print("Informe a modalidade da oportunidade (presencial, remoto, hibrido): ");
                                Modalidade modalidade = Modalidade.valueOf(scan.nextLine().toUpperCase());
                                System.out.print("Informe a carga horária da oportunidade: ");
                                int cargaHoraria = Integer.parseInt(scan.nextLine());
                                System.out.print("Informe o no. de vagas da oportunidade: ");
                                int vagas = Integer.parseInt(scan.nextLine());
                                LocalDate inicio = LocalDate.now();
                                LocalDate fim = inicio.plusDays(30);
                                System.out.print("Informe o nome do docente responsável: ");
                                String nomeDocOp = scan.nextLine();
                                Oportunidade op = menu.criarOportunidadeCoord(nomeDocOp, titulo, descricao, tipoOportunidade, modalidade, cargaHoraria, vagas, inicio, fim);
                                if (op != null) {
                                    System.out.println("Oportunidade " + op.getTitulo() + " criada com sucesso.\n");
                                }
                                else {
                                    System.out.println("Falha ao criar a oportunidade.\n");
                                }
                                break;
                            case 7:
                                System.out.println("Vendo oportunidades pendentes...");
                                menu.verOportunidadesPendentes();
                                break;
                            case 8:
                                System.out.println("Analisando oportunidades pendentes...");

                                scan.nextLine();
                                System.out.print("Digite o título da oportunidade aguardando aprovação: ");
                                String tituloOpPend = scan.nextLine();
                                System.out.print("Digite PUBLICADA para publicar ou CANCELADA para cancelar: ");
                                StatusOportunidade statusOportunidade = StatusOportunidade.valueOf(scan.nextLine().toUpperCase());
                                sucesso = menu.publicar(tituloOpPend, statusOportunidade);
                                if (sucesso) {
                                    System.out.println("Status da oportunidade alterado com sucesso.\n");
                                }
                                else {
                                    System.out.println("Não foi possível alterar o status da oportunidade.\n");
                                }
                                break;
                            case 9:
                                System.out.println("Vendo solicitações de aproveitamento...");
                                menu.verAproveitamento();
                                break;
                            case 10:
                                System.out.println("Analisando solicitações de aproveitamento...");

                                scan.nextLine();
                                System.out.print("Nome do discente: ");
                                String diApr = scan.nextLine();
                                System.out.print("Informe o ID: ");
                                String idApr = scan.nextLine();
                                System.out.print("Informe o novo status (APROVADO ou REJEITADO: ");
                                StatusAproveitamento status = StatusAproveitamento.valueOf(scan.nextLine());
                                sucesso = menu.editarAproveitamento(diApr, idApr, status);
                                if (sucesso) {
                                    System.out.println("Status do aproveitamento alterado com sucesso.\n");
                                }
                                else {
                                    System.out.println("Status do aproveitamento não pôde ser alterado.\n");
                                }
                                break;
                            case 11:
                                System.out.println("Visualizando PPC de um curso...");

                                scan.nextLine();
                                System.out.print("Digite o nome do curso: ");
                                String nomeCurso = scan.nextLine();
                                Curso curso = menu.pegarCurso(nomeCurso);
                                if (curso != null) {
                                    System.out.printf("Curso %s (código %s).\n Horas: %d, PPC versão %s.\n", curso.getNome(), curso.getCodigo(),
                                            curso.getCargaHoraria(), curso.getPpcVersao());

                                }
                                else {
                                    System.out.println("Não foi possível encontrar esse curso.\n");
                                }
                                break;
                            case 12:
                                System.out.println("Atualizando PPC de um curso...");

                                scan.nextLine();
                                System.out.print("Digite o nome do curso: ");
                                String nomeCursoUpdate = scan.nextLine();
                                System.out.print("Digite o novo código do curso: ");
                                int codigoCursoUpdate = Integer.parseInt(scan.nextLine());
                                System.out.print("Digite a nova carga horária do curso: ");
                                int cargaHorariaUpdate = Integer.parseInt(scan.nextLine());
                                System.out.print("Digite a nova versão do PPC do curso: ");
                                String ppcCursoUpdate = scan.nextLine();
                                sucesso = menu.atualizarPPC(nomeCursoUpdate, codigoCursoUpdate, cargaHorariaUpdate, ppcCursoUpdate, inCoo);
                                if (sucesso) {
                                    System.out.println("PPC do curso atualizado com sucesso.\n");
                                }
                                else {
                                    System.out.println("PPC do curso não pôde ser atualizado.\n");
                                }
                                break;
                            case 0:
                                System.out.println("Saindo...\n");
                                break;
                            default:
                                System.out.print("Sua escolha.");
                        }
                    } while (act3 != 0);
                    break;
                case 5:
                    System.out.println(" === Menu Admin ===");
                    do {
                        System.out.println("1. Cadastrar docente");
                        System.out.println("2. Cadastrar coordenador");
                        System.out.println("0. Sair do sistema");
                        System.out.print("Sua escolha: ");
                        act3 = scan.nextInt();
                        switch(act3) {
                            case 1:
                                System.out.println("Cadastrando novo docente...");

                                scan.nextLine();
                                System.out.print("Nome do docente: ");
                                String nomeDoc = scan.nextLine();
                                System.out.print("Email do docente: ");
                                String emailDoc = scan.nextLine();
                                System.out.print("Senha de acesso do docente: ");
                                String senhaDoc = scan.nextLine();
                                System.out.print("Informe o SIAPE do docente: ");
                                String siapeDoc = scan.nextLine();
                                System.out.print("Informe o departamento do docente: ");
                                String departamentoDoc = scan.nextLine();
                                sucesso = menu.criarDocente(nomeDoc, emailDoc, senhaDoc, siapeDoc, departamentoDoc);
                                if (sucesso) {
                                    System.out.println("Docente criado com sucesso.\n");
                                }
                                else {
                                    System.out.println("Não foi possível criar este docente.\n");
                                }
                                break;
                            case 2:
                                System.out.println("Cadastrando novo coordenador...");

                                scan.nextLine();
                                System.out.print("Nome do coordenador: ");
                                String nomeCoo = scan.nextLine();
                                System.out.print("Email do coordenador: ");
                                String emailCoo = scan.nextLine();
                                System.out.print("Senha de acesso do coordenador: ");
                                String senhaCoo = scan.nextLine();
                                System.out.print("Informe o SIAPE do coordenador: ");
                                String siapeCoo = scan.nextLine();
                                System.out.print("Informe o departamento do coordenador: ");
                                String departamentoCoo = scan.nextLine();
                                sucesso = menu.criarCoordenador(nomeCoo, emailCoo, senhaCoo, siapeCoo, departamentoCoo);
                                if (sucesso) {
                                    System.out.println("Coordenador criado com sucesso.\n");
                                }
                                else {
                                    System.out.println("Não foi possível criar este coordenador.\n");
                                }
                                break;
                            case 0:
                                System.out.println("Saindo...\n");
                                break;
                            default:
                                System.out.println("Tente novamente.\n");
                        }
                    } while (act3 != 0);
                    break;
                default:
                    break;
            }
        } while (act != 0);
    }
}