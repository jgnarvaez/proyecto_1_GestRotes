package co.edu.unicauca.gesrotesbackend.exceptions;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import java.util.List;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class DefaultExceptionHandler {

    /**
     *  Maneja las excepciones generales, Exception y RuntimeException
     *  
     *  @param e : La excepción que se ha producido. 
     *  @param request : La solicitud HttpServletRequest que provocó la excepción.
     *  @return Una ResponseEntity que contiene un objeto ApiError con los detalles del error.
     */
    @ExceptionHandler({Exception.class, RuntimeException.class})
    public ResponseEntity<ApiError> handleException(Exception e,
                                                    HttpServletRequest request){
        ApiError apiError = new ApiError(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now()
        );
        
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     *  Maneja las excepciones relaciondas a validaciones de variables de ruta recibidas
     *  
     *  @param e : La excepción MethodArgumentNotValidException que se ha producido. 
     *  @param request : La solicitud HttpServletRequest que provocó la excepción.
     *  @return Una ResponseEntity que contiene un objeto ApiError con los detalles del error.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleException(MethodArgumentNotValidException e, HttpServletRequest request) {
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
    
        List<String> errorMessages = fieldErrors.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
    
        String errorMessage = String.join(", ", errorMessages);
    
        ApiError apiError = new ApiError(
                request.getRequestURI(),
                errorMessage,
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );
    
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }


    /**
     *  Maneja las excepciones relaciondas a validaciones de JSON recibidos
     *  
     *  @param e : La excepción ConstraintViolationException que se ha producido. 
     *  @param request : La solicitud HttpServletRequest que provocó la excepción.
     *  @return Una ResponseEntity que contiene un objeto ApiError con los detalles del error.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handleException(ConstraintViolationException e, HttpServletRequest request) {
        List<String> errorMessages = e.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());

        String errorMessage = String.join(", ", errorMessages); // Unir los mensajes con ", " como separador

        ApiError apiError = new ApiError(
                request.getRequestURI(),
                errorMessage,
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    /**
     *  Maneja las excepciones tipo HTTPException
     *  
     *  @param e : La excepción HTTPException que se ha producido. 
     *  @param request : La solicitud HttpServletRequest que provocó la excepción.
     *  @return Una ResponseEntity que contiene un objeto ApiError con los detalles del error.
     */
    @ExceptionHandler(HTTPException.class)
    public ResponseEntity<ApiError> handleException(HTTPException e, HttpServletRequest request){
        ApiError apiError = new ApiError(
                request.getRequestURI(),
                e.getMessage(),
                e.getStatusCode(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(apiError.statusCode()).body(apiError);
    }
}