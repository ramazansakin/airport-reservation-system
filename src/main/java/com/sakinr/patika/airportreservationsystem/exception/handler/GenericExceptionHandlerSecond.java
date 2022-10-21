package com.sakinr.patika.airportreservationsystem.exception.handler;

import com.sakinr.patika.airportreservationsystem.exception.QuotaIsFullException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

// can be used @Order( value = Ordered.HIGHEST_PRECEDENCE )
// But for more than 2 controller advices, you can give priorities with 1,2,3,4,5,...
// the more lower, the more priority
@Order(value = 1)
@ControllerAdvice
public class GenericExceptionHandlerSecond {

    @ExceptionHandler(QuotaIsFullException.class)
    public ResponseEntity<Map> handleNotfoundException(QuotaIsFullException exception) {
        Map<String, String> response = new HashMap<>();
        response.put("message", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}
