package miniproject.carrotmarket1.service;

import miniproject.carrotmarket1.dao.MySQL.ReportDAO;
import miniproject.carrotmarket1.entity.Report;
import miniproject.carrotmarket1.entity.ReportStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {
    private final ReportDAO reportDAO;

    public ReportService(ReportDAO reportDAO) {
        this.reportDAO = reportDAO;
    }

    public List<Map<String, Object>> getAvgProcessingTimeByCategory() {
        return reportDAO.getAvgProcessingTimeByCategory();
    }

    public List<Map<String, Object>> getReportStats(String period) {
        Map<String, Object> params = new HashMap<>();
        params.put("period", period); // period 값: daily, weekly, monthly
        return reportDAO.getReportStats(params);
    }

    //모든 신고 목록 조회
    public List<Report> getAllReports() {
        return reportDAO.findAllReports();
    }
    //특정 신고 상세 조회
    public Report getReportById(Long reportId) {
        return reportDAO.findReportById(reportId);
    }
    // 신고 상태 업데이트
    @Transactional
    public void updateReportStatus(Long reportId, ReportStatus status) {
        Report report = reportDAO.findReportById(reportId); // 신고 정보 조회
        if (report != null) {
            report.setStatus(status.name()); // Enum의 name()을 사용하여 상태 저장
            if (status == ReportStatus.RESOLVED) {
                report.setResolvedAt(new java.sql.Timestamp(System.currentTimeMillis())); // 현재 시간 설정
            } else {
                report.setResolvedAt(null); // 해결 상태가 아니면 null
            }
            reportDAO.updateReport(report); // 업데이트
        } else {
            throw new IllegalArgumentException("신고 ID가 존재하지 않습니다: " + reportId);
        }
    }

}
