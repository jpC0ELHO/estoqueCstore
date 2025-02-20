package coelhostore.estoque.domain.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
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

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_vendas")
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProdutosVenda extends Produtos{

    @Column(name = "sku",unique = true,nullable = false)
    private String sku;
    @Column(name = "nome_produto",nullable = false)
    private String nomeProduto;
    @Column(name = "valor_venda",nullable = false,precision = 10)
    private BigDecimal valorVenda;
    @Column(name = "imposto_entraga",nullable = false)
    private BigDecimal impostoEntrega;
    @Column(name = "valor_total",nullable = false)
    private BigDecimal valorTotal;
    @Column(name = "cliente",nullable = false)
    private String cliente;
    @Column(name = "local_entrega", nullable = false)
    private String localEntrega;
    @Column(name = "local_venda",nullable = false)
    private String localVenda;
    @Column(name = "data_venda")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private LocalDate dataVenda;
    @Column(name = "data_entrega")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private LocalDate dataEntrega;
    @Column(name = "parelado",nullable = false)
    private Boolean parcelado;
    @Column(name = "situacao",nullable = false)
    private String situacao;
    @Column(name = "parcelas",nullable = false)
    private Integer parcelas;
    @Column(name = "quantidade_vendida",nullable = false)
    private Integer qtdVendida;
}
