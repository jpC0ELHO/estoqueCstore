package coelhostore.estoque.domain.repositories;

import coelhostore.estoque.domain.entities.ProdutosVenda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PvendaRepository extends JpaRepository<ProdutosVenda, UUID> {
    Optional<ProdutosVenda>findById(UUID uuid);
    Optional<ProdutosVenda>findBySku(String sku);
}
