package miniproject.carrotmarket1.controller;

import miniproject.carrotmarket1.entity.Report;
import miniproject.carrotmarket1.entity.ReportStatus;
import miniproject.carrotmarket1.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {

        this.reportService = reportService;
    }

//    @GetMapping("/reports")
//    public String getReportList(Model model) {
//        List<Report> reports = reportService.getAllReports();
//        model.addAttribute("reports", reports);
//        return "reports/report-list";
//    }

    // 신고 목록 조회
    @GetMapping("/reports")
    public String getReportList(@RequestParam(required = false) String startDate,
                                @RequestParam(required = false) String endDate,
                                @RequestParam(required = false) String status,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                Model model) {
        Page<Report> reportPage = reportService.getFilteredReports(startDate, endDate, status, page, size);
        model.addAttribute("reports", reportPage.getContent()); // 현재 페이지의 내용만 추가
        model.addAttribute("page", reportPage); // 페이지 정보를 추가
        model.addAttribute("statusList", ReportStatus.values()); // Enum 값 전달

        // Null 값이 포함될 수 있으므로 필터를 조건부로 추가
        Map<String, String> currentFilters = new HashMap<>();
        if (startDate != null) currentFilters.put("startDate", startDate);
        if (endDate != null) currentFilters.put("endDate", endDate);
        if (status != null) currentFilters.put("status", status);
        model.addAttribute("currentFilters", currentFilters);

        return "reports/report-list";
    }



    @GetMapping("/reports/{id}")
    public String getReportDetails(@PathVariable Long id, Model model) {
        Report report = reportService.getReportById(id);
        model.addAttribute("report", report);
        model.addAttribute("statusList", ReportStatus.values()); // Enum 값 전달
        return "reports/report-edit";
    }

    @PostMapping("/reports/update/{id}")
    public String updateReportStatus(@PathVariable Long id, @RequestParam ReportStatus status) {
        reportService.updateReportStatus(id, status); // Enum 타입으로 전달
        return "redirect:/admin/reports"; // 수정 후 목록 페이지로 리다이렉트
    }

    @GetMapping("/processing-time")
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
        System.out.println(stats); // 디버깅 출력
        model.addAttribute("chartData", stats);
        System.out.println(stats); // 템플릿으로 전달된 데이터 확인
        model.addAttribute("period", period);
        return "reports/stats";
    }
}
