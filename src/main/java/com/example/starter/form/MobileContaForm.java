package com.example.starter.form;

import com.example.starter.model.MobileConta;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class MobileContaForm {
    @NotEmpty @NotNull @Length(max = 12)
    private String senha;
    @NotEmpty @NotNull
    private String carteiraSUS;

    public String getSenha() {
        return senha.toUpperCase();
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCarteiraSUS() {
        return carteiraSUS;
    }

    public void setCarteiraSUS(String carteiraSUS) {
        this.carteiraSUS = carteiraSUS;
    }

    public String getCarteiraSUS() {
        return carteiraSUS;
    }

    public void setCarteiraSUS(String carteiraSUS) {
        this.carteiraSUS = carteiraSUS;
    }

    public MobileConta convert() {
        return new MobileConta(getCarteiraSUS(), getsenha().toUpperCase());
    }
}
}

