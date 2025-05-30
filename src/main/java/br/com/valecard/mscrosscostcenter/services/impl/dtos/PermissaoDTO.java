package br.com.valecard.mscrosscostcenter.services.impl.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PermissaoDTO implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;
    private String descricao;

    @Override
    public String toString() {
        return "PermissaoDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}