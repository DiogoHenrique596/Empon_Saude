package br.com.valecard.mscrosscostcenter.db.entities;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UsuarioPermissaoId implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer usuarioId;
    private Integer permissaoId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioPermissaoId that = (UsuarioPermissaoId) o;
        return Objects.equals(usuarioId, that.usuarioId) &&
                Objects.equals(permissaoId, that.permissaoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioId, permissaoId);
    }
}