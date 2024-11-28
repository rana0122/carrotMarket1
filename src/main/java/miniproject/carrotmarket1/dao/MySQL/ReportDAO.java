package miniproject.carrotmarket1.dao.MySQL;

import miniproject.carrotmarket1.entity.Category;
import miniproject.carrotmarket1.entity.Report;
import miniproject.carrotmarket1.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReportDAO {

    @Select("""
        SELECT 
            c.name AS categoryName,
            AVG(TIMESTAMPDIFF(HOUR, r.created_at, r.resolved_at)) AS avgProcessingTime
        FROM report r
        JOIN category c ON r.category_id = c.id
        WHERE r.status = 'RESOLVED'
        GROUP BY c.id
        ORDER BY c.name
    """)
    List<Map<String, Object>> getAvgProcessingTimeByCategory();

    List<Map<String, Object>> getReportStats(Map<String, Object> params);

    // 모든 신고 조회
    @Select("SELECT * FROM report ORDER BY created_at DESC")
    @Results({
            @Result(property = "reporter", column = "reporter_id", javaType = User.class,
                    one = @One(select = "miniproject.carrotmarket1.dao.MySQL.UserDAO.selectById")),
            @Result(property = "category", column = "category_id", javaType = Category.class,
                    one = @One(select = "miniproject.carrotmarket1.dao.MySQL.CategoryDAO.selectById")),
            @Result(property = "product", column = "product_id", javaType = Category.class,
                    one = @One(select = "miniproject.carrotmarket1.dao.MySQL.ProductDAO.findById"))
    })
    List<Report> findAllReports();

    // 특정 신고 조회
    @Select("SELECT * FROM report WHERE id = #{reportId}")
    @Results({
            @Result(property = "reporter", column = "reporter_id", javaType = User.class,
                    one = @One(select = "miniproject.carrotmarket1.dao.MySQL.UserDAO.selectById")),
            @Result(property = "category", column = "category_id", javaType = Category.class,
                    one = @One(select = "miniproject.carrotmarket1.dao.MySQL.CategoryDAO.selectById")),
            @Result(property = "product", column = "product_id", javaType = Category.class,
                    one = @One(select = "miniproject.carrotmarket1.dao.MySQL.ProductDAO.findById"))
    })
    Report findReportById(@Param("reportId") Long reportId);

    // 신고 상태 업데이트
    @Update("UPDATE report SET status = #{status}, resolved_at = #{resolvedAt} WHERE id = #{id}")
    void updateReport(Report report);


    List<Report> findFilteredReportsWithPagination(@Param("startDate") String startDate,
                                                   @Param("endDate") String endDate,
                                                   @Param("status") String status,
                                                   @Param("size") int size,
                                                   @Param("offset") int offset);

    long countFilteredReports(@Param("startDate") String startDate,
                              @Param("endDate") String endDate,
                              @Param("status") String status);
}
