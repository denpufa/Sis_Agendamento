package com.example.starter.service;

import com.example.starter.dto.MobileContaDTO;
import com.example.starter.model.MobileConta;
import com.example.starter.repository.MobileContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MobileContaService {

    @Autowired
    private MobileContaRepository mobileContaRepository;

    public boolean salvar (MobileConta mobileConta) {
        try {
            mobileContaRepository.save(mobileConta);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    public void remover (MobileConta mobileConta) {
        MobileConta aSerRemovido = new MobileConta();
        if(mobileConta == null) {
            return;
        }  else {
            aSerRemovido.setMobileConta(mobileContaRepository.findByCarteiraSUS(mobileConta.getCarteiraSUS()));
        }
        mobileContaRepository.deleteById(aSerRemovido.getId());
    }

    public MobileConta buscarPorSUS(String carteiraSUS) {
        if(mobileContaRepository.findByCarteiraSUS(carteiraSUS) != null) {
            return mobileContaRepository.findByCarteiraSUS(carteiraSUS);
        } else {
            throw new NullPointerException();
        }
    }

}



