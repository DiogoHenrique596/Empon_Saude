package br.com.valecard.mscrosscostcenter.db.entities;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ADC_CENTROS_CUSTO_CLIENTE", uniqueConstraints = {@UniqueConstraint(columnNames = {"FILIAL", "CODIGO", "DESCRICAO"}), @UniqueConstraint(columnNames = {"FILIAL", "CODIGO", "CENTRO_CUSTO"})})
@SequenceGenerator(allocationSize = 1, name = "SEQ_ADC_CENTRO_CUSTO", sequenceName = "SEQ_ADC_CENTRO_CUSTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdcClientCostCenterEntity implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_ADC_CENTRO_CUSTO")
    private Long id;

    @Column(name = "FILIAL", nullable = false, precision = 3, scale = 0)
    private Integer branch;

    @Column(name = "CODIGO", nullable = false, precision = 3, scale = 0)
    private Integer code;

    @Column(name = "DESCRICAO", nullable = false, length = 40)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_DESATIVACAO", length = 7)
    private Date deactivationDate;

    @Column(name = "CENTRO_CUSTO", length = 20)
    private String costCenter;

    @Column(name = "CLIENTE_ID", precision = 10, scale = 0)
    private Long clientId;

    @Column(name = "CENTRO_RESULTADO_ID", precision = 10, scale = 0)
    private Long resultCenterId;

    @Column(name = "VIRTUAL", length = 1)
    private String virtual;


    public AdcClientCostCenterEntity( Long id, Integer branch, Integer code, String description ) {
        this.id = id;
        this.branch = branch;
        this.code = code;
        this.description = description;
    }


    @Override
    public String toString() {
        return "AdcClientCostCenterEntity{" +
                "id=" + id +
                ", branch=" + branch +
                ", code=" + code +
                ", description='" + description + '\'' +
                ", deactivationDate=" + deactivationDate +
                ", costCenter='" + costCenter + '\'' +
                ", clientId=" + clientId +
                ", resultCenterId=" + resultCenterId +
                ", virtual='" + virtual + '\'' +
                '}';
    }
}
