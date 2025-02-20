package coelhostore.estoque.api.services.pVendasService;

import coelhostore.estoque.api.dtos.PvendaRequest;
import coelhostore.estoque.api.dtos.PvendaResponse;
import coelhostore.estoque.domain.exceptions.PVendaNotFoundExcpeiton;
import coelhostore.estoque.domain.repositories.PvendaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static coelhostore.estoque.api.dtos.PvendaRequest.toEntidade;

@Log4j2
@Service
@AllArgsConstructor
public class PvendasServiceImpl implements PvendasService{
    private final PvendaRepository pvendaRepository;
    @Override
    public List<PvendaResponse> findSaleList() {
        try {
            var findSalesList=pvendaRepository.findAll();
            if (findSalesList.isEmpty()){
                throw new PVendaNotFoundExcpeiton("Sale not found!");
            }
            return findSalesList.stream().map(PvendaResponse::toResponse).toList();
        }catch (RuntimeException e){
            log.error("Error: ",e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public Optional<PvendaResponse> findSaleById(UUID uuid) {
        var findSaleid=pvendaRepository.findById(uuid);
        if (findSaleid.isEmpty()){
            log.info("Sale not found! ID: {} ",uuid);
            throw new PVendaNotFoundExcpeiton("Sale not found!");
        }
        return findSaleid.map(PvendaResponse::toResponse);
    }

    @Override
    public Optional<PvendaResponse> findSaleBySku(String sku) {
        var findSaleSku=pvendaRepository.findBySku(sku);
        if (findSaleSku.isEmpty()){
            log.info("Sku not found!");
            throw new PVendaNotFoundExcpeiton("Sku not found!");
        }
        return findSaleSku.map(PvendaResponse::toResponse);
    }

    @Override
    public void createSale(PvendaRequest pvendaRequest) {
    var findSale=pvendaRepository.findBySku(pvendaRequest.sku());
    if (findSale.isPresent()){
        log.info("SKU: {} ,already exists!",pvendaRequest.sku());
        throw new RuntimeException("Error");
    }
    pvendaRepository.save(toEntidade(pvendaRequest));
    }

    @Override
    public void updateSale(UUID uuid, PvendaRequest pvendaRequest) {
    var findSaleId=pvendaRepository.findById(uuid);
    if (findSaleId.isEmpty()){
        log.info("ID:{} not found!",uuid);
        throw new PVendaNotFoundExcpeiton("Sale not found!");
    }
    findSaleId.map(produtosVenda -> {
        produtosVenda.setSku(pvendaRequest.sku());
        produtosVenda.setNomeProduto(pvendaRequest.nomeProduto());
        produtosVenda.setValorVenda(pvendaRequest.valorVenda());
        produtosVenda.setImpostoEntrega(pvendaRequest.impostoEntrega());
        produtosVenda.setValorTotal(pvendaRequest.valorTotal());
        produtosVenda.setCliente(pvendaRequest.cliente());
        produtosVenda.setLocalEntrega(pvendaRequest.localEntrega());
        produtosVenda.setLocalVenda(pvendaRequest.localVenda());
        produtosVenda.setDataVenda(pvendaRequest.dataVenda());
        produtosVenda.setDataEntrega(pvendaRequest.dataEntrega());
        produtosVenda.setParcelado(pvendaRequest.parcelado());
        produtosVenda.setSituacao(pvendaRequest.situacao());
        produtosVenda.setParcelas(pvendaRequest.parcelas());
        return pvendaRepository.save(produtosVenda);
    });
    }

    @Override
    public void deleteSale(UUID uuid) {
        try {
            var findSlaeId=pvendaRepository
                    .findById(uuid)
                    .orElseThrow(()-> new PVendaNotFoundExcpeiton("Id not found!"));
        }catch (RuntimeException e){
            log.info("Error: {} .",e.getMessage());
        }
    }
}
