package com.sanketh.AIChatBot.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseStructure {
   private String message;
   private int statusCode;
   private LocalDateTime timestamp;
public ResponseStructure(String message,int statusCode){
    super();
    this.message=message;
    this.statusCode=statusCode;
    this.timestamp=LocalDateTime.now();
}
public ResponseStructure(){
    super();
}
}
