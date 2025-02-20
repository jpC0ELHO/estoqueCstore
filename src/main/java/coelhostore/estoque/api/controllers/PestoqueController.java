package coelhostore.estoque.api.controllers;

import coelhostore.estoque.api.dtos.PestoqueRequest;
import coelhostore.estoque.api.dtos.PestoqueResponse;
import coelhostore.estoque.api.services.pEstoqueService.PestoqueService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/estoque/v1")
@AllArgsConstructor
public class PestoqueController {
    private final PestoqueService pestoqueService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PestoqueResponse>>findStockList(){
        return ResponseEntity.status(HttpStatus.FOUND).body(pestoqueService.findStockList());
    }
    @GetMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<PestoqueResponse>>findProductId(@PathVariable UUID uuid){
        return ResponseEntity.status(HttpStatus.FOUND).body(pestoqueService.findProductId(uuid));
    }
    @GetMapping(value = "/{sku}",consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<PestoqueResponse>>findProductSku(@PathVariable String sku){
       return ResponseEntity.status(HttpStatus.FOUND).body(pestoqueService.findProductSku(sku));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody PestoqueRequest pestoqueRequest){
        pestoqueService.creatProduct(pestoqueRequest);
    }
    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void updateProduct(@PathVariable UUID uuid,@RequestBody PestoqueRequest pestoqueRequest){
        pestoqueService.updateProduct(uuid,pestoqueRequest);
    }

    @DeleteMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public  void deletProduct(@PathVariable UUID uuid){
        pestoqueService.deletProduct(uuid);
    }
}
