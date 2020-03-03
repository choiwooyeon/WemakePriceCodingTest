package com.wemakeprice.exception;

import java.io.IOException;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.wemakeprice.model.CharacterSortResponseDTO;

/**
 * 예외처리 핸들러
 * 
 * @author wooyeon.choi
 * @since 2020.03.03
 *
 */
@ControllerAdvice  
@RestController
public class GlobalExceptionHandler {
	static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	 
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<CharacterSortResponseDTO> handleConstraintViolationException(ConstraintViolationException e) {
    	Set<ConstraintViolation<?>> resultList = e.getConstraintViolations();
    	CharacterSortResponseDTO response = null;
        for (ConstraintViolation<?> fieldError : resultList) {
        	response = new CharacterSortResponseDTO(getReponseMessage(fieldError.getPropertyPath().toString()),
        			"","");
        }
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    
    
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<CharacterSortResponseDTO> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
		CharacterSortResponseDTO response = new CharacterSortResponseDTO(getReponseMessage(e.getName()), "","");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    
    
    @ExceptionHandler(IOException.class)
    protected ResponseEntity<CharacterSortResponseDTO> handleIOException(IOException e) {
		CharacterSortResponseDTO response = new CharacterSortResponseDTO("사이트 연결이 불안정합니다. 다시 시도 하세요.", "", "");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    
    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<CharacterSortResponseDTO> handleIllegalArgumentException(IllegalArgumentException e) {
		CharacterSortResponseDTO response = new CharacterSortResponseDTO(e.getMessage(),"","");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    private String getReponseMessage(String exceptionFieldName) {
    	String msg = "";
        if(exceptionFieldName.indexOf("url") >=0) {
        	msg = "url은 http,https형식 이여야합니다.";
        }
        else if(exceptionFieldName.indexOf("groupCount") >=0) {
        	msg ="출력묶음 단위는 1보다 크고 "+Long.MAX_VALUE+"보다 작은 자연수이여야 합니다.";
        }
        return msg;
    }
}

