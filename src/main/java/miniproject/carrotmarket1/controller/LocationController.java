package miniproject.carrotmarket1.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import miniproject.carrotmarket1.dto.LocationRequest;
import miniproject.carrotmarket1.dto.Product;
import miniproject.carrotmarket1.dto.User;
import miniproject.carrotmarket1.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class LocationController {
    // application.properties에 설정된 API 키를 가져옴
    @Value("${google.api.key}")
    private String apiKey;
    // kakao 주소 api JavaScript 키 가져오기
    @Value("${kakao.restApi.Key}")
    private String kakaoApiKey;
    private final UserService userService;

    // 위도, 경도로 주소 조회
    @GetMapping("/get-address")
    @ResponseBody
    public String getAddress(@RequestParam double latitude, @RequestParam double longitude) {
        String url = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + latitude + "," + longitude + "&key=" + apiKey + "&language=ko";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

    // 주소로 위도, 경도 조회
    @GetMapping("/get-latlng")
    @ResponseBody
    public String getLatLng(@RequestParam("address") String address) {
        String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + address + "&key=" + apiKey;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }
    @PostMapping("/update-location")
    public ResponseEntity<String> updateLocation(@RequestBody LocationRequest locationRequest, HttpSession session) {

        User loggedInUser = userService.getLoggedInUser(session);
        // 로그인 여부 체크
        if (loggedInUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }
        //  최초 로그인시에만 자동 위치업데이트를 한다.
        if (locationRequest.getLatitude() != null && locationRequest.getLongitude() != null
             && locationRequest.getLocation() != null) {

            // 위치 정보를 업데이트
            userService.updateUserLocation(loggedInUser.getId(),
                                            locationRequest.getLatitude(),
                                            locationRequest.getLongitude(),
                                            locationRequest.getLocation());

            // 세션 내 사용자 정보 업데이트
            loggedInUser.setLocation(locationRequest.getLocation());
            loggedInUser.setLatitude(locationRequest.getLatitude());
            loggedInUser.setLongitude(locationRequest.getLongitude());

            session.setAttribute("loggedInUser", loggedInUser);


            return ResponseEntity.ok("Location updated successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid location data");
        }
    }


    // 주소를 검색하고 관련된 정보 반환 (카카오 API와 통신)
    @GetMapping("/get-address-kakao")
    @ResponseBody
    public String getAddressFromKakao(@RequestParam String query) {
        String url = "https://dapi.kakao.com/v2/local/search/address.json?query=" + query;

        // REST Template으로 카카오 API 호출
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + kakaoApiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        return response.getBody();
    }

    // 주소로 위도, 경도 정보 가져오기
    @GetMapping("/get-latlng-kakao")
    @ResponseBody
    public String getLatLngFromKakao(@RequestParam("address") String address) {
        String url = "https://dapi.kakao.com/v2/local/search/address.json?query=" + address;

        // REST Template으로 카카오 API 호출
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + kakaoApiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        return response.getBody();
    }

    public String calculateDistanceKakao(User originUser, Product destinationProduct, String mode) {
        String url = "https://apis-navi.kakaomobility.com/v1/directions?origin="
                + originUser.getLongitude() + "," + originUser.getLatitude()
                + "&destination=" + destinationProduct.getLongitude() + "," + destinationProduct.getLatitude()
                + "&car_type=1"; // priority 관련 파라미터 제거

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + kakaoApiKey);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response.getBody());
            JsonNode durationNode = rootNode.path("routes").get(0).path("summary").path("duration");

            int durationInSeconds = durationNode.asInt();
            int hours = durationInSeconds / 3600;
            int minutes = (durationInSeconds % 3600) / 60;

            StringBuilder durationString = new StringBuilder();
            if (hours > 0) {
                durationString.append(hours).append("시간 ");
            }
            if (minutes > 0) {
                durationString.append(minutes).append("분 ");
            }

            return durationString.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "정보 없음";
    }

    @GetMapping("/navigate")
    public void navigate(
            @RequestParam String  userLocation,
            @RequestParam double userLat,
            @RequestParam double userLng,
            @RequestParam String  destLocation,
            @RequestParam double destLat,
            @RequestParam double destLng,
            HttpServletResponse response) throws IOException {

        // 한글 문자열을 URL 인코딩
        String from = URLEncoder.encode(userLocation, StandardCharsets.UTF_8);
        String to = URLEncoder.encode(destLocation, StandardCharsets.UTF_8);

        // 카카오 네비게이션 URL 생성
        String naviUrl = String.format(
                "https://map.kakao.com/link/from/%s,%f,%f/to/%s,%f,%f",
                from, userLat, userLng, to, destLat, destLng
        );

        // 리다이렉트
        response.sendRedirect(naviUrl);
    }


}
