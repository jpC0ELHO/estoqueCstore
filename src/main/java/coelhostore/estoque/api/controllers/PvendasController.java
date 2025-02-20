package coelhostore.estoque.api.controllers;

import coelhostore.estoque.api.dtos.PvendaRequest;
import coelhostore.estoque.api.dtos.PvendaResponse;
import coelhostore.estoque.api.services.pVendasService.PvendasService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/vendas/v1")
@AllArgsConstructor
public class PvendasController {
    private final PvendasService pvendasService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PvendaResponse>>findSalesList(){
        return ResponseEntity.status(HttpStatus.FOUND).body(pvendasService.findSaleList());
    }
    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<PvendaResponse>>findSaleId(@PathVariable UUID uuid){
        return ResponseEntity.status(HttpStatus.FOUND).body(pvendasService.findSaleById(uuid));
    }
    @GetMapping(value = "/{sku}",
            produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<PvendaResponse>>findSaleSku(@PathVariable String sku){
        return ResponseEntity.status(HttpStatus.FOUND).body(pvendasService.findSaleBySku(sku));
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createSale(@RequestBody PvendaRequest pvendaRequest){
        pvendasService.createSale(pvendaRequest);
    }

    @PutMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateSale(@PathVariable UUID uuid,@RequestBody PvendaRequest pvendaRequest){
        pvendasService.updateSale(uuid,pvendaRequest);
    }
    @DeleteMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSale(@PathVariable UUID uuid){
        pvendasService.deleteSale(uuid);
    }

}
