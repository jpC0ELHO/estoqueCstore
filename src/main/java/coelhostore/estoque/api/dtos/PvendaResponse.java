package coelhostore.estoque.api.dtos;

import coelhostore.estoque.domain.entities.ProdutosVenda;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonPropertyOrder({"uuid","sku","nomePorduto","valorVenda"
        ,"impostoEntrega","valorTotal","cliente","localEntrega","localVenda"
        ,"dataVenda","dataEntrega","parcelado","situacao","parcelas"
        ,"createdBy","lastModifiedBy","createdAt","updateAt"})
public record PvendaResponse(
        UUID uuid,
        String sku,
        String nomeProduto,
        BigDecimal valorVenda,
        BigDecimal impostoEntrega,
        BigDecimal valorTotal,
        String cliente,
        String localEntrega,
        String localVenda,
        LocalDate dataVenda,
        LocalDate dataEntrega,
        Boolean parcelado,
        String situacao,
        Integer parcelas,
        String createdBy,
        String lastModifiedBy,
        LocalDateTime createdAt,
        LocalDateTime updateAt
){
    public static PvendaResponse toResponse(ProdutosVenda produtosVenda){
    if (produtosVenda==null){
        return null;
    }
    return new PvendaResponse(
            produtosVenda.getUuid(),
            produtosVenda.getSku(),
            produtosVenda.getNomeProduto(),
            produtosVenda.getValorVenda(),
            produtosVenda.getImpostoEntrega(),
            produtosVenda.getValorTotal(),
            produtosVenda.getLocalVenda(),
            produtosVenda.getLocalEntrega(),
            produtosVenda.getLocalVenda(),
            produtosVenda.getDataVenda(),
            produtosVenda.getDataEntrega(),
            produtosVenda.getParcelado(),
            produtosVenda.getSituacao(),
            produtosVenda.getParcelas(),
            produtosVenda.getCreatedBy(),
            produtosVenda.getLastModifiedBy(),
            produtosVenda.getCreatedAt(),
            produtosVenda.getUpdateAt()
    );
    }
}
