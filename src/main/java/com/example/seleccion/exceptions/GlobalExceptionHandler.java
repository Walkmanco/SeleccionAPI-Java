package com.example.seleccion.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EquipoNoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handlerEquipoException(EquipoNoEncontradoException exception){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage());        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }   

/*    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handlerMethodNotValidException(MethodArgumentNotValidException ex,HttpHeaders httpHeaders,HttpStatus httpStatus,WebRequest wRequest){
        ErrorResponse errorResponse = new ErrorResponse(httpStatus.value(),ex.getMessage(),wRequest.getDescription(false) );
        
        return ResponseEntity.status(httpStatus).
    }*/

     @ExceptionHandler(ResultadoNoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handlerResultadoException(ResultadoNoEncontradoException exception){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage());        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }   

}
