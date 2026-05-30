package view;

import dataTransfer.InscricaoData;
import dataTransfer.OportData;
import entity.Modalidade;
import entity.StatusOportunidade;
import entity.TipoOportunidade;

import java.time.LocalDate;
import java.util.Scanner;

public class DocenteView {
    Scanner scan = new Scanner(System.in);

    public int view() {
        System.out.println(" === Menu Docente === ");
        System.out.println("1. Consultar oportunidades");
        System.out.println("2. Visualizar inscrições em oportunidades");
        System.out.println("3. Avaliar inscrições em oportunidades");
        System.out.println("4. Criar uma oportunidade");
        System.out.println("5. Ver solicitações de novas oportunidades");
        System.out.println("6. Avaliar propostas de novas oportunidades");
        System.out.println("7. Criar plano de atividades de uma oportunidade");
        System.out.println("8. Fechar inscrições e iniciar oportunidade");
        System.out.println("9. Encerrar oportunidade");
        System.out.println("10. Promover discente a diretor");
        System.out.println("11. Visualizar certificados");
        System.out.println("12. Avaliar certificado");
        System.out.println("13. Atribuir cargo a discente em grupo");
        System.out.println("14. Remover cargo de discente em grupo");
        System.out.println("15. Substituir participante em oportunidade");
        System.out.println("0. Sair");
        System.out.print("Sua escolha: ");
        int act = scan.nextInt();
        if (act == 0) {
            System.out.println("Saindo.\n");
        }
        return act;
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

    public String fecharInscricoes() {
        scan.nextLine();
        System.out.print("Informe o título da oportunidade a ser iniciada: ");
        return scan.nextLine();
    }
    public void fecharInscricoesResult(boolean result) {
        if (result) System.out.println("Oportunidade iniciada com sucesso.\n");
        else System.out.println("Falha ao iniciar oportunidade.\n");
    }

    public String encerrarOportunidade() {
        scan.nextLine();
        System.out.print("Informe o título da oportunidade a ser encerrada: ");
        return scan.nextLine();
    }
    public void encerrarOportunidadeResult(boolean result) {
        if (result) System.out.println("Oportunidade finalizada com sucesso.\n");
        else System.out.println("Falha ao finalizar oportunidade.\n");
    }

    public void verInscricoes(InscricaoData data) {
        System.out.printf("%s %s, solicitada por %s. Status: %s\n", data.getOportunidade().getTipo(), data.getOportunidade().getTitulo(), data.getDiscente().getNome(), data.getStatus());
    }

    public void verInscricoesPendentes(InscricaoData data) {
        System.out.printf("%s %s, solicitada por %s. Status: %s\n", data.getOportunidade().getTipo(), data.getOportunidade().getTitulo(), data.getDiscente().getNome(), data.getStatus());
    }

    public String avaliarInscricao() {
        scan.nextLine();
        System.out.print("Veredito sobre a inscrição. Digite APROVADO ou REJEITADO: ");
        return scan.nextLine().toUpperCase();
    }
    public void avaliarInscricaoResult(boolean result) {
        if (result) System.out.println("Status da inscrição alterada com sucesso.\n");
        else System.out.println("Falha ao alterar status da inscrição.\n");
    }

    public String avaliarCertificadoPendente() {
        scan.nextLine();
        System.out.print("Digite ASSINADO para validar ou RECUSADO para cancelar: ");
        return scan.nextLine();
    }

    public void avaliarCertificadoResult(boolean result) {
        if (result) System.out.println("Certificado avaliado com sucesso.\n");
        else System.out.println("Falha ao alterar status do certificado.\n");
    }

    public void criarPlano() {}

    public String promoverDiscente() {
        scan.nextLine();
        System.out.print("Nome do discente a ser promovido: ");
        String nomeDs = scan.nextLine();
        System.out.print("Nome do grupo onde o discente está: ");
        String nomeGp = scan.nextLine();
        return nomeDs + ";" + nomeGp;
    }

    public void promoverDiscenteResult(boolean result) {
        if (result) System.out.println("Discente promovido com sucesso.\n");
        else System.out.println("Não foi possível promover este discente.\n");
    }

    public String atribuirCargo() {
        scan.nextLine();
        System.out.print("Nome do grupo onde o discente está: ");
        String nomeGp = scan.nextLine();
        System.out.print("Nome do discente a receber o cargo: ");
        String nomeDs = scan.nextLine();
        System.out.print("Informe o cargo (DIRETOR, VICE, TESOUREIRO, MEMBRO): ");
        String cargo = scan.nextLine().toUpperCase();
        return nomeGp + ";" + nomeDs + ";" + cargo;
    }

    public String removerCargo() {
        scan.nextLine();
        System.out.print("Nome do grupo onde o discente está: ");
        String nomeGp = scan.nextLine();
        System.out.print("Nome do discente a ser destituído do cargo: ");
        String nomeDs = scan.nextLine();
        return nomeGp + ";" + nomeDs;
    }

    public void gerenciarCargoResult(boolean result) {
        if (result) System.out.println("Operação de cargo realizada com sucesso.\n");
        else System.out.println("Acesso negado ou dados incorretos. Apenas o responsável pelo grupo pode gerir cargos.\n");
    }

    public String substituirParticipante() {
        scan.nextLine();
        System.out.print("Informe o título da oportunidade: ");
        String titulo = scan.nextLine();
        System.out.print("Nome do discente que será REMOVIDO: ");
        String saindo = scan.nextLine();
        System.out.print("Justificativa para a remoção: ");
        String just = scan.nextLine();
        System.out.print("Nome do discente que ASSUMIRÁ a vaga: ");
        String entrando = scan.nextLine();
        return titulo + ";" + saindo + ";" + just + ";" + entrando;
    }

    public void substituirParticipanteResult(boolean result) {
        if (result) System.out.println("Participante substituído com sucesso na Oportunidade.\n");
        else System.out.println("Falha ao efetuar a substituição. Verifique se os nomes estão corretos e se há alunos pendentes.\n");
    }
}