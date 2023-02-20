package com.LTIMindtree.exceptionhandler;
import java.net.ConnectException;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class CustomizedResponseEntityExecptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(CustomCustomerdException.class)	
	public final ResponseEntity<Object> userNotFoundException(CustomCustomerdException ex, WebRequest request){
		
		//ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), HttpStatus.NOT_FOUND ,request.getDescription(false), null);
		
			ExceptionResponse exceptionResponse1 = new ExceptionResponse();
			exceptionResponse1.setTimeStamp(new Date());
			exceptionResponse1.setMessage(ex.getMessage());
			exceptionResponse1.setStatus(HttpStatus.NOT_FOUND);
			exceptionResponse1.setDetails(request.getDescription(false));
			
			
		  return new ResponseEntity<Object>(exceptionResponse1,HttpStatus.NOT_FOUND);
		
		
	}
	
	@ExceptionHandler(value = {DataBaseExpection.class})
    public ResponseEntity<Object> cannotCreateTransactionException(DataBaseExpection exception, WebRequest request) {
        
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setTimeStamp(new Date());
		exceptionResponse.setMessage(exception.getMessage());		
		exceptionResponse.setDetails(request.getDescription(false));
		exceptionResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		
		return new ResponseEntity<Object>(exceptionResponse,exceptionResponse.getStatus());
	
	}
	
	@ExceptionHandler(value = {APIRequestTimeoutException.class})
    public ResponseEntity<Object> cannotCreateTransactionException(APIRequestTimeoutException exception, WebRequest request) {
        
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setTimeStamp(new Date());
		exceptionResponse.setMessage(exception.getMessage());		
		exceptionResponse.setDetails(request.getDescription(false));
		exceptionResponse.setStatus(HttpStatus.GATEWAY_TIMEOUT);
		
		return new ResponseEntity<Object>(exceptionResponse,exceptionResponse.getStatus());
	
	}
	
}


	
	
	

