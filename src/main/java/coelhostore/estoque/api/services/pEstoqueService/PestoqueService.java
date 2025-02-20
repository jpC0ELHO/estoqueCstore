package coelhostore.estoque.api.services.pEstoqueService;

import coelhostore.estoque.api.dtos.PestoqueRequest;
import coelhostore.estoque.api.dtos.PestoqueResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PestoqueService {
    List<PestoqueResponse> findStockList();
    Optional<PestoqueResponse>findProductId(UUID uuid);
    Optional<PestoqueResponse>findProductSku(String sku);
    void creatProduct(PestoqueRequest pestoqueRequest);
    void updateProduct(UUID uuid,PestoqueRequest pestoqueRequest);
    void deletProduct(UUID uuid);
}
