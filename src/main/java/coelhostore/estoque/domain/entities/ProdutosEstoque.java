package coelhostore.estoque.domain.entities;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_estoque")
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProdutosEstoque extends Produtos{

    @Column(name = "sku",unique = true,nullable = false)
    private String sku;

    @Column(name = "nome_produto", nullable = false)
    private String nomeProduto;

    @Column(name = "valor_compra", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorCompra;

    @Column(name = "qtd_compra", nullable = false)
    private BigDecimal qtdCompra;

    @Column(name = "imposto_importacao", nullable = false, precision = 5, scale = 2)
    private BigDecimal impostoImportacao;

    @Column(name = "valor_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTotal;

    @Column(name = "fornecedor", nullable = false)
    private String fornecedor;

    @Column(name = "data_compra", nullable = false)
    private LocalDate dataCompra;

    @Column(name = "qtd_estoque", nullable = false)
    private Integer qtdEstoque;

    @Column(name = "produto_reservado", nullable = false)
    private Boolean produtoReservado;

    @Column(name = "qtd_reservados", nullable = false)
    private Double qtdReservados;
}
