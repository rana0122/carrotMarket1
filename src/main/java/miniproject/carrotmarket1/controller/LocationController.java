package miniproject.carrotmarket1.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class LocationController {
    // application.properties에 설정된 API 키를 가져옴
    @Value("${google.api.key}")
    private String apiKey;
    // kakao 주소 api JavaScript 키 가져오기
    @Value("${kakao.restApi.Key}")
    private String kakaoApiKey;

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
}
