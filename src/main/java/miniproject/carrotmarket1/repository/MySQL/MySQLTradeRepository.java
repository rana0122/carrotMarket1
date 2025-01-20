package miniproject.carrotmarket1.repository.MySQL;

import miniproject.carrotmarket1.mapper.TradeDAO;
import miniproject.carrotmarket1.dto.Product;
import miniproject.carrotmarket1.dto.Trade;
import miniproject.carrotmarket1.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MySQLTradeRepository implements TradeRepository {
    private final TradeDAO tradeDAO;

    @Autowired
    public MySQLTradeRepository(TradeDAO tradeDAO) {
        this.tradeDAO = tradeDAO;
    }

    @Override
    public List<Product> findByBuyerId(Long buyerId) {
        return tradeDAO.findByBuyerId(buyerId);
    }

    @Override
    public Trade findByProductId(Long productId) {
        return tradeDAO.findByProductId(productId);
    }

    @Override
    public void createTrade(Trade trade) {
        tradeDAO.createTrade(trade);
    }

    @Override
    public void updateTrade(Trade trade) {
        tradeDAO.updateTrade(trade);
    }

    @Override
    public void deleteTrade(Long id) {
        tradeDAO.deleteTrade(id);
    }
}
