package com.sanketh.AIChatBot.Exception;

import com.sanketh.AIChatBot.DTO.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {
   @ExceptionHandler(UserNotFoundException.class)
   public ResponseStructure handleUserNotFoundException(UserNotFoundException ex) {
       return new ResponseStructure(ex.getMessage(), HttpStatus.NOT_FOUND.value());
   }
   public ResponseStructure handleRegistrationFailedException(RegistrationFailedException ex) {
       return new ResponseStructure(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
   }
}



