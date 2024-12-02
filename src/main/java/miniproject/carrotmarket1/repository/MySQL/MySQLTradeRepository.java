package miniproject.carrotmarket1.repository.MySQL;

import miniproject.carrotmarket1.dao.MySQL.TradeDAO;
import miniproject.carrotmarket1.entity.Product;
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
}
