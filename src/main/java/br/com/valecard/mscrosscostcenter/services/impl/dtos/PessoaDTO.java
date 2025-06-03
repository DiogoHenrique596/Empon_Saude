package br.com.valecard.mscrosscostcenter.services.impl.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PessoaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String tipo;
    private String nome;
    private LocalDateTime dataCadastro;
    private String telefone;
    private String telefoneFixo;

    @Override
    public String toString() {
        return "PessoaDTO{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", nome='" + nome + '\'' +
                ", dataCadastro=" + dataCadastro +
                ", telefone='" + telefone + '\'' +
                ", telefoneFixo='" + telefoneFixo + '\'' +
                '}';
    }
}