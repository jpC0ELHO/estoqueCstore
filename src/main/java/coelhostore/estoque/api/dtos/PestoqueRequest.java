package coelhostore.estoque.api.dtos;

import coelhostore.estoque.domain.entities.ProdutosEstoque;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record PestoqueRequest(
        @NotBlank
        String sku,
        @NotBlank
        String nomeProduto,
        @NotNull
        BigDecimal valorCompra,
        @NotNull
        BigDecimal qtdCompra,
        @NotNull
        BigDecimal impostoImportacao,
        @NotNull
        BigDecimal valorTotal,
        @NotBlank
        String fornecedor,
        @NotNull
        @NotBlank
        LocalDate dataCompra,
        @NotNull
        Integer qtdEstoque,
        @NotNull
        Boolean produtoReservado,
        @NotNull
        Double qtdReservados
) {
    public static ProdutosEstoque toEntidade(PestoqueRequest pestoqueRequest){
        if (pestoqueRequest==null){
            return null;
        }
        return new ProdutosEstoque(
               pestoqueRequest.sku,
                pestoqueRequest.nomeProduto,
                pestoqueRequest.valorCompra,
                pestoqueRequest.qtdCompra,
                pestoqueRequest.impostoImportacao,
                pestoqueRequest.valorTotal,
                pestoqueRequest.fornecedor,
                pestoqueRequest.dataCompra,
                pestoqueRequest.qtdEstoque,
                pestoqueRequest.produtoReservado,
                pestoqueRequest.qtdReservados
        );
    }
}
