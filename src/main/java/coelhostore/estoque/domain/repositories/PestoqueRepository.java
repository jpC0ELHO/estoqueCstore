package coelhostore.estoque.domain.repositories;

import coelhostore.estoque.domain.entities.ProdutosEstoque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PestoqueRepository extends JpaRepository<ProdutosEstoque, UUID> {
    Optional<ProdutosEstoque>findById(UUID uuid);
    Optional<ProdutosEstoque>findBySku(String sku);
}
