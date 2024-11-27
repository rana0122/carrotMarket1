package miniproject.carrotmarket1.controller;

import lombok.RequiredArgsConstructor;
import miniproject.carrotmarket1.entity.Report;
import miniproject.carrotmarket1.entity.ReportStatus;
import miniproject.carrotmarket1.service.ReportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

//./gradlew clean build -> 원래 있던 내용 청소하고 다시 빌드하는 거
//                          (파일을 아예 통으로 다시 끼웟을 때 보통사용)

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;
    //신고 목록 조회 (필터 기능)
    @GetMapping("/reports")
    public String getReportList(Model model,
                                @RequestParam(required = false) String startDate,
                                @RequestParam(required = false) String endDate,
                                @RequestParam(required = false) String status)
    {
        List<Report> reports = reportService.getReportList(startDate, endDate, status);
        model.addAttribute("reports", reports);
        //<select>에 매핑할 ReportStatus(Enum) 상수 값 전달
        model.addAttribute("statusList", ReportStatus.values());
        return "reports/report-list";
    }

    //신고 상세 조회
    @GetMapping("/reports/{id}")
    public String getReportDetails(@PathVariable Long id, Model model) {
        Report report = reportService.getReportById(id);
        model.addAttribute("report", report);
        //<select>에 매핑할 ReportStatus(Enum) 상수 값 전달
        model.addAttribute("statusList",ReportStatus.values());
        return "/reports/report-edit";
    }

    //신고 처리 상태 변경
    @PostMapping("/reports/update/{id}")
    public String updateReportStatus(@PathVariable Long id, @RequestParam ReportStatus status) {
        reportService.updateReportStatus(id, status);
        return "redirect:/admin/reports";
    }



}
