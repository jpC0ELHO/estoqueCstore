package coelhostore.estoque.domain.exceptions;

import lombok.Getter;

@Getter
public class ModelNotFoundException extends RuntimeException{
    public ModelNotFoundException(String message){
        super(message);
    }
}
