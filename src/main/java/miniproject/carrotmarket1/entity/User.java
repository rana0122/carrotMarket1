package miniproject.carrotmarket1.entity;

import lombok.*;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    //스네이크 표기법으로 사용하는 이유 : column 과 동일한 이름으로 지정하면 컬럼 매핑을 피할 수 있다.
    private Long id;
    private String username;
    private String password;
    private String email;
    private String location;
    private Double latitude;
    private Double longitude;
    private String profileImage;
    private Timestamp createdAt;
    private String userGroup;
    private String firebaseUid;
    private Double radiusKm;
}