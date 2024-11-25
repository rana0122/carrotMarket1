package miniproject.carrotmarket1.controller;

import miniproject.carrotmarket1.service.ReportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/reports/processing-time")
    public String showProcessingTimeChart(Model model) {
        List<Map<String, Object>> data = reportService.getAvgProcessingTimeByCategory();
        model.addAttribute("chartData", data);
        return "reports/processing-time";
    }

    @GetMapping("/reports/stats")
    public String showReportStats(
            @RequestParam(defaultValue = "daily") String period,
            Model model) {
        List<Map<String, Object>> stats = reportService.getReportStats(period);
        model.addAttribute("chartData", stats);
        model.addAttribute("period", period);
        return "reports/stats";
    }
}
