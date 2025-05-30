package br.com.valecard.mscrosscostcenter.db.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PessoaJuridicaEntity extends PessoaEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "cnpj", nullable = false, length = 18, unique = true)
    private String cnpj;

    @Column(name = "razao_social", nullable = false, length = 255)
    private String razaoSocial;

    @Column(name = "nome_fantasia", length = 255)
    private String nomeFantasia;

    @Column(name = "inscricao_estadual", length = 30)
    private String inscricaoEstadual;

    @Column(name = "inscricao_municipal", length = 30)
    private String inscricaoMunicipal;

    @Column(name = "data_abertura")
    private LocalDate dataAbertura;

    @Override
    public String toString() {
        return "PessoaJuridicaEntity{" +
                "id=" + getId() +
                ", cnpj='" + cnpj + '\'' +
                ", razaoSocial='" + razaoSocial + '\'' +
                ", nomeFantasia='" + nomeFantasia + '\'' +
                ", inscricaoEstadual='" + inscricaoEstadual + '\'' +
                ", inscricaoMunicipal='" + inscricaoMunicipal + '\'' +
                ", dataAbertura=" + dataAbertura +
                '}';
    }
}