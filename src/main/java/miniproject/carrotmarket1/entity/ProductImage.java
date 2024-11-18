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
public class ProductImage {
    private Long id;
    private Long product_id;
    private String image_url;
    private Timestamp uploaded_at;

    // Product 객체 관계 매핑
    private Product product;
}
