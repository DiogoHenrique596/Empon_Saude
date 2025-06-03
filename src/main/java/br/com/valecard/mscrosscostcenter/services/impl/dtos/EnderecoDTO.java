package br.com.valecard.mscrosscostcenter.services.impl.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer pessoaId;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private String pais = "Brasil";

    @Override
    public String toString() {
        return "EnderecoDTO{" +
                "id=" + id +
                ", pessoaId=" + pessoaId +
                ", logradouro='" + logradouro + '\'' +
                ", numero='" + numero + '\'' +
                ", complemento='" + complemento + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", cep='" + cep + '\'' +
                ", pais='" + pais + '\'' +
                '}';
    }
}