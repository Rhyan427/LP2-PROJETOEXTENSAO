package view;

import dataTransfer.AprovtData;
import dataTransfer.CertData;
import dataTransfer.OportData;
import entity.Modalidade;
import entity.TipoOportunidade;
import entity.Discente;
import service.GrupoService;
import entity.Docente;
import dataTransfer.GrupoData;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;

public class DiscenteView {
    Scanner scan = new Scanner(System.in);

    public int view() {
        System.out.println(" === Menu Discente === ");

        System.out.println("1. Consultar oportunidades");
        System.out.println("2. Inscrever-se em uma oportunidade");
        System.out.println("3. Cancelar inscrição em oportunidade");
        System.out.println("4. Ver solicitações de aproveitamento");
        System.out.println("5. Fazer solicitação de aproveitamento");
        System.out.println("6. Reenviar solicitação indeferida"); //RF023
        System.out.println("7. Visualizar certificados");
        System.out.println("8. Criar uma oportunidade (diretor)");
        System.out.println("9. Fazer solicitacao de grupo");
        System.out.println("0. Sair");
        int act = lerIntSeguro(scan, "Sua escolha: ");
        if (act == 0) {
            System.out.println("Saindo.\n");
        }
        return act;
    }
    public String getOport() {
        scan.nextLine();
        System.out.print("Título da oportunidade:");
        return lerStringSegura(scan, "");
    }

    public void criarInscricaoResult(boolean result) {
        if (result) System.out.println("Inscrição realizada com sucesso.\n");
        else System.out.println("Falha ao se inscrever.\n");
    }

    public void cancelarInscricaoResult(boolean result) {
        if (result) System.out.println("Inscrição retirada com sucesso.\n");
        else System.out.println("Falha ao retirar inscrição.\n");
    }

    public void verCertificados(CertData data) {
        System.out.printf("- Certificado de participação do discente %s na oportunidade %s (ID: %s). Status: %s\n", data.getDiscente().getNome(), data.getOportunidade().getTitulo(), data.getUuidHash(), data.getStatusAssinatura());
    }

    public OportData criarOportunidadeData(OportData data) {
        scan.nextLine();
        System.out.print("Informe o título da oportunidade: ");
        data.setTitulo(lerStringSegura(scan, ""));
        System.out.print("Informe a descrição da oportunidade: ");
        data.setDescricao(lerStringSegura(scan, ""));
        System.out.print("Informe o tipo da oportunidade (projeto, curso, evento, oficina): ");
        data.setTipo(TipoOportunidade.valueOf(lerStringSegura(scan, "").toUpperCase()));
        System.out.print("Informe a modalidade da oportunidade (presencial, remoto, hibrido): ");
        data.setModalidade(Modalidade.valueOf(lerStringSegura(scan, "").toUpperCase()));
        System.out.print("Informe a carga horária da oportunidade: ");
        data.setCargaHoraria(Integer.parseInt(lerStringSegura(scan, "")));
        System.out.print("Informe o no. de vagas da oportunidade: ");
        data.setVagas(Integer.parseInt(lerStringSegura(scan, "")));
        data.setInicio(LocalDate.now());
        data.setFim(data.getInicio().plusDays(30));
        return data;
    }

    public String getResponsavel() {
        scan.nextLine();
        System.out.print("Informe o nome do docente responsável: ");
        return lerStringSegura(scan, "");
    }

    public void criarOportunidadeResult(boolean result) {
        if (result) System.out.println("Oportunidade criada com sucesso.\n");
        else System.out.println("Falha ao criar nova oportunidade.\n");
    }

    public void verAproveitamentoDiscente(AprovtData data) {
        System.out.printf("- Aproveitamento %s. Status: %s\n", data.getDescricao(), data.getStatus());
        // RF022 — exibe prazo de decisão se pendente
        if (data.getStatus() == entity.StatusAproveitamento.PENDENTE && data.getDataLimiteDecisao() != null) {
            System.out.printf("  Prazo para decisão do coordenador: %s\n", data.getDataLimiteDecisao());
        }
        // RF022/RF023 — exibe motivo e prazo de reenvio se indeferido
        if (data.getStatus() == entity.StatusAproveitamento.INDEFERIDO && data.getDataLimiteReenvio() != null) {
            System.out.printf("  Motivo: %s | Prazo para reenvio: %s\n",
                    data.getMotivo_rejeicao(), data.getDataLimiteReenvio());
        }
    }

    // RF023 — coleta chave da solicitação indeferida e novos dados
    public String[] reenviarAproveitamento() {
        scan.nextLine();
        System.out.print("Descrição (ID) da solicitação indeferida: ");
        String descricao = lerStringSegura(scan, "");
        System.out.print("Nova descrição: ");
        String novaDescricao = lerStringSegura(scan, "");
        System.out.print("Nova carga horária (horas): ");
        String novasHoras = lerStringSegura(scan, "");
        return new String[]{descricao, novaDescricao, novasHoras};
    }

    public void reenviarAproveitamentoResult(boolean result) {
        if (result) System.out.println("Solicitação reenviada com sucesso. Aguarde nova análise.\n");
        else System.out.println("Falha ao reenviar solicitação.\n");
    }

    public String criarAproveitamento() {
        scan.nextLine();
        System.out.print("Título da oportunidade a ser aproveitada: ");
        return lerStringSegura(scan, "");
    }

    public void criarAproveitamentoResult(boolean result) {
        if (result) System.out.println("Solicitação de aproveitamento criada com sucesso.\n");
        else System.out.println("Falha ao criar nova solicitação de aproveitamento.\n");
    }

    public void exibirBarraProgresso(int horasValidas, int horasTotais){
        int tamanhoBarra = 20;
        double porcentagem;

        if(horasTotais > 0){
            porcentagem = Math.min(1.0, (double) horasValidas / horasTotais);
        } else {
            porcentagem = 0.0;
        }
        int preenchido = (int) (tamanhoBarra * porcentagem);
        int vazio = tamanhoBarra - preenchido;

        String barra = "[" + "█".repeat(preenchido) + "-".repeat(vazio) + "]";
        String valorPorcentagem = String.format("%.2f", porcentagem * 100);

        System.out.println("Progresso de extensao: " + barra + " " + valorPorcentagem + "% (" + horasValidas + "/" + horasTotais + " h)");
    }

    public void socilitarNovoGrupo(Discente discenteLogado, ArrayList<Docente> docentes, GrupoService grupoService){
        System.out.println("Formulario: Solicitacao de novo grupo estudantil");

        System.out.println("Nome do grupo: ");
        String nome = lerStringSegura(scan, "");

        System.out.println("Descricao das atividades: ");
        String descricao = lerStringSegura(scan, "");

        System.out.println("Objetivos academicos: ");
        String objetivos = lerStringSegura(scan, "");

        System.out.println("Selecione o Docente responsavel: ");

        for(int i = 0; i < docentes.size(); i++){
            System.out.println(i + " - " + docentes.get(i).getNome());
        }

        System.out.println("Opcao: ");
        int escolha = lerIntSeguro(scan, "");
        scan.nextLine();

        if(escolha >= 0 && escolha < docentes.size()) {
            Docente responsavel = docentes.get(escolha);

            GrupoData dadosSocilitacao = new GrupoData(nome, descricao, objetivos, responsavel, discenteLogado);

            grupoService.criarSolicitacao(dadosSocilitacao);

            System.out.println("A solicitacao foi enviada e está pendente de aprovação");
        } else {
            System.out.println("Opção inválida");
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