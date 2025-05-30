package br.com.valecard.mscrosscostcenter.services.impl.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioPermissaoDTO implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private Integer usuarioId;
    private Integer permissaoId;

    @Override
    public String toString() {
        return "UsuarioPermissaoDTO{" +
                "usuarioId=" + usuarioId +
                ", permissaoId=" + permissaoId +
                '}';
    }
}