package miniproject.carrotmarket1.dao.MySQL;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
}
