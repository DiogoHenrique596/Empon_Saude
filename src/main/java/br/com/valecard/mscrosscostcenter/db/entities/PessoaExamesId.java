package br.com.valecard.mscrosscostcenter.db.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PessoaExamesId implements Serializable {
    private static final long serialVersionUID = 1L;


    private Integer pessoaFisica;

    private Integer exames;

    @Column(name = "data_cadastro")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataCadastro;


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PessoaExamesId that = (PessoaExamesId) o;
        return Objects.equals(pessoaFisica, that.pessoaFisica) && Objects.equals(exames, that.exames) && Objects.equals(dataCadastro, that.dataCadastro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pessoaFisica, exames, dataCadastro);
    }
}
