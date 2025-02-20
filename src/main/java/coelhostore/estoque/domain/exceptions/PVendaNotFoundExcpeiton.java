package coelhostore.estoque.domain.exceptions;

import lombok.Getter;

@Getter
public class PVendaNotFoundExcpeiton extends ModelNotFoundException{
    public PVendaNotFoundExcpeiton(String message){
        super(message);
    }
    public PVendaNotFoundExcpeiton(){
        super("Sale no found!");
    }
}
