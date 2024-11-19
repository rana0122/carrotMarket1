package miniproject.carrotmarket1.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private String location;
    private Double latitude;
    private Double longitude;
    private Long categoryId;
    private Timestamp createdAt;
    private Long userId;
    private String status;

    // 관계 매핑
    private User user;
    private Category category;
    private List<ProductImage> images;
}
