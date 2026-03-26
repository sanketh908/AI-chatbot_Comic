package com.sanketh.AIChatBot.Exception;

import com.sanketh.AIChatBot.DTO.ResponseStructure;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {
   @ExceptionHandler(UserNotFoundException.class)
   public ResponseStructure handleUserNotFoundException(UserNotFoundException ex) {
       ResponseStructure response = new ResponseStructure();
       response.setStatusCode(404);
       response.setMessage(ex.getMessage());
       return response;
   }
}



