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
public class PessoaFisicaDTO extends PessoaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cpf;
    private String rg;
    private LocalDate dataNascimento;
    private String sexo;
    private String estadoCivil;
    private String nomeMae;
    private String nomePai;

    @Override
    public String toString() {
        return "PessoaFisicaDTO{" +
                "cpf='" + cpf + '\'' +
                ", rg='" + rg + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", sexo='" + sexo + '\'' +
                ", estadoCivil='" + estadoCivil + '\'' +
                ", nomeMae='" + nomeMae + '\'' +
                ", nomePai='" + nomePai + '\'' +
                '}';
    }
}