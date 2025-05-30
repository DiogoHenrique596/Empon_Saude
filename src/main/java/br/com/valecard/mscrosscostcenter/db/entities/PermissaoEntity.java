package br.com.valecard.mscrosscostcenter.db.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "PERMISSAO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PermissaoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", nullable = false, unique = true, length = 50)
    private String nome;

    @Override
    public String toString() {
        return "PermissaoEntity{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}