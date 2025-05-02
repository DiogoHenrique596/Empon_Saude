package br.com.valecard.mscostcenter.services.exceptions.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProblemObject {
    private String name;
    private String detail;
}
