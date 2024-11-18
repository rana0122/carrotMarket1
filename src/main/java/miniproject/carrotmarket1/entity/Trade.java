package miniproject.carrotmarket1.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Trade {
    private Long id;
    private Long buyerId;
    private Long productId;
    private Timestamp tradeDate;
    private String status;

    // User 와 Product 객체 관계 매핑
    private User buyer;
    private Product product;
}
