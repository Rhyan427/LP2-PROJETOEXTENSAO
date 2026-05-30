package service;

import dataTransfer.GrupoData;
import entity.Discente;
import entity.Docente;
import entity.Grupo;
import java.util.ArrayList;
import java.util.HashMap; // NOVO IMPORT NECESSÁRIO
import java.util.Objects;
import entity.HistoricoCargo;
import entity.CargoGrupo;
import java.time.LocalDate;

public class GrupoService {
    ArrayList<Grupo> grupos = new ArrayList<>();

    public void verGrupos() {
        System.out.println("Lista de grupos: ");
        for (Grupo grupo : grupos) {
            System.out.printf("- Grupo %s. Responsável: %s. Membros: ", grupo.getNome(), grupo.getResponsavel().getNome());
            for (Discente di : grupo.getMembros().values()) {
                System.out.printf("%s ", di.getNome());
            }
            System.out.printf("\n");
        }
    }

    public boolean grupoExiste(String nome, String email) {
        for (Grupo grupo : grupos) {
            if ((Objects.equals(grupo.getEmail(), email) == true) || (Objects.equals(grupo.getNome(), nome) == true)) {
                return true;
            }
        }
        return false;
    }

    public Grupo criarGrupo(String nome, String descricao, String email, Docente responsavel) {
        if (!grupoExiste(nome, email) && responsavel != null) {

            HashMap<String, Discente> membros = new HashMap<>();
            Grupo novo = new Grupo(nome, descricao, email, responsavel, membros);
            grupos.add(novo);

            return novo;
        }
        return null;
    }



    public boolean adicionarMembros(Discente di, String nomeGp) {
        for (Grupo grupo : grupos) {
            if (Objects.equals(grupo.getNome(), nomeGp) == true && di != null) {
                if (!membroPresente(di, grupo)) {
                    // CORRIGIDO: Usa .put() e passa o nome como chave
                    grupo.getMembros().put(di.getNome(), di);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean removerMembros(Discente di, String nomeGp) {
        for (Grupo grupo : grupos) {
            if (Objects.equals(grupo.getNome(), nomeGp) == true && di != null) {
                if (membroPresente(di, grupo)) {
                    // CORRIGIDO: Remove passando a chave (nome)
                    grupo.getMembros().remove(di.getNome());
                    return true;
                }
            }
        }
        return false;
    }

    public boolean membroPresente(Discente di, Grupo grupo) {
        // CORRIGIDO: Adicionado .values() para conseguir fazer o 'for' no HashMap
        for (Discente a : grupo.getMembros().values()) {
            if (Objects.equals(a, di) == true) {
                return true;
            }
        }
        return false;
    }

    // RF009
    public boolean atribuirCargo(Docente autor, Grupo grupo, Discente discente, CargoGrupo novoCargo) {
        // 1. Verifica se quem está a dar o cargo é realmente o docente responsável pelo grupo
        if (!Objects.equals(grupo.getResponsavel(), autor)) {
            System.out.println("ERRO: Apenas o docente responsável pelo grupo pode atribuir cargos.");
            return false;
        }

        // 2. Se o discente já tinha um cargo ativo neste grupo, encerra esse cargo antigo
        removerCargo(autor, grupo, discente);

        // 3. Cria o novo registo e adiciona ao histórico do grupo
        HistoricoCargo novoRegisto = new HistoricoCargo(discente, grupo, novoCargo);
        grupo.getHistoricoCargos().add(novoRegisto);

        // Garante que ele está na lista geral de membros ativos (código antigo do Rhyan)
        if (!membroPresente(discente, grupo)) {
            // CORRIGIDO: Usa .put()
            grupo.getMembros().put(discente.getNome(), discente);
        }

        return true;
    }

    // RF009 RF010
    public boolean removerCargo(Docente autor, Grupo grupo, Discente discente) {
        if (!Objects.equals(grupo.getResponsavel(), autor)) return false;

        boolean encontrouAtivo = false;
        for (HistoricoCargo hc : grupo.getHistoricoCargos()) {

            if (Objects.equals(hc.getDiscente(), discente) && hc.getDataFim() == null) {
                hc.setDataFim(LocalDate.now());
                encontrouAtivo = true;
            }
        }

        // Remove da lista de membros ativos atuais
        if (membroPresente(discente, grupo)) {
            // CORRIGIDO: Usa .remove() com a chave
            grupo.getMembros().remove(discente.getNome());
        }

        return encontrouAtivo;
    }

    // RF010
    public void verHistoricoDeCargos(Grupo grupo) {
        System.out.println("=== Histórico de Cargos: Grupo " + grupo.getNome() + " ===");
        for (HistoricoCargo hc : grupo.getHistoricoCargos()) {
            String dataFimStr = (hc.getDataFim() == null) ? "Atual" : hc.getDataFim().toString();
            System.out.printf("- %s: %s (De %s até %s)\n",
                    hc.getCargo(), hc.getDiscente().getNome(), hc.getDataInicio(), dataFimStr);
        }
    }

    public boolean isDiretorAtivo(Discente di) {
        for (Grupo grupo : grupos) {
            for (HistoricoCargo hc : grupo.getHistoricoCargos()) {
                if (Objects.equals(hc.getDiscente(), di) &&
                        hc.getCargo() == CargoGrupo.DIRETOR &&
                        hc.getDataFim() == null) {
                    return true;
                }
            }
        }
        return false;
    }




    // 1. Método que o DiscenteView está a tentar chamar para pedir um grupo novo
    public void criarSolicitacao(GrupoData data) {
        // Como não sabemos exatamente onde eles querem guardar os pendentes,
        // você pode adaptar isto depois. Por enquanto, criamos o grupo e marcamos como INATIVO.
        if (!grupoExiste(data.getNome(), data.getEmail()) && data.getResponsavel() != null) {
            HashMap<String, Discente> membros = new HashMap<>();
            Grupo novo = new Grupo(data.getNome(), data.getDescricao(), data.getEmail(), data.getResponsavel(), membros);

            // Aqui seria ideal ter um StatusGrupo.PENDENTE, mas como só temos ATIVO e INATIVO:
            novo.setStatus(entity.StatusGrupo.INATIVO);
            grupos.add(novo);
            System.out.println("Solicitação do grupo " + data.getNome() + " enviada para avaliação.");
        }
    }

    public ArrayList<Grupo> listarGruposPendentes(HashMap<String, Grupo> mapaDeGrupos) {
        ArrayList<Grupo> listaPendentes = new ArrayList<>();

        for (Grupo g : mapaDeGrupos.values()) {
            if (g.getStatus() == entity.StatusGrupo.INATIVO) {
                listaPendentes.add(g);
            }
        }
        return listaPendentes;
    }


    public boolean avaliarSolicitacao(Grupo grupo, boolean aprovado) {
        if (grupo != null) {
            if (aprovado) {
                grupo.setStatus(entity.StatusGrupo.ATIVO);
                System.out.println("O grupo " + grupo.getNome() + " foi APROVADO e está ativo!");
                return true;
            } else {
                // Se foi rejeitado, removemos da lista de grupos
                grupos.remove(grupo);
                System.out.println("O grupo " + grupo.getNome() + " foi REJEITADO.");
                return true; // Retornamos true para indicar que a ação de avaliar foi concluída
            }
        }
        return false; // Falhou porque o grupo não existe
    }

}