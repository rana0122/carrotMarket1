package miniproject.carrotmarket1.controller;

import lombok.RequiredArgsConstructor;
import miniproject.carrotmarket1.entity.Report;
import miniproject.carrotmarket1.entity.ReportStatus;
import miniproject.carrotmarket1.service.ReportService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    //신고 목록 조회 (필터 기능, 페이징 기능)
    @GetMapping("/reports")
    public String getReportListPagination(Model model,
                                          @RequestParam(required = false) String startDate,
                                          @RequestParam(required = false) String endDate,
                                          @RequestParam(required = false) String status,
                                          @RequestParam(required = false) String tag,
                                          @RequestParam(required = false) String search,
                                          @RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size
                                          ) {
        Page<Report> reports = reportService.getReportListPagination(startDate, endDate, status, page, size,tag,search);
        model.addAttribute("reports", reports); // 보고서 데이터
        model.addAttribute("page", reports); // 페이지 정보
        //<select>에 매핑할 ReportStatus(Enum) 상수 값 전달
        model.addAttribute("statusList", ReportStatus.values());

        //NULL값이 포함될 수 있으므로 필터를 조건부로 추가
        Map<String, String> currentFilters = new HashMap<>();
        currentFilters.put("startDate", startDate != null ? startDate : "");
        currentFilters.put("endDate", endDate != null ? endDate : "");
        currentFilters.put("status", status != null ? status : "");
        currentFilters.put("tag", tag != null ? tag : "");
        currentFilters.put("search", search != null ? search : "");
        model.addAttribute("currentFilters", currentFilters);

        //검색 기능

        return "reports/report-list";
    }

    //신고 상세 조회
    @GetMapping("/reports/{id}")
    public String getReportDetails(@PathVariable Long id, Model model) {
        Report report = reportService.getReportById(id);
        model.addAttribute("report", report);
        //<select>에 매핑할 ReportStatus(Enum) 상수 값 전달
        model.addAttribute("statusList", ReportStatus.values());
        return "/reports/report-edit";
    }

    //신고 처리 상태 변경
    @PostMapping("/reports/update/{id}")
    public String updateReportStatus(@PathVariable Long id, @RequestParam ReportStatus status) {
        reportService.updateReportStatus(id, status);
        return "redirect:/admin/reports";
    }


    //통계 페이지
    @GetMapping("/chart")
    public String chart(Model model) {
        //월별 총합
        //주간 총합
        //일간 총합
        //뽑는 걸로

        // X축 레이블 (월)
        List<String> labels = Arrays.asList("1월", "2월", "3월", "4월");

        // Y축 데이터 (매출)
        List<Integer> values = Arrays.asList(100, 200, 300, 400);

        model.addAttribute("labels", labels);
        model.addAttribute("values", values);
        return "/reports/report-chart";
    }


}
