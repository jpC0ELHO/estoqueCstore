package coelhostore.estoque.api.services.pEstoqueService;

import coelhostore.estoque.api.dtos.PestoqueRequest;
import coelhostore.estoque.api.dtos.PestoqueResponse;
import coelhostore.estoque.domain.entities.ProdutosEstoque;
import coelhostore.estoque.domain.entities.ProdutosVenda;
import coelhostore.estoque.domain.exceptions.PEstoqueNotFoundException;
import coelhostore.estoque.domain.repositories.PestoqueRepository;
import coelhostore.estoque.domain.repositories.PvendaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static coelhostore.estoque.api.dtos.PestoqueRequest.toEntidade;

@Service
@Log4j2
@AllArgsConstructor
public class PestoqueServiceImp implements PestoqueService {
    private final PestoqueRepository pestoqueRepository;
    private ProdutosVenda produtosVenda;
    @Override
    public List<PestoqueResponse> findStockList() {
        try {
            var findstockList=pestoqueRepository.findAll();
            if (findstockList.isEmpty()){
                log.info("List not found!");
                throw new PEstoqueNotFoundException("List not found.");
            }
            return findstockList.stream().map(PestoqueResponse::toResponse).toList();
        }catch (RuntimeException e){
            log.error("Error: {} !",e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public Optional<PestoqueResponse> findProductId(UUID uuid) {
        var findProduct=pestoqueRepository.findById(uuid);
        if (findProduct.isEmpty()){
            log.info("Id: {} not found!",uuid);
            throw new PEstoqueNotFoundException("ID not found!");
        }
        return findProduct.map(PestoqueResponse::toResponse);
    }

    @Override
    public Optional<PestoqueResponse> findProductSku(String sku) {
        var findProductBySku=pestoqueRepository.findBySku(sku);
        if (findProductBySku.isEmpty()){
            log.info("SKU:{} not found!",sku);
            throw new PEstoqueNotFoundException("SKU not found!");
        }
        return findProductBySku.map(PestoqueResponse::toResponse);
    }

    @Override
    public void creatProduct(PestoqueRequest pestoqueRequest) {
        var findProductSku=pestoqueRepository.findBySku(pestoqueRequest.sku());
        if (findProductSku.isPresent()){
            log.info("Product with sku:{} already exists!",pestoqueRequest.sku());
        }
        pestoqueRepository.save(toEntidade(pestoqueRequest));
    }

    @Override
    public void updateProduct(UUID uuid, PestoqueRequest pestoqueRequest) {
        var findProductSku=pestoqueRepository.findBySku(pestoqueRequest.sku());
        var findProductId=pestoqueRepository.findById(uuid);
        if (findProductSku.isEmpty()||findProductId.isEmpty()){
            log.info("Product not found!");
            throw new PEstoqueNotFoundException("Not found!");
        } var produtoSituacao=produtosVenda.getSituacao();
        ProdutosEstoque produtosEstoque=findProductSku.orElse(findProductId.get());
                produtosEstoque.setSku(pestoqueRequest.sku());
                produtosEstoque.setNomeProduto(pestoqueRequest.nomeProduto());
                produtosEstoque.setValorCompra(pestoqueRequest.valorCompra());
                produtosEstoque.setQtdCompra(pestoqueRequest.qtdCompra());
                produtosEstoque.setImpostoImportacao(pestoqueRequest.impostoImportacao());
                produtosEstoque.setValorTotal(produtosEstoque.getValorTotal());
                produtosEstoque.setFornecedor(pestoqueRequest.fornecedor());
                produtosEstoque.setDataCompra(pestoqueRequest.dataCompra());
                if (produtoSituacao.equalsIgnoreCase("vendido")){
                   int valorAtual=produtosEstoque.getQtdEstoque().intValue();
                   int quantidadeVendida=produtosVenda.getQtdVendida();
                   if (valorAtual>=quantidadeVendida){
                       produtosEstoque.setQtdEstoque(valorAtual-quantidadeVendida);
                   }else {
                       log.warn("Quantidade de venda excedeu o numero de produtos no estoque!");
                       throw new IllegalArgumentException("Quantidade maior que a disponivel!");
                   }
                }else {
                    produtosEstoque.setQtdEstoque(pestoqueRequest.qtdEstoque());
                }
                produtosEstoque.setQtdEstoque(pestoqueRequest.qtdEstoque());
                produtosEstoque.setProdutoReservado(pestoqueRequest.produtoReservado());
                produtosEstoque.setQtdReservados(pestoqueRequest.qtdReservados());
                pestoqueRepository.save(produtosEstoque);


    }

    @Override
    public void deletProduct(UUID uuid) {
        try {
            var findId=pestoqueRepository
                    .findById(uuid)
                    .orElseThrow(()->new PEstoqueNotFoundException("Not found!"));
            pestoqueRepository.delete(findId);
        }catch (RuntimeException e){
            log.error("Error:{} !",e.getMessage());
        }
    }
}
