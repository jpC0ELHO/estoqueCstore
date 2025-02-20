package coelhostore.estoque.domain.exceptions;

import lombok.Getter;

@Getter
public class PEstoqueNotFoundException extends ModelNotFoundException{

    public PEstoqueNotFoundException(String  message){
        super(message);
    }
    public PEstoqueNotFoundException(){
        super("Product not found!");
    }
}
