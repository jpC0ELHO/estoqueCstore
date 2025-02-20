package coelhostore.estoque.api.dtos;

import coelhostore.estoque.domain.entities.ProdutosEstoque;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonPropertyOrder({"uuid, sku, nomeProduto, valorCompra, qtdCompra, impostoImportacao, valorTotal" +
        "fornecedor, dataCompra,qtdEstoque,produtoReservado, qtdReservados, createdBy, lastModifiedBy, createdAt, updateAt"})
public record PestoqueResponse(
        UUID uuid,
        String sku,
        String nomeProduto,
        BigDecimal valorCompra,
        BigDecimal qtdCompra,
        BigDecimal impostoImportacao,
        BigDecimal valorTotal,
        String fornecedor,
        LocalDate dataCompra,
        Integer qtdEstoque,
        Boolean produtoReservado,
        Double qtdReservados,
        String createdBy,
        String lastModifiedBy,
        LocalDateTime createdAt,
        LocalDateTime updateAt
) {
    public static PestoqueResponse toResponse(ProdutosEstoque produtosEstoque){
        if (produtosEstoque==null){
            return null;
        }
        return new PestoqueResponse(
                produtosEstoque.getUuid(),
                produtosEstoque.getSku(),
                produtosEstoque.getNomeProduto(),
                produtosEstoque.getValorCompra(),
                produtosEstoque.getQtdCompra(),
                produtosEstoque.getImpostoImportacao(),
                produtosEstoque.getValorTotal(),
                produtosEstoque.getFornecedor(),
                produtosEstoque.getDataCompra(),
                produtosEstoque.getQtdEstoque(),
                produtosEstoque.getProdutoReservado(),
                produtosEstoque.getQtdReservados(),
                produtosEstoque.getCreatedBy(),
                produtosEstoque.getLastModifiedBy(),
                produtosEstoque.getCreatedAt(),
                produtosEstoque.getUpdateAt()
        );
    }
}
