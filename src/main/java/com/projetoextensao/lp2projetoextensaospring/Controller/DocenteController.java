package com.projetoextensao.lp2projetoextensaospring.Controller;

import com.projetoextensao.lp2projetoextensaospring.dataTransfer.DocenteData;
import com.projetoextensao.lp2projetoextensaospring.dataTransfer.OportData;
import com.projetoextensao.lp2projetoextensaospring.entity.Discente;
import com.projetoextensao.lp2projetoextensaospring.entity.Docente;
import com.projetoextensao.lp2projetoextensaospring.entity.Oportunidade;
import com.projetoextensao.lp2projetoextensaospring.entity.StatusOportunidade;
import com.projetoextensao.lp2projetoextensaospring.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.micrometer.observation.autoconfigure.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/docente")
public class DocenteController {

    @Autowired
    private DocenteService docenteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Docente criarDocente(DocenteData dt){
        return docenteService.criarDocente(dt);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Oportunidade criarOportunidade(Docente docente, Docente dos, OportData oportData){
        return docenteService.criarOportunidade(docente, dos, oportData);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public boolean publicar(Oportunidade oportunidade, Docente docente, StatusOportunidade status){
        return docenteService.publicar(oportunidade, docente, status);
    }
}
