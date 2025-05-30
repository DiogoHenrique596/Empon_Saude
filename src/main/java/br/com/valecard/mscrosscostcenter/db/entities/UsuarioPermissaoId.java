package br.com.valecard.mscrosscostcenter.db.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioPermissaoId implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer usuario;
    private Integer permissao;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioPermissaoId that = (UsuarioPermissaoId) o;
        return Objects.equals(usuario, that.usuario) &&
                Objects.equals(permissao, that.permissao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, permissao);
    }
}