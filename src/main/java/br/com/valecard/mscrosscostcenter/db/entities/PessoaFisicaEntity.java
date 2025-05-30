package br.com.valecard.mscrosscostcenter.db.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "PESSOA_FISICA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PessoaFisicaEntity extends PessoaEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "cpf", nullable = false, length = 14, unique = true)
    private String cpf;

    @Column(name = "rg", length = 20)
    private String rg;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "sexo", length = 1)
    private String sexo;

    @Column(name = "estado_civil", length = 20)
    private String estadoCivil;

    @Column(name = "nome_mae", length = 255)
    private String nomeMae;

    @Column(name = "nome_pai", length = 255)
    private String nomePai;

    @Override
    public String toString() {
        return "PessoaFisicaEntity{" +
                "id=" + getId() +
                ", cpf='" + cpf + '\'' +
                ", rg='" + rg + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", sexo='" + sexo + '\'' +
                ", estadoCivil='" + estadoCivil + '\'' +
                ", nomeMae='" + nomeMae + '\'' +
                ", nomePai='" + nomePai + '\'' +
                '}';
    }
}