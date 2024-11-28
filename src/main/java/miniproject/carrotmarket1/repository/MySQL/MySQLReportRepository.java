package miniproject.carrotmarket1.repository.MySQL;

import lombok.RequiredArgsConstructor;
import miniproject.carrotmarket1.dao.MySQL.ReportDAO;
import miniproject.carrotmarket1.entity.Report;
import miniproject.carrotmarket1.entity.ReportStatus;
import miniproject.carrotmarket1.repository.ReportRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MySQLReportRepository implements ReportRepository {
    private final ReportDAO reportDAO;

    //신고 목록 조회(필터 기능)
    @Override
    public List<Report> getReportList(String startDate, String endDate, String status) {
        return reportDAO.getReportList(startDate,endDate,status);
    }

    //신고 상세 조회
    @Override
    public Report getReportById(Long id) {
        return reportDAO.getReportById(id);
    }

    //신고 처리 상태 변경
    @Override
    public void updateReportStatus(Report report) {
        reportDAO.updateReportStatus(report);
    }

}
