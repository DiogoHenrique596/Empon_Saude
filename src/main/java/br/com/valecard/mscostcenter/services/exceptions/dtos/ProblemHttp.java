package br.com.valecard.mscostcenter.services.exceptions.dtos;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@AllArgsConstructor
public class ProblemHttp {

    // Default RFC 7807 (Problem Details for HTTP APIs).
    private Integer status;

    private String type;

    private String title;

    private String detail;

    private String message;

    private String instance;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape = STRING)
    private OffsetDateTime timestamp = OffsetDateTime.now();

    private List<ProblemObject> objects;

}
