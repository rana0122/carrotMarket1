# 🥕 당근마켓 클론 프로젝트

## 📌 프로젝트 소개
당근마켓 클론 프로젝트는 중고 거래 플랫폼 기능을 구현한 웹 애플리케이션입니다.  
사용자 간 **1:1 채팅**, **위치 기반 거래**, **상품 관리** 등 실상적인 기능을 제공합니다.

---

## 📅 개발 기간

- **2023년 11월 26일 ~ 2023년 12월 16일**

---

## 📊 프로젝트 참여자

| 이름  | 역할                              |
|-----|---------------------------------|
| 김수현 | 팀장 / 회원 및 로그인 기능, 위치정보 관련 기능 구현 |
| 이상엽 | 팀원 / 채팅 기능 구현                   |
| 남궁일 | 팀원 / 게시글 기능 구현                  |
| 이규상 | 팀원 / 관리자 기능 구현                  |


---

## 🛠️ 기술 스택

### **Back-End**
- **Spring Boot**: 서버 사이드 앱 구현
- **MyBatis**: 데이터 접근 및 코드 관리
- **Gradle**: 프로젝트 빌드 및 의염성 관리
- **MySQL**: 데이터베이스 관리
- **Firebase (NoSQL)**: 실시간 채팅 기능 구현

### **Front-End**
- **HTML5 / CSS3**: 화면 및 스타일 관리
- **JavaScript (ES6)**: 동적 페이지 기능 구현
- **Thymeleaf**: 서버 사이드 템플릿 엔진
- **Bootstrap**: 반응형 UI 구현

### **API**
- **Kakao Map API**: 사용자 위치와 거래 계산
- **Google Geocoding API**: 주소를 위도/거로로 변환
- **Google Distance Matrix API**: 사용자 간 거래 및 이동 시간 계산

---

## 🗝 주요 기능

### 1. **회원 관리**
- **회원가입 / 로그인**: 프로필 사진 등록 및 위치 정보 제공
- **회원 정보 수정**: 사용자 주소 및 비밀번호 변경

### 2. **상품 관리**
- **상품 등록 / 수정 / 삭제**: 제목, 설명, 컨텐츠, 이미지, 가격 입력
- **판매 상황 관리**: 판매중, 예약중, 판매완료로 상황 변경 가능
- **상품 숨기기능**: 특정 상품을 사용자에게 숨기로 처리

### 3. **거래 기능**
- **1:1 채팅**: 구매자와 판매자 간 실시간 채팅 기능
- **위치 기반 거래**: 사용자의 반경 설정에 따라 근처 상품만 표시
- **거래 거로 및 시간 계산**: Kakao Map 및 Google API를 통해 사용자 간 거로 및 시간 계산

### 4. **관리자 기능**
- **신고 관리**: 부적절한 게시글 및 사용자를 신고하고 관리
- **사용자 계정 정지**: 관리자가 신고 내영을 검토 후 계정 상황 변경

---

## 💽 폴더 구조

```plaintext
src/
├── main/
│   ├── java/com/carrotmarket/
│   │   ├── config/         # 설정 관리 클래스
│   │   ├── controller/     # 컨트롤러 클래스
│   │   ├── entity/         # 데이터 엔티티 클래스
│   │   ├── dao/            # MyBatis 매퍼 인터페이스
│   │   ├── repository/     # 데이터 접근 레이어
│   │   └── service/        # 서비스 로직
│   ├── resources/
│   │   ├── mapper/         # 동적쿼리 활용 xml
│   │   ├── static/         # CSS, JS, 이미지 파일
│   │   ├── templates/      # Thymeleaf 템플릿
│   │   └── application.properties # 설정 파일
└── test/
    └── java/               # 테스트 코드
```

---
## 📊 산출물
- [요구사항 명세서](src/main/resources/docs/요구사항명세서.xlsx)
- [화면 및 기능 설계서](src/main/resources/docs/화면 및 기능 설계서(발표).pptx)

---

## 💡 향후 개선 사항
- 실시간 알림 기능 추가
- 결제 시스템 연동
- 리뷰 및 평점 시스템 구현방정이 되는 파일