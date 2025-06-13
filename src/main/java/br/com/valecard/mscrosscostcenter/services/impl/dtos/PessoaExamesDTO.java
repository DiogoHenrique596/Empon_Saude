package br.com.valecard.mscrosscostcenter.services.impl.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PessoaExamesDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer pessoaId;
    private Integer exameId;
    private LocalDate dataCadastro;

    @Override
    public String toString() {
        return "PessoaExamesDTO{" +
                "pessoaId=" + pessoaId +
                ", exameId=" + exameId +
                ", dataCadastro=" + dataCadastro +
                '}';
    }


}
