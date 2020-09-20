package com.example.starter.dto;

import com.example.starter.model.MobileConta;
import org.springframework.data.domain.Page;

public class MobileContaDTO {
    private String carteiraSUS;
    private String ;

    public MobileContaDTO(MobileConta mobileConta) {
        this.carteiraSUS = mobileConta.getCarteiraSUS();
        this.senha = mobileConta.getSenha();
    }

    public String getSenha() {
        return senha;
    }

    public String getCarteiraSUS() {
        return carteiraSUS;
    }

    public static Page<MobileContaDTO> convert(Page<MobileConta> contas) {
        return contas.map(MobileContaDTO::new);
    }

    public static MobileContaDTO convert (MobileConta conta) {
        return new MobileContaDTO(conta);
    }
}
