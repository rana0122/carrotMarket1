package miniproject.carrotmarket1.dao.MySQL;

import miniproject.carrotmarket1.entity.Report;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
    List<Report> findAllReports();

    // 특정 신고 조회
    @Select("SELECT * FROM report WHERE id = #{reportId}")
    Report findReportById(@Param("reportId") Long reportId);

    // 신고 상태 업데이트
    @Update("UPDATE report SET status = #{status}, resolved_at = #{resolvedAt} WHERE id = #{id}")
    void updateReport(Report report);
}
