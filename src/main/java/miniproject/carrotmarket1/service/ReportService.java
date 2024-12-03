package miniproject.carrotmarket1.service;

import lombok.RequiredArgsConstructor;
import miniproject.carrotmarket1.entity.Category;
import miniproject.carrotmarket1.entity.Product;
import miniproject.carrotmarket1.entity.Report;
import miniproject.carrotmarket1.entity.ReportStatus;
import miniproject.carrotmarket1.repository.MySQL.MySQLProductRepository;
import miniproject.carrotmarket1.repository.ReportRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;
    private final MySQLProductRepository mySQLProductRepository;

    //신고 목록 조회(필터 기능, 페이징 기능)
    public Page<Report> getReportListPagination(String startDate,
                                                String endDate,
                                                String status,
                                                int page,
                                                int size,
                                                String tag,
                                                String search) {
        //페이지 번호(page)와 페이지 크기(size) 설정
        Pageable pageable = PageRequest.of(page, size);
        //데이터의 시작 위치를 지정
        int offset = page * size;
        List<Report> report = reportRepository.getReportListPagination(startDate,endDate,status,size,offset,tag,search);
        long total = reportRepository.countFilterReports(startDate,endDate,status);

        // new PageImpl<>(...)의 매개변수 순서는 중요
        // 순서 : 리스트 데이터, 페이지 정보, 전체 데이터 수
        return new PageImpl<>(report, pageable,total);
    }

    //신고 상세 조회
    public Report getReportById(Long id) {
        return reportRepository.getReportById(id);
    }

    //신고 처리 상태 변경
    public void updateReportStatus(Long id, ReportStatus status) {
        //id로 기존 report 조회
        Report report = reportRepository.getReportById(id);
        if(report != null){
            report.setStatus(status); //선택한 status 값으로 상태 변경 (pending -> resolved)
            if(ReportStatus.PENDING == status){
                report.setResolvedAt(null);
            }
            else{ // 처리 완료로 변경시 resolved_at에 시간 추가
                report.setResolvedAt(new Timestamp(System.currentTimeMillis()));
            }
            reportRepository.updateReportStatus(report); //DB적용
        }
        else{
            throw new IllegalArgumentException("신고 ID가 존재하지 않습니다: " + id);
        }
    }
    //신고글 숨김 처리 기능
    public void updateProductLock(Long productId, String lockYn) {
        reportRepository.updateProductLock(productId,lockYn);
    }

    //계정잠금 기능
    public void updateUserLock(Long userId, String lockYn) {
        reportRepository.updateUserLock(userId,lockYn);
    }

    //신고 페이지 카테고리
    public List<Category> getCategoriesByRange() {
        return reportRepository.getCategoriesByRange();
    }

    //신고 내용 insert 기능
    public void insertReport(Report report) {
        reportRepository.insertReport(report);
    }

}

