package miniproject.carrotmarket1.dao.MySQL;

import miniproject.carrotmarket1.entity.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReportDAO {

    //신고 목록 조회(필터 기능)-- xml의 동적 쿼리로 구현
    List<Report> getReportList(
            @Param("startDate") String startDate,
            @Param("endDate") String endDate,
            @Param("status") String status
    );;

    //property = "reporter" -> Report엔티티에 있는 User reporter 가져온것
    //column = "reporter_id" -> MySQL root계정 report테이블에 있는 컬럼을 가져온것
    //javaType = User.class -> User클래스에서 가져온 것(User클래스안에 있는 id,name 등등 사용)
    //one = @One(select = "miniproject.carrotmarket1.dao.MySQL.UserDAO.selectById"))
    //          -> one = @One() : one은 하나만 출력해줄 때 사용
    //          -> select = "miniproject.carrotmarket1.dao.MySQL.UserDAO.selectById
    //              ->
    //신고 상세 조회
    @Select("select * from report where id=#{id}")
    @Results({
            @Result(property = "reporter", column = "reporter_id", javaType = User.class,
                    one = @One(select = "miniproject.carrotmarket1.dao.MySQL.UserDAO.selectById")),
            @Result(property = "category", column = "category_id", javaType = Category.class,
                    one = @One(select = "miniproject.carrotmarket1.dao.MySQL.CategoryDAO.selectById")),
            @Result(property = "product", column = "product_id", javaType = Product.class,
                    one = @One(select = "miniproject.carrotmarket1.dao.MySQL.ProductDAO.findById")),
    })
    Report getReportById(Long id);

    //신고 처리 상태 변경
    @Update("update report set status=#{status}, resolved_at=#{resolvedAt} where id=#{id}")
    void updateReportStatus(Report report);

}
