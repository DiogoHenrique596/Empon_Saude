package br.com.valecard.mscrosscostcenter.services.impl.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrcamentoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private PessoaExamesDTO pessoaExames;
    private PessoaJuridicaDTO pessoaJuridica;
    private BigDecimal valor;
    private LocalDate dataOrcamento;

    @Override
    public String toString() {
        return "OrcamentoDTO{" +
                "id=" + id +
                ", pessoaExames=" + pessoaExames +
                ", pessoaJuridica=" + pessoaJuridica +
                ", valor=" + valor +
                ", dataOrcamento=" + dataOrcamento +
                '}';
    }
}
