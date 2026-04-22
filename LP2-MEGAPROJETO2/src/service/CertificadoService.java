package service;

import entity.Certificado;
import entity.Discente;
import entity.Oportunidade;
import entity.StatusAssinatura;
import java.util.ArrayList;
import java.util.Objects;

public class CertificadoService {

    ArrayList<Certificado> certificados = new ArrayList<>();

    public boolean certificadoExiste(Discente di, Oportunidade op) {
        for (Certificado cert : certificados) {
            if (Objects.equals(cert.getDiscente(), di) == true && Objects.equals(cert.getOportunidade(), op) == true) {
                return true;
            }
        }
        return false;
    }
    public boolean criarCertificado(Discente di, Oportunidade op) {
        if (!certificadoExiste(di, op)) {
            int horas = op.getCargaHoraria();
            Certificado cert = new Certificado(di, op, horas, "certificado_path"); //TODO: o que é pra ser certificadoPath???
            certificados.add(cert);
            return true;
        }
        return false;
    }
    public void verCertificados() {
        for (Certificado cert : certificados) {
            System.out.printf("- Certificado de participação do discente %s na oportunidade %s. Status: %s\n", cert.getDiscente().getNome(),
                    cert.getOportunidade().getTitulo(), cert.getStatusAssinatura());
        }
    }

    public void verCertificadosPorDiscente(Discente di) {
        for (Certificado cert : certificados) {
            if (Objects.equals(cert.getDiscente(), di) == true) {
                System.out.printf("- (ID: %s) Certificado de participação na oportunidade %s. Status: %s\n", cert.getUuidHash(), cert.getOportunidade().getTitulo(), cert.getStatusAssinatura());
            }
        }
    }

    // Simula a geração do QRCode com base no UUID único do certificado
    public String gerarQRCode(Certificado certificado) {
        String qrData = "QRCODE_DATA://" + certificado.getUuidHash();
        System.out.println("SUCESSO: QR Code gerado para o certificado do aluno: " + certificado.getDiscente().getNome());
        return qrData;
    }

    //Verifica a autenticidade do certificado
    public boolean verificarAutenticidade(String nomeDi, String hashInformado) {
        for (Certificado cert : certificados) {
            if (Objects.equals(cert.getDiscente().getNome(), nomeDi) == true && Objects.equals(cert.getUuidHash(), hashInformado) == true) {
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }

    // Simula a assinatura digital do docente
    public void aprovarCertificado(Certificado cert) {
        cert.setStatusAssinatura(StatusAssinatura.ASSINADO);
    }

    public void recusarCertificado(Certificado cert) {
        cert.setStatusAssinatura(StatusAssinatura.RECUSADO);
    }

    public Certificado pegarCertificado(String nomeDi, String nomeOp) {
        for (Certificado cert : certificados) {
            if (Objects.equals(cert.getDiscente().getNome(), nomeDi) == true && Objects.equals(cert.getOportunidade().getTitulo(), nomeOp) == true) {
                return cert;
            }
        }
        return null;
    }
}
