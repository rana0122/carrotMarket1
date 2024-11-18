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
public class ChatRoom {
    private Long id;
    private Long productId;
    private Long buyerId;
    private Long sellerId;
    private Timestamp createdAt;

    // Product 와 User 객체 관계 매핑
    private Product product;
    private User buyer;
    private User seller;
}
