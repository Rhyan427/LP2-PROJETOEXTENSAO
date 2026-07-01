package com.projetoextensao.lp2projetoextensaospring.Controller;

import com.projetoextensao.lp2projetoextensaospring.dataTransfer.CertData;
import com.projetoextensao.lp2projetoextensaospring.entity.Certificado;
import com.projetoextensao.lp2projetoextensaospring.entity.Discente;
import com.projetoextensao.lp2projetoextensaospring.entity.Oportunidade;
import com.projetoextensao.lp2projetoextensaospring.service.CertificadoService;
import com.projetoextensao.lp2projetoextensaospring.service.DiscenteService;
import com.projetoextensao.lp2projetoextensaospring.service.OportunidadeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/certificados")
public class CertificadoController {

    @Autowired
    private CertificadoService certificadoService;

    @Autowired
    private DiscenteService discenteService;

    @Autowired
    private OportunidadeService oportunidadeService;

    // Rota: POST /certificados/emitir
    @PostMapping("/emitir")
    @ResponseStatus(HttpStatus.CREATED)
    public Certificado emitirCertificado(@RequestBody @Valid CertData data) {

        Discente discente = discenteService.buscarPorId(data.getIdDiscente())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Discente não encontrado."));

        Oportunidade oportunidade = oportunidadeService.buscarPorId(data.getIdOportunidade())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Oportunidade não encontrada."));

        return certificadoService.criarCertificado(discente, oportunidade);
    }

    // Rota: GET /certificados/{uuid}
    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public Certificado buscarPorUuid(@PathVariable String uuid) {
        Certificado certificado = certificadoService.buscarPorUuid(uuid);
        if (certificado == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Certificado não encontrado ou UUID inválido.");
        }
        return certificado;
    }
}