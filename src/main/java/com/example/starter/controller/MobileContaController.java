package com.example.starter.controller;

import com.example.starter.dto.MobileContaDTO;
import com.example.starter.form.MobileContaFORM;
import com.example.starter.model.MobileConta;
import com.example.starter.service.MobileContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;


@RestController
@RequestMapping(path = "/mobile")
public class MobileContaController {
    @Autowired
    private MobileContaService mobileContaService;

    @GetMapping(path = "/buscarCadastroSUS/{carteiraSUS}")
    public ResponseEntity<MobileContaDTO> buscarCadastroSUS(@PathVariable String carteiraSUS) {
        try{
            MobileConta mobileConta = mobileContaService.buscarPorSUS(carteiraSUS);
            return ResponseEntity.ok(MobileContaDTO.convert(mobileConta));
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    //falta configurar nesse post para ele verificar se ja tem se tiver remover (rodolfo aqui ,so pra dizer que ainda vou fazer isso por isso nao coloquei um metodo de atualizar)
    @PostMapping
    @Transactional
    public ResponseEntity<MobileContaDTO> cadastrarMobileConta(@RequestBody @Valid MobileContaFORM mobileContaForm, UriComponentsBuilder uriComponentsBuilder) {
        try {
            mobileContaService.salvar(mobileContaForm.convert());
            MobileConta mobileConta = mobileContaService.buscarPorSUS(mobileContaForm.getCarteiraSUS());
            URI uri = uriComponentsBuilder.path("/mobile/{id}").buildAndExpand(mobileConta.getId()).toUri();
            return ResponseEntity.created(uri).body(new MobileContaDTO(mobileConta));
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }






}

