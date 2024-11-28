package miniproject.carrotmarket1.service;

import miniproject.carrotmarket1.dao.MySQL.ReportDAO;
import miniproject.carrotmarket1.entity.Report;
import miniproject.carrotmarket1.entity.ReportStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        // 신고 정보 조회
        Report report = reportDAO.findReportById(reportId);

        if (report != null) {
            // 상태 업데이트 (Enum 타입 그대로 사용)
            report.setStatus(status);

            // 상태가 RESOLVED인 경우 resolvedAt에 현재 시간 설정
            if (status == ReportStatus.RESOLVED) {
                report.setResolvedAt(new java.sql.Timestamp(System.currentTimeMillis())); // 해결 시간 설정
            } else {
                report.setResolvedAt(null); // 해결되지 않은 상태로 설정
            }

            // 업데이트
            reportDAO.updateReport(report);
        } else {
            throw new IllegalArgumentException("신고 ID가 존재하지 않습니다: " + reportId);
        }
    }

    //신고 목록 조회
    public Page<Report> getFilteredReports(String startDate, String endDate, String status, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        int offset = page * size;
        List<Report> reports = reportDAO.findFilteredReportsWithPagination(startDate, endDate, status, size, offset);
        long totalElements = reportDAO.countFilteredReports(startDate, endDate, status);

        return new PageImpl<>(reports, pageable, totalElements);
    }
}
