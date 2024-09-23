package com.ebr.proposta_app.controller;

import com.ebr.proposta_app.dto.PropostaDTO;
import com.ebr.proposta_app.service.PropostaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/proposta")
@CrossOrigin("*")
public class PropostaController {

    private final PropostaService propostaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PropostaDTO criar(@RequestBody PropostaDTO propostaDTO) {
        return propostaService.salvar(propostaDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PropostaDTO> obterPropostas() {
        return propostaService.obterPropostas();
    }

}
