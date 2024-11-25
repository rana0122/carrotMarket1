package miniproject.carrotmarket1.service;

import miniproject.carrotmarket1.dao.MySQL.ReportDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
