package com.example.starter.controller;

import com.example.starter.dto.MedicoDTO;
import com.example.starter.form.MedicoForm;
import com.example.starter.model.Especialidade;
import com.example.starter.model.Medico;
import com.example.starter.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping("/listar")
    public ResponseEntity<Page<MedicoDTO>> listarTodos(@PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC, sort = "nomeMedico")
                                                                   Pageable pageable) {
        try {
            List<Medico> medicos = medicoService.listarTodos();
            Page<MedicoDTO> medicosDTO = converterListToPageMedicoDTO(medicos,pageable);
            return ResponseEntity.ok(medicosDTO);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/listarPorEspecialidade")
    public ResponseEntity<Page<MedicoDTO>> listarTodosPorEspecialidade(@RequestParam String nomeEspecialidade,
                                                       @PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC, sort = "nomeMedico")
                                                               Pageable pageable) {
        try {
            List<Medico> medicos = medicoService.listarPorEspecialidade(new Especialidade(nomeEspecialidade));
            Page<MedicoDTO> medicosDTO = converterListToPageMedicoDTO(medicos,pageable);
            return ResponseEntity.ok(medicosDTO);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/listarPorNome")
    public ResponseEntity<Page<MedicoDTO>> listarPorNome(@RequestParam String nomeMedico,
                                                       @PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC, sort = "nomeMedico")
                                                               Pageable pageable) {
        try {
            List<Medico> medicos = medicoService.buscarPorNome(new Medico(nomeMedico));
            Page<MedicoDTO> medicosDTO = converterListToPageMedicoDTO(medicos,pageable);
            return ResponseEntity.ok(medicosDTO);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/buscarPorCredenciais")
    public ResponseEntity<MedicoDTO> buscarPorConselho(@RequestParam String numeroConselho,
                                                         @PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC, sort = "nomeMedico")
                                                                 Pageable pageable) {
        try {
            Medico medico = new Medico();
            medico.setNumeroConselho(numeroConselho);
            MedicoDTO medicoDTO = new MedicoDTO(medicoService.buscarPorNumeroConselho(medico));
            return ResponseEntity.ok(medicoDTO);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<MedicoDTO> cadastrarMedico(@RequestBody @Valid MedicoForm medicoForm,
                                                     UriComponentsBuilder uriComponentsBuilder) {
        try{
            Medico medico = medicoService.salvar(medicoForm);
            URI uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
            return ResponseEntity.created(uri).body(new MedicoDTO(medico));
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/atualizarMedico")
    @Transactional
    public ResponseEntity<MedicoDTO> atualizarMedico(@RequestParam String numeroConselho, @RequestBody @Valid MedicoForm medicoForm) {
        try{
            Medico medico = medicoService.atualizar(numeroConselho, medicoForm);
            return ResponseEntity.ok(new MedicoDTO(medico));
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/removerMedico")
    @Transactional
    public ResponseEntity<?> removerMedico(@RequestParam String numeroConselho) {
        try {
            Medico medico = new Medico();
            medico.setNumeroConselho(numeroConselho);
            medicoService.deletar(medico);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    private Page<MedicoDTO> converterListToPageMedicoDTO(List<Medico> medicos, Pageable pageable) {
        List<MedicoDTO> listaMedicos = new ArrayList<>();
        for (Medico m:
             medicos) {
            listaMedicos.add(new MedicoDTO(m.getNomeMedico(),m.getConselho(),m.getNumeroConselho(),m.getEspecialidades()));
        }
        return new PageImpl<>(listaMedicos,pageable,listaMedicos.size());
    }
}
