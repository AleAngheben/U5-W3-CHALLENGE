package alessandro.angheben.u5w3d5.exceptions;

import alessandro.angheben.u5w3d5.payloads.errors.ErrorDTO;
import alessandro.angheben.u5w3d5.payloads.errors.ErrorDTOwithList;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionsHandler {


    //400
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTOwithList handleBadRequest(BadRequestException ex){
        List<String> errMessage = new ArrayList<>();
        if(ex.getErrorList() != null)
            errMessage = ex.getErrorList().stream().map(err -> err.getDefaultMessage()).toList();
        return new ErrorDTOwithList(ex.getMessage(), LocalDateTime.now(), errMessage);
    }


    //401
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorDTO handleUnauthorized(UnauthorizedException e){
        return new ErrorDTO(e.getMessage(), LocalDateTime.now());
    }


    //403
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorDTO handleAccessDenied(AccessDeniedException ex) {
        return new ErrorDTO("no permiss to access, no right role", LocalDateTime.now());
    }


    //404
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO HandleNotFound(NotFoundException ex){
        return new ErrorDTO(ex.getMessage(), LocalDateTime.now());
    }


    //500 Internal
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO handleGenericError(Exception ex){
        ex.printStackTrace();
        return new ErrorDTO("Server problem , we will fix as soon as possible!", LocalDateTime.now());
    }

}
