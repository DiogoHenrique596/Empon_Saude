package br.com.valecard.mscrosscostcenter.db.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "PESSOA_EXAMES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PessoaExamesEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private PessoaExamesId id;

    @ManyToOne
    @MapsId("pessoaFisica")
    @JoinColumn(name = "pessoa_fisica", referencedColumnName = "pessoa_id")
    private PessoaFisicaEntity pessoaFisica;

    @ManyToOne
    @MapsId("exames")
    @JoinColumn(name = "exames", referencedColumnName = "id")
    private ExameEntity exames;

    @Override
    public String toString() {
        return "PessoaExamesEntity{" +
                "id=" + id +
                ", pessoaFisica=" + pessoaFisica +
                ", exames=" + exames +
                '}';
    }
}
