package br.com.valecard.mscostcenter.services.dtos;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientCostCenterDTO implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Integer branch;

    private Integer code;


    private String description;
    private Date deactivationDate;

    private String costCenter;

    private Long clientId;

    private Long resultCenterId;

    private String virtual;

}
