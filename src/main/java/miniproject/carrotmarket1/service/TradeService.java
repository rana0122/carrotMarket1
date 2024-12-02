package miniproject.carrotmarket1.service;

import miniproject.carrotmarket1.entity.Product;
import miniproject.carrotmarket1.repository.ProductRepository;
import miniproject.carrotmarket1.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeService {
    private final TradeRepository tradeRepository;
    private final ProductRepository productRepository;

    @Autowired
    public TradeService(TradeRepository tradeRepository, ProductRepository productRepository) {
        this.tradeRepository = tradeRepository;
        this.productRepository = productRepository;
    }

    public List<Product> findByBuyerId(Long buyerId) {
        return tradeRepository.findByBuyerId(buyerId);
    }
}
