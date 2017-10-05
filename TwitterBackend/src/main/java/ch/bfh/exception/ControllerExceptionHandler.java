package ch.bfh.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.logging.Level;
import java.util.logging.Logger;

@ControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger LOG = Logger.getLogger(ControllerExceptionHandler.class.getName());

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {EntityNotFoundException.class})
    public  void entityNotFoundException(Exception e){
        LOG.log(Level.SEVERE, e.getMessage(), e);
    }
}
