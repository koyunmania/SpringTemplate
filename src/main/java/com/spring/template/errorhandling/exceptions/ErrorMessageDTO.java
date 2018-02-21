package com.spring.template.errorhandling.exceptions;

public class ErrorMessageDTO {
	  private String message;
	  private MessageType type;
	  
	  public ErrorMessageDTO() {
	    super();
	  }
	  
	  public ErrorMessageDTO(MessageType type, String message) {
	    super();
	    this.message = message;
	    this.type = type;
	  }

	  public String getMessage() {
	    return message;
	  }
	  
	  public void setMessage(String message) {
	    this.message = message;
	  }
	  
	  public MessageType getType() {
	    return type;
	  }
	  
	  public void setType(MessageType type) {
	    this.type = type;
	  }
	}
