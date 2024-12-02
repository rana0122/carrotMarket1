package miniproject.carrotmarket1.repository;

import miniproject.carrotmarket1.entity.Product;
import miniproject.carrotmarket1.entity.Trade;

import java.util.List;

public interface TradeRepository {
    List<Product> findByBuyerId(Long buyerId);
    Trade findByProductId(Long productId);
    void createTrade(Trade trade);
    void updateTrade(Trade trade);
    void deleteTrade(Long id);
}


