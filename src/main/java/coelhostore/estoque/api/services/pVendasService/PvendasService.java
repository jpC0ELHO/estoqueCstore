package coelhostore.estoque.api.services.pVendasService;

import coelhostore.estoque.api.dtos.PvendaRequest;
import coelhostore.estoque.api.dtos.PvendaResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PvendasService {
    List<PvendaResponse>findSaleList();
    Optional<PvendaResponse>findSaleById(UUID uuid);
    Optional<PvendaResponse>findSaleBySku(String sku);
    void createSale(PvendaRequest pvendaRequest);
    void updateSale(UUID uuid,PvendaRequest pvendaRequest);
    void deleteSale(UUID uuid);
}
