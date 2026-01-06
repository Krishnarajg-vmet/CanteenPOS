package com.teamsynk.canteenpos.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

   private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

   @ExceptionHandler(ResourceNotFoundException.class)
   public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
       logger.error("Resource not found: {}", ex.getMessage());
       ErrorResponse errorResponse = new ErrorResponse(
           ex.getMessage(),
           "The requested resource could not be found"
       );
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
   }

   @ExceptionHandler(BusinessException.class)
   public ResponseEntity<ErrorResponse> handleBusiness(BusinessException ex) {
       logger.error("Business exception: {}", ex.getMessage());
       ErrorResponse errorResponse = new ErrorResponse(
           ex.getMessage(),
           "There was an error with business logic"
       );
       return ResponseEntity.badRequest().body(errorResponse);
   }

   @ExceptionHandler(Exception.class)
   public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
       logger.error("An unexpected error occurred: {}", ex.getMessage(), ex);
       ErrorResponse errorResponse = new ErrorResponse(
           "An unexpected error occurred",
           "Please contact support"
       );
       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
   }
}
