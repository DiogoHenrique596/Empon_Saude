package br.com.valecard.mscrosscostcenter.db.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "PESSOA")
@Inheritance(strategy = InheritanceType.JOINED)
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
    @Pattern(regexp = "[FJ]", message = "Tipo deve ser 'F' (Física) ou 'J' (Jurídica)")
    private String tipo;

    @Column(name = "nome", nullable = false, length = 255)
    private String nome;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "data_cadastro", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime dataCadastro;

    @Column(name = "telefone", length = 20)
    private String telefone;

    @Column(name = "telefone_fixo", length = 20)
    private String telefoneFixo;

    @PrePersist
    public void prePersist() {
        if (this.dataCadastro == null) {
            this.dataCadastro = LocalDateTime.now();
        }
    }


    @Override
    public String toString() {
        return "PessoaEntity{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", nome='" + nome + '\'' +
                ", dataCadastro=" + dataCadastro +
                ", telefone='" + telefone + '\'' +
                ", telefone_fixo='" + telefoneFixo + '\'' +
                '}';
    }
}
