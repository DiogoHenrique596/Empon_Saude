package br.com.valecard.mscrosscostcenter.db.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "usuario_permissao")
@IdClass(UsuarioPermissaoId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioPermissaoEntity implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity usuario;

    @Id
    @ManyToOne
    @JoinColumn(name = "permissao_id", nullable = false)
    private PermissaoEntity permissao;

    @Override
    public String toString() {
        return "UsuarioPermissaoEntity{" +
                "usuario=" + usuario +
                ", permissao=" + permissao +
                '}';
    }
}
