package br.com.valecard.mscrosscostcenter.db.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "PESSOA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PessoaEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tipo", nullable = false, length = 1)
    private String tipo;

    @Column(name = "nome", nullable = false, length = 255)
    private String nome;

    @Column(name = "data_cadastro", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime dataCadastro;

    @Column(name = "telefone", length = 20)
    private String telefone;

    @Column(name = "telefone_fixo", length = 20)
    private String telefone_fixo;

    @Override
    public String toString() {
        return "PessoaEntity{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", nome='" + nome + '\'' +
                ", dataCadastro=" + dataCadastro +
                ", telefone='" + telefone + '\'' +
                ", telefone_fixo='" + telefone_fixo + '\'' +
                '}';
    }
}
