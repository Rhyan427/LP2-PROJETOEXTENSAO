package view;

import dataTransfer.AprovtData;
import dataTransfer.GrupoData;
import dataTransfer.OportData;
import entity.Discente;
import entity.Grupo;
import entity.Modalidade;
import entity.StatusOportunidade;
import entity.TipoOportunidade;
import service.GrupoService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CoordenadorView {
    Scanner scan = new Scanner(System.in);

    public int view() {
        System.out.println(" === Menu Coordenador ===");
        System.out.println("1. Ver grupos");
        System.out.println("2. Criar grupo");
        System.out.println("3. Adicionar membros a um grupo");
        System.out.println("4. Remover membros de um grupo");
        System.out.println("5. Consultar oportunidades");
        System.out.println("6. Criar uma oportunidade");
        System.out.println("7. Ver solicitações de novas oportunidades");
        System.out.println("8. Avaliar propostas de novas oportunidades");
        System.out.println("9. Consultar solicitações de aproveitamento");
        System.out.println("10. Avaliar solicitações de aproveitamento");
        System.out.println("11. Indeferir solicitação de aproveitamento"); //RF022
        System.out.println("12. Ver PPC vigente");
        System.out.println("13. Criar novo PPC");
        System.out.println("14. Ver solicitacoes de grupos");
        System.out.println("0. Sair");
        System.out.print("Sua escolha: ");
        int act = scan.nextInt();
        if (act == 0) {
            System.out.println("Saindo.\n");
        }
        return act;
    }

    public GrupoData criarGrupoData(GrupoData data) {
        scan.nextLine();
        System.out.print("Nome do grupo: ");
        data.setNome(scan.nextLine());
        System.out.print("Descrição do grupo: ");
        data.setDescricao(scan.nextLine());
        System.out.print("Objetivo do grupo: ");
        data.setObjetivos(scan.nextLine());
        System.out.print("Email para contato do grupo: ");
        data.setEmail(scan.nextLine());
        return data;
    }

    public OportData criarOportunidadeData(OportData data) {
        scan.nextLine();
        System.out.print("Informe o título da oportunidade: ");
        data.setTitulo(scan.nextLine());
        System.out.print("Informe a descrição da oportunidade: ");
        data.setDescricao(scan.nextLine());
        System.out.print("Informe o tipo da oportunidade (projeto, curso, evento, oficina): ");
        data.setTipo(TipoOportunidade.valueOf(scan.nextLine().toUpperCase()));
        System.out.print("Informe a modalidade da oportunidade (presencial, remoto, hibrido): ");
        data.setModalidade(Modalidade.valueOf(scan.nextLine().toUpperCase()));
        System.out.print("Informe a carga horária da oportunidade: ");
        data.setCargaHoraria(Integer.parseInt(scan.nextLine()));
        System.out.print("Informe o no. de vagas da oportunidade: ");
        data.setVagas(Integer.parseInt(scan.nextLine()));
        data.setInicio(LocalDate.now());
        data.setFim(data.getInicio().plusDays(30));
        return data;
    }

    public String getResponsavel() {
        scan.nextLine();
        System.out.print("Informe o nome do docente responsável: ");
        return scan.nextLine();
    }

    public void criarOportunidadeResult(boolean result) {
        if (result) System.out.println("Oportunidade criada com sucesso.\n");
        else System.out.println("Falha ao criar nova oportunidade.\n");
    }

    public String avaliarOportunidadesPendentes() {
        scan.nextLine();
        System.out.print("Digite o título da oportunidade aguardando aprovação: ");
        String titulo = scan.nextLine();
        System.out.print("Digite PUBLICADA para publicar ou CANCELADA para cancelar: ");
        StatusOportunidade status = StatusOportunidade.valueOf(scan.nextLine().toUpperCase());
        return titulo + ";" + status;
    }

    public void avaliarOportunidadesResult(boolean result) {
        if (result) System.out.println("Oportunidade alterada com sucesso.\n");
        else System.out.println("Falha ao alterar oportunidade.\n");
    }

    public void verGrupos(GrupoData data) {
        System.out.printf("- Grupo %s. Responsável: %s. Membros: ", data.getNome(), data.getResponsavel().getNome());
        for (Discente d : data.getMembros().values()) {
            System.out.printf("%s ", d.getNome());
        }
        System.out.print("\n");
    }

    public void criarGrupoResult(boolean result) {
        if (result) System.out.println("Grupo criado com sucesso.\n");
        else System.out.println("Falha ao criar grupo novo.");
    }

    public String getMembroGrupo() {
        scan.nextLine();
        System.out.print("Informe o nome do discente: ");
        String nomeD = scan.nextLine();
        System.out.print("Informe o nome do grupo: ");
        String nomeG = scan.nextLine();
        return nomeD + ";" + nomeG;
    }

    public void addMembroResult(boolean result) {
        if (result) System.out.println("Membro adicionado com sucesso.\n");
        else System.out.println("Falha ao adicionar novo membro.\n");
    }
    public void removeMembroResult(boolean result) {
        if (result) System.out.println("Membro removido com sucesso.\n");
        else System.out.println("Falha ao remover membro.\n");
    }

    public String criarPPC() {
        scan.nextLine();
        System.out.print("Informe a descrição do novo PPC: ");
        return scan.nextLine();
    }

    public int criarPPCHoras() {
        scan.nextLine();
        System.out.print("Informe a carga horária de extensão do novo PPC: ");
        return scan.nextInt();
    }

    public void verAproveitamento(AprovtData data) {
        System.out.printf("- Aproveitamento %s, por %s. Status: %s\n", data.getDescricao(), data.getDiscente().getNome(), data.getStatus());
    }

    public String analisarAproveitamento() {
        scan.nextLine();
        System.out.print("Veredito sobre a inscrição. Digite APROVADO ou REJEITADO: ");
        return scan.nextLine().toUpperCase();
    }

    public String parecerRecusa() {
        scan.nextLine();
        System.out.print("Justificativa de rejeição: ");
        return scan.nextLine();
    }

    public void analisarAproveitamentoResult(boolean result) {
        if (result) System.out.println("Status de aproveitamento alterado com sucesso.\n");
        else System.out.println("Falha ao alterar status do aproveitamento.\n");
    }

    // RF022 — coleta chave     do aproveitamento e motivo do indeferimento
    public String[] indeferirAproveitamento() {
        scan.nextLine();
        System.out.print("Nome do discente da solicitação: ");
        String nomeDi = scan.nextLine();
        System.out.print("Descrição (ID) da solicitação: ");
        String descricao = scan.nextLine();
        System.out.print("Motivo do indeferimento: ");
        String motivo = scan.nextLine();
        return new String[]{nomeDi, descricao, motivo};
    }

    public void indeferirAproveitamentoResult(boolean result) {
        if (result) System.out.println("Solicitação indeferida. O discente tem 5 dias para reenviar.\n");
        else System.out.println("Falha ao indeferir solicitação.\n");
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