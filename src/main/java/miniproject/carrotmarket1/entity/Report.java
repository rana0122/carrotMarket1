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
public class Report {
    private Long id;
    private Long productId;
    private Long reporterId;
    private Long categoryId;
    private Long adminId;
    private String details;
    private ReportStatus  status;
    private Timestamp createdAt;
    private Timestamp resolvedAt;

    // Product, User, Category 객체 관계 매핑
    private Product product;
    private User reporter;
    private User admin;
    private Category category;
}
