package com.REST.example.handler;

import com.REST.example.dto.error.ErrorDetails;
import com.REST.example.dto.error.ValidationError;
import com.REST.example.model.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class RestHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(
            ResourceNotFoundException ex, HttpServletRequest request
    ) {
        ErrorDetails errorDetails = new ErrorDetails();

        errorDetails.setTitle("Resource not found");
        errorDetails.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetails.setDetails(ex.getMessage());
        errorDetails.setDeveloperMessage(ex.getClass().getName());
        errorDetails.setTimeStamp(new Date().getTime());

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(
            MethodArgumentNotValidException ex, HttpServletRequest request
    ) {
        ErrorDetails errorDetails = new ErrorDetails();

        errorDetails.setTitle("Validation failed");
        errorDetails.setStatus(HttpStatus.BAD_REQUEST.value());
        errorDetails.setDetails("Input validation failed");
        errorDetails.setDeveloperMessage(ex.getClass().getName());
        errorDetails.setTimeStamp(new Date().getTime());

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        for(FieldError fieldError : fieldErrors) {
            List<ValidationError> validationErrorList = errorDetails.getErrors()
                    .computeIfAbsent(fieldError.getField(), k -> new ArrayList<>());

            ValidationError validationError = new ValidationError();
            validationError.setConstraint(fieldError.getCode());
            validationError.setMessage(fieldError.getDefaultMessage());
            validationErrorList.add(validationError);
        }

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
