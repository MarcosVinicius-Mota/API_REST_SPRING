package com.teste.demo.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "GMT-3")
    private Instant timestamp;
    private String message;
    private String details;

}
