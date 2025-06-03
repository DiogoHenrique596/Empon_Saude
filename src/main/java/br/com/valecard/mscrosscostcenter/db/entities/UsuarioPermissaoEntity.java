package br.com.valecard.mscrosscostcenter.db.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "usuario_permissao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioPermissaoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "usuarioId", column = @Column(name = "usuario_id", nullable = false)),
            @AttributeOverride(name = "permissaoId", column = @Column(name = "permissao_id", nullable = false))
    })
    private UsuarioPermissaoId id;

    @ManyToOne
    @MapsId("usuarioId")
    @JoinColumn(name = "usuario_id", insertable = false, updatable = false)
    private UsuarioEntity usuario;

    @ManyToOne
    @MapsId("permissaoId")
    @JoinColumn(name = "permissao_id", insertable = false, updatable = false)
    private PermissaoEntity permissao;

    @Override
    public String toString() {
        return "UsuarioPermissaoEntity{" +
                "id=" + id +
                ", usuario=" + usuario +
                ", permissao=" + permissao +
                '}';
    }
}