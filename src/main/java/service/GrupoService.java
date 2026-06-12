package service;

import dataTransfer.GrupoData;
import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.GrupoRepo;
import repository.HistoricoCargoRepo;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
public class GrupoService {

    @Autowired
    private GrupoRepo grupoRepository;

    @Autowired
    private HistoricoCargoRepo historicoCargoRepository;

    public List<Grupo> listarGrupos(){
        return grupoRepository.findAll();
    }

    public boolean grupoExiste(String nome, String email){
        return grupoRepository.existsByNomeOrEmail(nome, email);
    }

    public Grupo criarGrupo(GrupoData data) {
        if (grupoExiste(data.getNome(), data.getEmail()) || data.getResponsavel() == null) {
            return null;
        }

        Grupo novo = new Grupo(data.getNome(),
                data.getDescricao(),
                data.getEmail(),
                data.getResponsavel());

        novo.setStatus(StatusGrupo.ATIVO);
        return grupoRepository.save(novo);
    }



    public boolean adicionarMembros(Discente di, Grupo grupo) {
        if(di == null || grupo == null || membroPresente(di, grupo)) {
            return false;
        }
        HistoricoCargo novo = new HistoricoCargo(di, grupo, CargoGrupo.MEMBRO);
        historicoCargoRepository.save(novo);
        return true;
    }

    public boolean removerMembros(Discente di, Grupo grupo) {
        List<HistoricoCargo> ativos = historicoCargoRepository.findAtivosByGrupoAndDiscente(grupo, di);
        if(ativos.isEmpty()){
            return false;
        }

        for(HistoricoCargo hc : ativos){
            hc.setDataFim(LocalDate.now());
            historicoCargoRepository.save(hc);
        }
        return true;
    }

    public boolean membroPresente(Discente di, Grupo grupo) {
        return historicoCargoRepository.existsByDiscenteAndGrupoAndDataFimIsNull(di, grupo);
    }

    // RF009
    public boolean atribuirCargo(Docente autor, Grupo grupo, Discente discente, CargoGrupo novoCargo) {
        if(!Objects.equals(grupo.getResponsavel(), autor)){
            return false;
        }
        removerCargo(autor, grupo, discente);

        HistoricoCargo novoRegistro = new HistoricoCargo(discente, grupo, novoCargo);
        historicoCargoRepository.save(novoRegistro);
        return true;
    }

    // RF009 RF010
    public boolean removerCargo(Docente autor, Grupo grupo, Discente discente) {
        if(!Objects.equals(grupo.getResponsavel(), autor)){
            return false;
        }

        List<HistoricoCargo> ativos = historicoCargoRepository.findAtivosByGrupoAndDiscente(grupo, discente);
        for(HistoricoCargo hc : ativos){
            hc.setDataFim(LocalDate.now());
            historicoCargoRepository.save(hc);
        }
        return !ativos.isEmpty();
    }

    // RF010
    public List<HistoricoCargo> buscarHistorico(Grupo grupo){
        return historicoCargoRepository.findByGrupo(grupo);
    }

    public boolean isDiretorAtivo(Discente di) {
        return historicoCargoRepository.existsByDiscenteAndCargoAndDataFimIsNull(di, CargoGrupo.DIRETOR);
    }

    // 1. Método que o DiscenteView está a tentar chamar para pedir um grupo novo
    public void criarSolicitacao(GrupoData data) {
        // Como não sabemos exatamente onde eles querem guardar os pendentes,
        // você pode adaptar isto depois. Por enquanto, criamos o grupo e marcamos como INATIVO.
        if (!grupoExiste(data.getNome(), data.getEmail()) && data.getResponsavel() != null) {
            Grupo novo = new Grupo(
                    data.getNome(),
                    data.getDescricao(),
                    data.getEmail(),
                    data.getResponsavel()
            );

            // Aqui seria ideal ter um StatusGrupo.PENDENTE, mas como só temos ATIVO e INATIVO:
            novo.setStatus(StatusGrupo.INATIVO);
            grupoRepository.save(novo);
            System.out.println("Solicitação do grupo " + data.getNome() + " enviada para avaliação.");
        }
    }

    public List<Grupo> listarGruposPendentes(HashMap<String, Grupo> mapaDeGrupos) {
        return grupoRepository.findByStatus(StatusGrupo.INATIVO);
    }


    public boolean avaliarSolicitacao(Grupo grupo, boolean aprovado) {
        if (grupo != null) {
            if (aprovado) {
                grupo.setStatus(StatusGrupo.ATIVO);
                System.out.println("O grupo " + grupo.getNome() + " foi APROVADO e está ativo!");
                grupoRepository.save(grupo);
                return true;
            } else {
                // Se foi rejeitado, removemos da lista de grupos
                grupoRepository.delete(grupo);
                System.out.println("O grupo " + grupo.getNome() + " foi REJEITADO.");
                return true; // Retornamos true para indicar que a ação de avaliar foi concluída
            }
        }
        return false; // Falhou porque o grupo não existe
    }
}