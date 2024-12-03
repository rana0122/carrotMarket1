package miniproject.carrotmarket1.repository;

import miniproject.carrotmarket1.entity.Category;
import miniproject.carrotmarket1.entity.Report;

import java.util.List;


public interface ReportRepository {

    //신고 목록 조회(페이징 기능)
    List<Report> getReportListPagination(String startDate, String endDate, String status, int size, int offset, String tag, String search);
    //신고 목록 조회(필터 기능)
    long countFilterReports(String startDate, String endDate, String status);
    //신고 상세 조회
    Report getReportById(Long id);
    //신고 처리 상태 변경
    void updateReportStatus(Report report);
    //게시글 숨김 관리
    void updateProductLock(Long productId, String lockYn);
    //계정 잠금 기능
    void updateUserLock(Long userId, String lockYn);
    //신고 페이지 카테고리
    List<Category> getCategoriesByRange();
    //신고 내용 insert 기능
    void insertReport(Report report);

}
