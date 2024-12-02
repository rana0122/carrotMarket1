package miniproject.carrotmarket1.service;

import miniproject.carrotmarket1.entity.Product;
import miniproject.carrotmarket1.entity.Trade;
import miniproject.carrotmarket1.repository.ProductRepository;
import miniproject.carrotmarket1.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TradeService {
    private final TradeRepository tradeRepository;
    private final ProductRepository productRepository;

    @Autowired
    public TradeService(TradeRepository tradeRepository, ProductRepository productRepository) {
        this.tradeRepository = tradeRepository;
        this.productRepository = productRepository;
    }

    // 기존 메서드 유지
    public List<Product> findByBuyerId(Long buyerId) {
        return tradeRepository.findByBuyerId(buyerId);
    }

    // 새로운 메서드 추가
    public Optional<Trade> findByProductId(Long productId) {
        Trade trade = tradeRepository.findByProductId(productId);
        return Optional.ofNullable(trade);
    }

    public void createTrade(Trade trade) {
        tradeRepository.createTrade(trade);
    }

    public void updateTrade(Trade trade) {
        tradeRepository.updateTrade(trade);
    }

    public void deleteTrade(Long id) {
        tradeRepository.deleteTrade(id);
    }
}