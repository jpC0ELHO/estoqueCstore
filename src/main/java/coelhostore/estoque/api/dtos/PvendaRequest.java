package coelhostore.estoque.api.dtos;

import coelhostore.estoque.domain.entities.ProdutosVenda;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record PvendaRequest(
        @NotBlank
        String sku,
        @NotBlank
        String nomeProduto,
        @NotNull
        BigDecimal valorVenda,
        @NotNull
        BigDecimal impostoEntrega,
        @NotNull
        BigDecimal valorTotal,
        @NotBlank
        String cliente,
        @NotBlank
        String localEntrega,
        @NotBlank
        String localVenda,
        @NotNull
        LocalDate dataVenda,
        @NotNull
        LocalDate dataEntrega,
        @NotNull
        Boolean parcelado,
        @NotBlank
        String situacao,
        @NotNull
        Integer parcelas
) {
        public static ProdutosVenda toEntidade(PvendaRequest pVendaRequest){
                if (pVendaRequest==null){
                        return  null;
                }
                return new ProdutosVenda(
                        pVendaRequest.sku(),
                        pVendaRequest.nomeProduto(),
                        pVendaRequest.valorVenda(),
                        pVendaRequest.impostoEntrega(),
                        pVendaRequest.valorTotal(),
                        pVendaRequest.cliente(),
                        pVendaRequest.localEntrega(),
                        pVendaRequest.localVenda(),
                        pVendaRequest.dataVenda(),
                        pVendaRequest.dataEntrega(),
                        pVendaRequest.parcelado(),
                        pVendaRequest.situacao(),
                        pVendaRequest.parcelas()
                );
        }
}
