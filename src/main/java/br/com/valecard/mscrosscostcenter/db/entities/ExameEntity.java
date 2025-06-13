package br.com.valecard.mscrosscostcenter.db.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "EXAME")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExameEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "codigo",unique = true, nullable = false, length = 50)
    private String codigo;

    @Column(name = "descricao", unique = true, nullable = false, length = 255)
    private String descricao;

    @Column(name = "tipo", nullable = false, length = 100)
    private String tipo;

    @Override
    public String toString() {
        return "ExameEntity{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
