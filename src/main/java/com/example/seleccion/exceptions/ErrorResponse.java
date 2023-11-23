package com.example.seleccion.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ErrorResponse {
    private int statusCode;
    private String mensaje;
}
