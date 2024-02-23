package alessandro.angheben.u5w3d5.exceptions;

public class NotFoundException extends RuntimeException{

    public NotFoundExceptions(long id){super("Element with id " + id + " not found");}
    public NotFoundExceptions(String message){super(message);}

}
