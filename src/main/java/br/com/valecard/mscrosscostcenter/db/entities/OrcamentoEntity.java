package br.com.valecard.mscrosscostcenter.db.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "ORCAMENTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrcamentoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "pessoa_fisica", referencedColumnName = "pessoa_fisica"),
            @JoinColumn(name = "exames", referencedColumnName = "exames"),
            @JoinColumn(name = "data_cadastro", referencedColumnName = "data_cadastro")
    })
    private PessoaExamesEntity pessoaExames;

    @ManyToOne
    @JoinColumn(name = "pessoa_juridica", referencedColumnName = "id", nullable = false)
    private PessoaJuridicaEntity pessoaJuridica;

    @Column(name = "valor", nullable = false, precision = 15, scale = 2)
    private BigDecimal valor;

    @Column(name = "data_orcamento", nullable = false)
    private LocalDate dataOrcamento;

    @Override
    public String toString() {
        return "OrcamentoEntity{" +
                "id=" + id +
                ", pessoaExames=" + pessoaExames +
                ", pessoaJuridica=" + pessoaJuridica +
                ", valor=" + valor +
                ", dataOrcamento=" + dataOrcamento +
                '}';
    }
}