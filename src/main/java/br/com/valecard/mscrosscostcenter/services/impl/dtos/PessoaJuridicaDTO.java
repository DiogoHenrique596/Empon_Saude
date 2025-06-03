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
public class PessoaJuridicaDTO extends PessoaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cnpj;
    private String inscricaoEstadual;
    private String inscricaoMunicipal;
    private String razaoSocial;
    private String nomeFantasia;

    @Override
    public String toString() {
        return "PessoaJuridicaDTO{" +
                "id=" + getId() +
                ", tipo='" + getTipo() + '\'' +
                ", nome='" + getNome() + '\'' +
                ", dataCadastro=" + getDataCadastro() +
                ", telefone='" + getTelefone() + '\'' +
                ", telefoneFixo='" + getTelefoneFixo() + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", inscricaoEstadual='" + inscricaoEstadual + '\'' +
                ", inscricaoMunicipal='" + inscricaoMunicipal + '\'' +
                ", razaoSocial='" + razaoSocial + '\'' +
                ", nomeFantasia='" + nomeFantasia + '\'' +
                '}';
    }
}