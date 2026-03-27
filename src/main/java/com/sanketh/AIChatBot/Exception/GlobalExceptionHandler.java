package com.sanketh.AIChatBot.Exception;

import com.sanketh.AIChatBot.DTO.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {
   @ExceptionHandler(UserNotFoundException.class)
   public ResponseStructure handleUserNotFoundException(UserNotFoundException ex) {
       return new ResponseStructure(ex.getMessage(), HttpStatus.NOT_FOUND.value());
   }
   @ExceptionHandler(RegistrationFailedException.class)
   public ResponseStructure handleRegistrationFailedException(RegistrationFailedException ex) {
       return new ResponseStructure(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
   }
   @ExceptionHandler(NothingToDeleteException.class)
   public ResponseStructure handleNothingToDeleteException(Exception ex) {
       return new ResponseStructure(ex.getMessage(), HttpStatus.NO_CONTENT.value());
   }
   @ExceptionHandler(ChatResponseGenerationException.class)
   public ResponseStructure handleChatResponseGenerationException(Exception ex) {
       return new ResponseStructure(ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE.value());
   }
   @ExceptionHandler(BadCredentialException.class)
   public ResponseStructure handleBadCredentialException(Exception ex) {
       return new ResponseStructure(ex.getMessage(), HttpStatus.UNAUTHORIZED.value());
   }
}



