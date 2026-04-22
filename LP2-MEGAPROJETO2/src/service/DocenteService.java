package service;

import entity.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;


public class DocenteService {
    ArrayList<Docente> docentes = new ArrayList<>();
    private CertificadoService certificadoService = new CertificadoService();

    public boolean criarDocente(String nome, String email, String senha, String siape, String departamento) {
        if (!docenteExiste(nome, email, siape)) {
            Docente docente = new Docente(nome, email, senha, siape, departamento);
            docentes.add(docente);
            return true;
        }
        return false;
    }
    public boolean docenteExiste(String nome, String email, String siape) {
        for (Docente doc : docentes) {
            if ((Objects.equals(doc.getNome(), nome) == true) || (Objects.equals(doc.getEmail(), email) == true) || (Objects.equals(doc.getSiape(), siape) == true)) {
                return true;
            }
        }
        return false;
    }

    public boolean loginDocente(Docente in, String nome, String senha) {
        for (Docente doc : docentes) {
            if ((Objects.equals(doc.getNome(), nome) == true) && (Objects.equals(doc.getSenha(), senha) == true)) {
                repasseDadosLogin(in, doc);
                return true;
            }
        }
        return false;
    }
    public void repasseDadosLogin(Docente in, Docente doc) {
        in.setNome(doc.getNome());
        in.setEmail(doc.getEmail());
        in.setSenha(doc.getSenha());
        in.setAtivo(doc.isAtivo());
        in.setPapel(doc.getPapel());
        in.setSiape(doc.getSiape());
        in.setDepartamento(doc.getDepartamento());
    }

    //aqui é criada uma nova oportunidade para ser publicada no OportunidadeService
    public Oportunidade criarOportunidade(Docente autor, String titulo, String descricao, TipoOportunidade tipo, Modalidade modalidade, int cargaHoraria, int vagas, LocalDate inicio, LocalDate fim) {
        // checa se existem para prosseguir para criar a nova oportunidade
        if (autor == null || titulo == null || inicio == null || fim == null) {
            return null;
        }
        // passou? Nova oportunidade criada
        Oportunidade novaOportunidade = new Oportunidade(titulo, descricao, tipo, modalidade, cargaHoraria, vagas, LocalDate.now(), inicio, fim, autor, autor);
        return novaOportunidade;
    }

    public boolean registrarPlanoAtividades(Docente autor, Oportunidade oportunidade, LocalDate date) {
        // checa se a oportunidade, autor ou data existem
        if (oportunidade == null || autor == null || date == null) {
            return false;
        }

        // o plano de atividades é registrado se o responsavel pela oportunidade for
        // igual ao docente querendo criar o plano
        if (oportunidade.getResponsavel() != null && oportunidade.getResponsavel().equals(autor)){
            oportunidade.setDataPlanoAtividades(date);

            System.out.println("Plano registrado com sucesso");
            return true;
        }
        return false;
    } //TODO: utilizar essa função futuramente

    // basicamente ele só recebe o certificado, chama o métodо de certificadoService
    // e printa por quem ele foi assinado, já que lá isso não acontece
    public boolean aprovarCertificado(Docente autor, Certificado certificado) {
        if (autor == null || certificado == null) {
            return false;
        }

        certificadoService.aprovarCertificado(certificado);
        return true;
    }

    //Recusa o certificado
    public boolean recusarCertificado(Docente autor, Certificado certificado) {
        if (autor == null || certificado == null) {
            return false;
        }

        certificadoService.recusarCertificado(certificado);

        return true;
    }

    public void exibirInfo(Docente docente) {
        if (docente == null) {
            System.out.println("Erro: Docente não encontrado para exibição.");
            return;
        }

        //simplesmente printa os dados do docente
        System.out.println("\n=== Dados do Docente ===");
        System.out.println("Nome:         " + docente.getNome());
        System.out.println("Email:        " + docente.getEmail());
        System.out.println("SIAPE:        " + docente.getSiape());
        System.out.println("Departamento: " + docente.getDepartamento());
    }

    public Docente pegarDocente(String nome) {
        for (Docente doc : docentes) {
            if (Objects.equals(doc.getNome(), nome) == true) {
                return doc;
            }
        }
        return null;
    }
}
