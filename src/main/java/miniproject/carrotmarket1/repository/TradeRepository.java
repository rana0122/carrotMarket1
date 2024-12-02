package miniproject.carrotmarket1.repository;

import miniproject.carrotmarket1.entity.Product;

import java.util.List;

public interface TradeRepository {
    List<Product> findByBuyerId(Long buyerId);
}
