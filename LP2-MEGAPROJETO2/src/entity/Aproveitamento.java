package entity;

public class Aproveitamento {
    private Discente discente;
    private String descricao;
    private String instituicao;
    private int horas;
    private StatusAproveitamento status;
    private String certificado_path;
    //private Usuario avaliador;
    private String motivo_rejeicao;

    public Discente getDiscente() {
        return discente;
    }
    public void setDiscente(Discente discente) {
        this.discente = discente;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getInstituicao() {
        return instituicao;
    }
    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }
    public int getHoras() {
        return horas;
    }
    public void setHoras(int horas) {
        this.horas = horas;
    }
    public StatusAproveitamento getStatus() {
        return status;
    }
    public void setStatus(StatusAproveitamento status) {
        this.status = status;
    }
    public String getCertificado_path() {
        return certificado_path;
    }
    public void setCertificado_path(String certificado_path) {
        this.certificado_path = certificado_path;
    }
    /*public Usuario getAvaliador() {
        return avaliador;
    }
    public void setAvaliador(Usuario avaliador) {
        this.avaliador = avaliador;
    }*/
    public String getMotivo_rejeicao() {
        return motivo_rejeicao;
    }
    public void setMotivo_rejeicao(String motivo_rejeicao) {
        this.motivo_rejeicao = motivo_rejeicao;
    }

    public Aproveitamento(Discente discente, String descricao, int horas, StatusAproveitamento status/*, Usuario avaliador*/) {
        this.discente = discente;
        this.descricao = descricao;
        this.instituicao = "UUUFMA";
        this.horas = horas;
        this.status = status;
        this.certificado_path = "certificado_path";
        //this.avaliador = avaliador;
        this.motivo_rejeicao = "N/A";
    }
}
