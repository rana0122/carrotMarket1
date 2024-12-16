-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: carrotmarket
-- ------------------------------------------------------
-- Server version	8.0.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `description` text,
  `price` decimal(10,2) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `category_id` bigint DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `status` enum('SALE','SOLD','RESERVED') DEFAULT NULL,
  `used_yn` char(1) NOT NULL DEFAULT 'Y',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `product_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=124 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (23,'접이식 아크릴 의자','필요가 없어서 판매 합니다\r\n\r\n평일 오후 6시 이전 도산공원 인근에서 직거래 가능합니다\r\n\r\n네고는 어렵습니다\r\n\r\n',30000.00,'경기 수원시 영통구 망포로',37.2413370032263,127.041485383994,3,'2024-12-02 07:35:31',6,'SALE','Y'),(24,'까사미아 리클라이너','까사미아 1인용 리클라이너 상태 사진보시는거와같이 최상이고요  구매시기1년 이사로인해 정리중입니다.\r\n',780000.00,'경기 수원시 권선구 곡반정동 652',37.2431221399481,127.035725689007,3,'2024-12-02 08:39:37',6,'SALE','Y'),(25,'유리 오브제','1. 8000원\r\n2. 8000원\r\n3. 11,000원\r\n4. 5,000원\r\n5. 개당 5,000원\r\n\r\n4번 5번은 인테리어용으로 책이나 소품 누르는데 쓰면 느낌있어 보여요',5000.00,'경기 수원시 영통구 망포1동',37.2382550383405,127.05713308294,3,'2024-12-02 08:41:45',6,'SALE','Y'),(26,'미니 크리스마스트리','나무+오너먼트+전구+건전지까지 다 세트로 드릴게요!\r\n큰 트리를 사서 싸게 내놓아요\r\n우드풍으로 느낌있고 예뻐요\r\n오너먼트는 빼서 다시 디자인하셔도 됩니다❣️',29000.00,'경기 수원시 영통구 망포로',37.2413370032263,127.041485383994,3,'2024-12-02 08:45:51',6,'SOLD','Y'),(27,'총 옷장 4개 거의 새상품','운송은 직접하시거나 업체 부르셔야해용',300000.00,'경기 수원시 영통구 망포2동',37.2393339576777,127.045936122933,3,'2024-12-02 08:46:46',6,'SALE','Y'),(28,'울 청키 니트 이불','[라지킹 사이즈] 호주 메리노 울 청키 니트 이불 블랭킷 : 오프화이트 컬러 72*84category\r\n? Made in USA (중국산 X)\r\n\r\n? 한번도 쓰지 않은 새상품 또는 미사용 제품.\r\n\r\n? 일반 니트 이불, 블랭킷 아니구요. 오스트레일리아 메리노에요. 공식 컬러는 Off-White.\r\n\r\n현재 이런 퀄리티, 이런 사이즈로 판매하는 곳은 많지 않아보이네요. 이 브랜드의 현재 상품들은 원가 때문에 호주산 직물을 사용하지 않는다고 하네요.\r\n\r\n? 사이즈는 74*84인치 (약183*213cm)로 큰 편입니다. 배드 라지킹 사이즈 이상이에요. 꽈배기 니트 이불의 경우 사이즈가 커질수록 가격은 몇 배씩 높아지는 경향이 있네요. 즉 50*60이 10만원라면, 60*80은 최소 3,40만원이 되는 듯 해요. 그것도 중국산 니트가 말이죠.\r\n\r\n? 실사는 맨 마지막 이미지에 원단 실제 느낌만요. 전체 이미지 찍기가 어렵네요.\r\n\r\n바로 구매하실 분만 문의주세요.\r\n\r\n경기 부천 GS25 중동서흥점 앞 직거래\r\n\r\n크기\r\n180x210',300000.00,'서울 송파구 가락본동',37.495586073466,127.121767713047,3,'2024-12-02 08:47:55',1,'SALE','Y'),(29,'크리스마스 미니 리스','크리스마스 장식용 미니리스^^\r\n식탁이나 테이블장식이나 오너먼트로 사용하실수 있어요!!\r\n택배만 가능/택포 가격으로 드려요:)\r\n교환 환불 불가x\r\n',29000.00,'서울 송파구 가락본동',37.495586073466,127.121767713047,3,'2024-12-02 08:49:11',1,'SALE','Y'),(30,'까사 알렉시스 트레이와 알파벳 컵세트','까사 알렉시스 트레이와 알파벳 컵세트',200000.00,'서울 송파구 가락본동',37.495586073466,127.121767713047,3,'2024-12-02 08:50:15',1,'SALE','Y'),(31,'미술품 서양화 유화 그림','위아트에서 구매한 프랑스 화가 르누아르의 [선상 파티의 점심] 입니다\r\n현재 판매가격 69만원\r\n\r\n-액자 크기 : 가로 92cm x 세로 72cm   \r\n                     (입체블랙관 액자)\r\n-그림 크기 : 가로 73cm x 세로 53cm (20호)\r\n\r\n프린트 위에 덧방한 그림이 아닌 홍대 미대 출신 전속 작가분이 캔버스 위에 그린 유화 작품입니다\r\n\r\n르누아르 고유의 감성적인 컬러감을 느낄 수 있습니다\r\n',270000.00,'서울 송파구 가락본동',37.495586073466,127.121767713047,3,'2024-12-02 08:51:14',1,'SALE','Y'),(32,'스타벅스 포터블 램프 보니키아 K 화이트','직거래는 금천구 시흥동 시흥5동 주민센터앞,\r\n역삼역 가능합니다.\r\n\r\n일반택배 가능하시고 배송료 별도입니다.\r\n\r\n구입 후 교환 환불은 불가하오니 신중한 구매 부탁드립니다.',40000.00,'서울 송파구 가락본동',37.495586073466,127.121767713047,3,'2024-12-02 09:05:26',1,'SALE','Y'),(33,'까사미아 콤비노 암체어 [다크브라운]','신세계백화점에서 구매 \r\n현재 네이버 최저가 30만원 \r\n기스없이 깨끗하게 사용했어요!\r\n\r\n가격 문의 사절\r\n부피가 커서 직거래 해야합니다.',150000.00,'서울 영등포구 당산동4가',37.5290653986881,126.899075891843,3,'2024-12-02 09:06:33',2,'SALE','Y'),(34,'ㅁ우드 블라인드 가로 98*2개 높이 220cm 창 가로 2미터용이에요','ㅁ우드 블라인드 가로 98*2개 높이 220cm 창 가로 2미터용이에요\r\n두개 일괄 십만원드려요\r\n\r\n상태 좋구요. 클로즈업 컷 참고하셔요\r\n브라켓까지 같이 드립니다\r\n색상도 티크 원목 컬러라 내추럴 인테리어 모던 인테리어 골고루 다 잘 어울려요\r\n\r\n만원 추가하시면 택배로 보내드릴게요\r\n\r\n상태좋고 컬러 좋은 우드 블라인드 가져가세염\r\n꼭 사실분만 시간 정해서 연락주세염\r\n',100000.00,'서울 영등포구 당산동4가',37.5290653986881,126.899075891843,3,'2024-12-02 09:07:00',2,'SALE','Y'),(35,'소프시스 위더스 티테이블','소프시스 위더스 티테이블 팝니다.\r\n사용감 거의 없이 상태 깨끗하며,\r\n가구라 직접 가지러 오셔야 합니다.\r\n\r\n크기\r\n1200×480×320mm',10000.00,'서울 영등포구 당산동4가',37.5290653986881,126.899075891843,3,'2024-12-02 09:07:27',2,'SALE','Y'),(36,'템바보드 원목 리프트 테이블 1200*600','테이블 올리고 내리는게 정말 부드럽게 되고, 내부 은근 많이 수납되어서 좋아요. 약 1년 반 사용한 제품이며 이사하며 내놓게되었습니다. \r\n직접 가져가셔야 합니다.\r\n',120000.00,'서울 영등포구 당산동4가',37.5290653986881,126.899075891843,3,'2024-12-02 09:07:57',2,'SALE','Y'),(37,'곰돌이 저금통','열쇠 포함\r\n한티역 직거래만 가능\r\n',1000.00,'서울 영등포구 당산동4가',37.5290653986881,126.899075891843,3,'2024-12-02 09:08:54',2,'SALE','Y'),(38,'거울 화장대','제품에 하자나 오염 없고 상태좋습니다. 수납할 공간이나 서랍이 많아서 정말 좋아요 :) 아래 링크에서 상세 정보 확인후 질문 있으면 채팅주세요!\r\n\r\n상세정보 참고\r\nhttps://janginshop.com/product/%EC%9E%A5%EC%9D%B8%EA%B0%80%EA%B5%AC-%EC%8A%A4%ED%82%A4%EB%8B%88-%EB%A9%80%ED%8B%B0%EC%88%98%EB%82%A9%ED%99%94%EC%9E%A5%EB%8C%80/29/\r\n\r\n\r\n',40000.00,'서울 성동구 마조로9길',37.5612600745879,127.04039629329,3,'2024-12-02 09:09:34',3,'SALE','Y'),(39,'새상품 인형 판매합니다','?큰인형이고 비닐과 택 그대로 보관해서 \r\n깨끗한 새제품입니다\r\n✔직거래 진행합니다 (인형이 커서 택배는 어려울거 같아요)\r\n?장소: 선정릉역 9호선&수인분당선 및 7호선 강남구청역 인근\r\n⏰️시간: 평일 20:00~ /주말 조율가능⭕️\r\n\r\n❌환불/교환/반품은 불가해요❌\r\n구매하실분들만 연락부탁드립니다☎️\r\n\r\n',25000.00,'서울 성동구 마조로9길',37.5612600745879,127.04039629329,3,'2024-12-02 09:10:25',3,'SALE','N'),(40,'피카 원목 사이드 테이블 협탁','오늘의 집에서 구매 했습니다 :)\r\n이사 준비로 인해서 저렴하게 내놔용!\r\n무게감이 있어서 자차가 있어야 합니다\r\n\r\nhttps://ozip.me/T6PJWAf\r\n\r\n✔️직거래 가로수길 윌비스콘 근처\r\n',100000.00,'서울 성동구 마조로9길',37.5612600745879,127.04039629329,3,'2024-12-02 09:11:01',3,'SALE','Y'),(41,'장식접시','에드나 하이벨 그림 접시\r\n개당 3만원\r\n\r\n크기\r\n23센티\r\n',30000.00,'서울 성동구 마조로9길',37.5612600745879,127.04039629329,3,'2024-12-02 09:11:25',3,'SALE','Y'),(42,'바디럽 침대 매트리스','올해 4월 구매했고 \r\n이사때문에 내놓습니다.\r\n사용감적고 깨끗합니다~! \r\n\r\n크기\r\n가로200×세로150×높이25\r\n크기\r\n23센티\r\n\r\n',100000.00,'서울 성동구 마조로9길',37.5612600745879,127.04039629329,3,'2024-12-02 09:12:07',3,'SALE','Y'),(43,'라이젠 7500f 미개봉','라이젠 amd 7500f 미개봉새제품입니다\r\n봉인씰 그대로 붙어있어요\r\n논현역, 신논현역에서 거래가능',145000.00,'서울 영등포구 신풍로2길',37.4997815296946,126.907677983744,1,'2024-12-02 09:13:17',4,'SALE','Y'),(44,'2019 i7 맥북프로 16인치 512GB','2019 맥북프로 16인치 i7 512GB 판매합니다.\r\n눌림이나 찌그러진 곳 없고 사양은 사진에서 확인하시면 되며 배터리사이클 98입니다.\r\n네고 정중히 사양하고 충전기와 케이블 함께 드리겠습니다.\r\n잠원/고속터미널 일대에서 직거래 하면 좋을 것 같습니다-',700000.00,'서울 영등포구 신풍로2길',37.4997815296946,126.907677983744,1,'2024-12-02 09:13:44',4,'SALE','Y'),(45,'인스탁스 미니9 핑크 팔아요~','한 두번쓰고 더 이상 안쓰게 되서 팔아요!\r\n박스는 없습니다...!',40000.00,'서울 영등포구 신풍로2길',37.4997815296946,126.907677983744,1,'2024-12-02 09:14:12',4,'SALE','Y'),(46,'s10플러스 512g','s10플러스 512g\r\n작년 액정, 배터리 교체 했으며, \r\n이후 커버씌우고 사용했습니다 \r\n외관, 액정 파손 없으며 \r\n생활기스는 존재합니다 (이미지 확인)\r\n20만원\r\n백현마을2단지 \r\n초기화 해두었습니다',200000.00,'서울 영등포구 신풍로2길',37.4997815296946,126.907677983744,1,'2024-12-02 09:14:39',4,'SALE','Y'),(47,'UM2 4in1 올인원 충전독 미개봉','모델명 UMWH -4IN1\r\n현재 최저가 29000원입니다.',13000.00,'서울 영등포구 신풍로2길',37.4997815296946,126.907677983744,1,'2024-12-02 09:15:05',4,'SALE','Y'),(48,'AKG N5005 미개봉','미사용 제품입니다\r\n연락주세요',240000.00,'서울 영등포구 신길1동',37.5111452107739,126.921413655672,1,'2024-12-02 09:15:48',5,'SALE','Y'),(49,'가성비 사무용 게이밍 컴퓨터 팝니다.','게이밍 사무업무 겸용으로 사용할 수 있는 PC 판매합니다.\r\n롤, 발로란트 잘 돌아가고 사무용으로는 차고 넘치는 성능입니다.\r\n\r\n포맷 후 윈도우 설치 해놔서 연결해서 바로 사용하시면 됩니다. \r\n\r\nCPU  INTEL CORE i5-8500 커피레이크\r\n메인보드  ASUS PRIME H310M-K\r\n메모리  삼성전자 16GB\r\n그래픽카드  ASUS GTX 1050Ti 4GB\r\n저장장치  삼성전자 860EVO 250GB SSD\r\n파워서플라이  500W\r\n케이스  앱코 NCORE 식스팬 LUNAR 화이트',300000.00,'서울 영등포구 신길1동',37.5111452107739,126.921413655672,1,'2024-12-02 09:16:11',5,'SALE','Y'),(50,'갤럭시 액티브2','갤럭시 액티브 2 기기 및 박스 구성품 판매합니다\r\n*정품 밴드 및 충전기는 분실\r\n\r\n기기 화면엔 스크래치가 없지만, 테두리에 생활기스 있습니다.',30000.00,'서울 영등포구 신길1동',37.5111452107739,126.921413655672,1,'2024-12-02 09:16:35',5,'RESERVED','Y'),(51,'갤럭시 액티브2','갤럭시 액티브 2 기기 및 박스 구성품 판매합니다\r\n*정품 밴드 및 충전기는 분실\r\n\r\n기기 화면엔 스크래치가 없지만, 테두리에 생활기스 있습니다.',30000.00,'서울 영등포구 신길1동',37.5111452107739,126.921413655672,1,'2024-12-02 09:17:03',5,'SALE','Y'),(52,'엠스톤그루브 풀윤활 클라리온 기계식키보드','Groove F 풀윤활 클라리온S 기계식키보드\r\n\r\n색상 블루 풀배열\r\n실사용기간 1년\r\n적축 45g(추정)\r\n사용감있음(키캡은 구매 후 청소하셔야 합니다)\r\n\r\n구매시 기존박스는 아니지만 다른모델 키보드박스에 담아서 드립니다(사진참고)',100000.00,'서울 영등포구 신길1동',37.5111452107739,126.921413655672,1,'2024-12-02 09:17:26',5,'SALE','Y'),(53,'MX Keys Mini 로지텍','사용감 많이 없고, 박스 있어요. 개포동 양재 거래 가능합니다.',67000.00,'서울 송파구 송파1동',37.5062229082525,127.109316959721,1,'2024-12-02 09:18:22',7,'RESERVED','Y'),(54,'니콘 d200 dslr 풀세트-바디 렌즈 배터리전부 24-85줌렌즈 배터리2개','니콘 d200 dslr 입니다\r\n24.01.05 오전 서비스 센터 가서 문제 없는지 확인 받았구요 (마지막 사진 인증)\r\n바디+24-85줌렌즈 문제 전혀 없음 확인 받았습니다.\r\n\r\n\r\n바디는 국내 구입 정품\r\n렌즈는 일본에서 구매한거라 나중에 수리 받을 일 있을 때 가격이 조금 더 나온대요.\r\n\r\n\r\n바디+24-85렌즈+cf카드3개+카메라가방+배터리2+충전기 풀세트로 드립니다.\r\n\r\n아끼던거라 거의 찍지 않았고 소중히 보관만 한 카메라 입니다.\r\n잘 사용해 주실 분께 드려요.',280000.00,'서울 송파구 송파1동',37.5062229082525,127.109316959721,1,'2024-12-02 09:18:46',7,'SALE','Y'),(55,'아이폰11 128G 박스있음','수리 보상 이력없습니다\r\n하자없고\r\n케이스.끼우고 필름 붙이고 다녀 \r\n깨끗함\r\n\r\n새필름 붙여놓았습니다\r\n배터리 70프로대초반\r\n초기화해서 정확치않습니다',198000.00,'서울 송파구 송파1동',37.5062229082525,127.109316959721,1,'2024-12-02 09:19:17',7,'RESERVED','Y'),(56,'새상품) 춘식이 손난로 보조배터리','춘식이 손난로 보조배터리 \r\n5000mAh\r\n\r\n손난로 기능+보조배터리 기능\r\n새상품',25000.00,'서울 송파구 송파1동',37.5062229082525,127.109316959721,1,'2024-12-02 09:19:41',7,'SALE','Y'),(57,'고프로12 (10회 미만 사용) 128GB 팔아요','취미용으로 샀다가 안써서 팝니다! \r\n여행 가서 몇번 사용하고 집에만 둬서 새거같아요. \r\n사진 그대로 거치대랑 메모리카드 128기가 같이 드리며 구매내역 첨부합니다~\r\n\r\n논현역 부근 직거래\r\n택배시 +5000원입니다',360000.00,'서울 송파구 송파1동',37.5062229082525,127.109316959721,1,'2024-12-02 09:20:09',7,'SALE','Y'),(58,'사용하지 않은 소니 미러리스 카메라 a5100.상태 최상! 메모리카드있음.','모델명 : 소니 미러리스 a5100 \r\n구성품 : 카메라가방, 렌즈(사진참조),\r\n             16기가 메모리카드, 충전아답터\r\n\r\n사용하지 않은 소니 미러리스 카메라 팝니다.\r\n화면을 보면서 셀카찍기 가능한 모델입니다.\r\n흰색이라 이쁘구요.\r\n\r\n사진 배우려고 사놓고 한번도 사용을 안했네요.\r\n요즘 디카가 다시 유행중이라는 이야기 듣고  중고거래 내놓습니다.\r\n\r\n상태 최상인 풀세트 구하기 힘드실텐데 얼른 구입하셔서 24년 가을, 남은 시간동안 멋진 사진과 추억 남기시길 바랍니다.\r\n\r\n감사합니다. 좋은 하루 되세요!',320000.00,'인천 남동구 논현1동',37.4056917351436,126.729318789009,1,'2024-12-02 09:20:45',8,'SALE','Y'),(59,'레오폴드 기계식 유선 키보드 저소음 갈축','레오폴드 저소음 갈축 유선 키보드입니다.\r\n영문 레트로 키캡입니다.',40000.00,'인천 남동구 논현1동',37.4056917351436,126.729318789009,1,'2024-12-02 09:21:09',8,'SALE','N'),(60,'갤럭시링 6호 티타늄블랙','풀박스 구성\r\n미미하게 칠까짐 사진확인\r\n서초구직거래가능합니다',300000.00,'인천 남동구 논현1동',37.4056917351436,126.729318789009,1,'2024-12-02 09:21:32',8,'SALE','Y'),(61,'알파믹 조명','신반포역 직거래 희망합니다~ \r\n집앞으로 오셔서 가져가주시면 5000원 깎아드릴게요!',20000.00,'인천 남동구 논현1동',37.4056917351436,126.729318789009,1,'2024-12-02 09:21:57',8,'SALE','Y'),(62,'스카티카메룬 팬텀 x11 34인치 퍼터 판매합니다','작년에 구매한 스카티카멘룬 팬텀 x11 퍼터입니다 당연히 정품이고요 직진성 정말 좋은 퍼터입니다 34인치인데 제키에 비해 긴거같아 처분하려고합니다 필드다녀와 세척을 못해 모래가 조금 묻어있네요 구매후 처음 커버없이 사용해서 약간은 긁힘있습니다 필드는 10번정도 갔다온거 같네요\r\n시리얼은 각인되어 있습니다. \r\n\r\n사이즈 때문에 직거래 해야할거 같습니다 \r\n직거래는 7호선 장승배기 또는 신대방삼거리역에서 하면될거같습니다 구매후 반품 불가하니 사진 참조해주세요',380000.00,'인천 남동구 논현1동',37.4056917351436,126.729318789009,4,'2024-12-02 09:22:21',8,'SALE','Y'),(63,'스카티카메룬 팬텀 x11 34인치 퍼터','작년에 구매한 스카티카멘룬 팬텀 x11 퍼터입니다 당연히 정품이고요 직진성 정말 좋은 퍼터입니다 34인치인데 제키에 비해 긴거같아 처분하려고합니다 필드다녀와 세척을 못해 모래가 조금 묻어있네요 구매후 처음 커버없이 사용해서 약간은 긁힘있습니다 필드는 10번정도 갔다온거 같네요\r\n시리얼은 각인되어 있습니다. \r\n\r\n사이즈 때문에 직거래 해야할거 같습니다 \r\n직거래는 7호선 장승배기 또는 신대방삼거리역에서 하면될거같습니다 구매후 반품 불가하니 사진 참조해주세요',380000.00,'인천 연수구 송도2동',37.4032746079109,126.641696771317,4,'2024-12-02 09:24:28',9,'SALE','Y'),(64,'Seacsub 세악서브 스쿠버 부력기 bc 부력장치','Seacsub 스쿠버 bc 부력기 판매 합니다. \r\n사진과 같이 매우 깨끗하게 사용했습니다 \r\n스쿠버를 안하게 되어 정리 합니다.\r\n사이즈는 m 이고 제가 일반 옷 105 정도 입는데 넉넉합니다.',250000.00,'인천 연수구 송도2동',37.4032746079109,126.641696771317,4,'2024-12-02 09:24:50',9,'SALE','Y'),(65,'캠핑의자2개','캠핑의자 2개에 5만원에 팝니다!\r\n\r\n선물받았는데 캠핑을 안해서.. 한번도 못썼습니다ㅠㅠ\r\n가방안에 넣으면 선풍기 사이즈 정도 되구요! 의자뒤에 그물망도 있어서 책같은거 넣어놓으면 좋을것 같아요',50000.00,'인천 연수구 송도2동',37.4032746079109,126.641696771317,4,'2024-12-02 09:25:27',9,'SALE','Y'),(66,'침낭-캐리보우 사계절 사각침낭','침낭 깨끗하게 세탁 후 보관 중입니다\r\n\r\n자세한 설명은 링크 열어보시면 보여요\r\n안쪽 체크무늬이고 보들보들 푹신하고 예뻐요\r\n',20000.00,'인천 연수구 송도2동',37.4032746079109,126.641696771317,4,'2024-12-02 09:26:04',9,'SALE','Y'),(67,'모테로스 오토바이 헬멧s +쉴드+블루투스+장갑','모테로스 헬멧S 5개월정도 된것같아여 출퇴근 할때만 사용해서 엄청 깨끗합니다 생활기스 미세하게 있는데 잘안보여요! 오토바이팔면서 헬멧도 같이 당근합니다ㅠㅠ 블루투스는 설치되어있는상태로 드리니 그대로 폰이랑 연결만해서 사용하면되세요!! 쉴드 블루투스 다 따로 구매하는거라 세트로 가져가세오~~~~',280000.00,'인천 연수구 송도2동',37.4032746079109,126.641696771317,4,'2024-12-02 09:30:39',9,'SALE','Y'),(68,'스키부츠 살로몬s-max 265,130flex','3년전구매한 최상급 부츠입니다\r\n시간이없어 스키를 잘 안타서 내놓습니다',180000.00,'부산 수영구 수영동 503-28',35.168570052153,129.120000524836,4,'2024-12-02 09:32:06',10,'SALE','Y'),(69,'요넥스 브이코어97프로 (310g/16×19) 두자루 일괄판매','요넥스 브이코어97프로 (310g) 두자루 일괄 판매합니다 \r\n일괄판매 :28만원  자루당개별판매 :15만원\r\n생활스크레치조금있습니다. ',280000.00,'부산 수영구 수영동 503-28',35.168570052153,129.120000524836,4,'2024-12-02 09:32:39',10,'SALE','Y'),(70,'탁구라켓 팝니다 (새제품)','탁구채, 가방, 공(3개) 팝니다\r\n\r\n총 5 세트 있습니다',30000.00,'서울 영등포구 신길1동',37.5111452107739,126.921413655672,4,'2024-12-02 09:33:16',10,'SOLD','Y'),(71,'탁구라켓 팝니다 (새제품)','탁구채, 가방, 공(3개) 팝니다\r\n\r\n총 5 세트 있습니다',30000.00,'서울특별시 서초구 양재동 201-2',37.4693014,127.033314,4,'2024-12-02 09:33:22',10,'SALE','Y'),(72,'탁구라켓 팝니다 (새제품)','탁구채, 가방, 공(3개) 팝니다\r\n\r\n총 5 세트 있습니다',30000.00,'부산 수영구 수영동 503-28',35.168570052153,129.120000524836,4,'2024-12-02 09:33:30',10,'SALE','Y'),(73,'버팔로돔텐트 그늘막도 가능','15만원정도로 구매했던것같습니다!\r\n\r\n아버지랑 모토캠핑용으로 샀는데\r\n\r\n3번 사용했습니다. 3일 모토캠핑\r\n\r\n구성품다있습니다!\r\n\r\n불그을리거나 그런거 하나도 없습니다!',80000.00,'부산 영도구 영선동4가 1044-6',35.0779153529382,129.04528226002,4,'2024-12-02 09:36:38',11,'SALE','Y'),(74,'A급 타이틀리스트 칼라 로스트볼 17구','안쓰고 남은거 올려요.\r\nA급정도로 상태좋아요.',20000.00,'부산 영도구 영선동4가',35.0768563301435,129.048407670117,4,'2024-12-02 09:37:05',11,'SALE','Y'),(75,'스페셜라이즈드 에스웍스 타막 sl5 팝니다','스페셜라이즈드 에스웍스 타막 sl5\r\n로드바이크 팝니다.\r\n\r\n년식 : 2016년식\r\n구동계 : 듀라에이스 9000 (크랭크만 9100)\r\n스프라켓 32T 장착\r\n프레임사이즈 : 52\r\n휠셋 : zipp\r\n물통케이지 : 아룬델\r\n핸들바, 스템 : 엔비\r\n안장 : 스페셜라이즈드 오우라\r\n페달 : 스피드플레이\r\n\r\n구매하시면 가민 520 이랑\r\n자전거랑 어울리는 비돈 2쌍(4개) 함께 드려요!',3159000.00,'부산 영도구 영선동4가',35.0768563301435,129.048407670117,4,'2024-12-02 09:37:34',11,'SALE','Y'),(76,'입문~초급자가 사용하기 좋은 골프채 SET','드라이버 - 던롭 10.5도 드라이버\r\n\r\n우드 - starata, 포간 3번4번 유틸리티 (3번유틸 새제품)\r\n\r\n아이언 - 아스카아이언세트\r\n(6. 7. 8. 9. PW. AW. SW)\r\n\r\n퍼터 - 트루라인 블레이드 퍼터\r\n\r\n골프백 - 히어트래블 경량골프백\r\n가격 - 28만원\r\n\r\n입문~초급자가 사용하기 좋은 세트입니다 필드에서 사용도 가능합니다!!',280000.00,'부산 영도구 영선동4가',35.0768563301435,129.048407670117,4,'2024-12-02 09:38:03',11,'SALE','Y'),(77,'나이키 여성 테니스화 240','나이키 CZ0222-136 나이키 우먼스 에어 줌 베이퍼 프로 240 사이즈 판매합니다\r\n몇달 실내에서만 사용한 제품입니다.\r\n사용감 있으니 사진 참고해주세요.',30000.00,'부산 영도구 영선동4가',35.0768563301435,129.048407670117,4,'2024-12-02 09:38:31',11,'SALE','Y'),(78,'블랙야크 고어텍스 방수등산자켓','블랙야크 고어텍스 방수등산자켓\r\n\r\n아이더\r\n디자인. 색상 \r\n정말 예뻐요.\r\n\r\n사이즈100\r\n\r\n후드 모자는 없으니 참고 부탁드립니다 \r\n저렴하게 판매합니다 \r\n\r\n매장에서 25만원 정도 구입 했습니다 ',29900.00,'경기 성남시 중원구 성남동',37.4353291482246,127.138944267643,4,'2024-12-02 09:39:26',12,'SALE','Y'),(79,'제라강염5구버너 JB 105 2개','차박하려고 아들하고 함께 구매했는데 전기인버터 사용하려고하니 필요하신분 요긴하게 사용했으면 하구요 한개는 사용중인데 화력이 엄청납니다',100000.00,'경기 성남시 중원구 성남동',37.4353291482246,127.138944267643,4,'2024-12-02 09:39:52',12,'SOLD','Y'),(80,'빈폴골프 여성 파우치','[골프 : 빈폴골프 여성 파우치] \r\n\r\n*2회 사용\r\n*캔버스천 소재. 가볍고 수납력 좋음\r\n*내부 지퍼주머니1개 포함3개, 외부 포켓2개\r\n*사이즈(cm) 가로30 x 세로14 x 높이21',110000.00,'경기 성남시 중원구 성남동',37.4353291482246,127.138944267643,4,'2024-12-02 09:41:50',12,'SALE','Y'),(81,'Dod 키친 쉘프','Dod 키친 쉘프\r\n\r\n캠핑 접게 되어 저렴하게 처분합니다',50000.00,'경기 성남시 중원구 성남동',37.4353291482246,127.138944267643,4,'2024-12-02 09:42:21',12,'SALE','Y'),(82,'전동킥보드 익스트림 스케이트보드 자전거 헬멧RE10(미착용제품)','전동킥보드 익스트림 스케이트보드  자전거 헬멧 RE10 판매합니다. (미착용제품)\r\n미착용 제품이라 새거나 마찬가지 입니다. \r\n상태 매우 양호하며 직거래만 합니다.',19000.00,'경기 성남시 중원구 성남동',37.4353291482246,127.138944267643,4,'2024-12-02 09:42:47',12,'SALE','Y'),(83,'접이식전기킥보드(성인용)','* 접이식전기킥보드(성인용)\r\n* 접어서 이동가능. 전기충전식\r\n* 작동 ON\r\n',80000.00,'서울특별시 서초구 양재동 227-1',37.4696261,127.033314,4,'2024-12-02 09:45:56',13,'SALE','N'),(84,'격투 글러브 ISAMI 두종류','ISAMI격투 글러브 입니다 \r\n사용하지 않은 제품 입니다 \r\n두 종류 모두 사이즈L 입니다 \r\n구매 의향 있으신분 연락 주세요',73000.00,'서울특별시 서초구 양재동 227-1',37.4696261,127.033314,4,'2024-12-02 09:47:03',13,'SALE','Y'),(85,'노스페이스 후리스 (95) 팝니다','작년에 무신사에서 샀는데\r\n한번 입고 옷장에만 있어서 당근합니다\r\n\r\n직거래 선호',100000.00,'경기 성남시 분당구 판교동',37.3898680691971,127.09878136729,2,'2024-12-02 10:16:01',14,'SALE','Y'),(86,'톰브라운 백팩 판매합니다','사용감 있지만 상태 좋은편입니다 \r\n정상가 150이상 제품이었어요 \r\n이촌동 오만원권 직거래 원해요 \r\n문의주세요\r\n',550000.00,'경기 성남시 분당구 판교동',37.3898680691971,127.09878136729,2,'2024-12-02 10:17:59',14,'SALE','Y'),(87,'나이키 조던 11 레트로 그래티튜드 280','나이키 조던 11 레트로 그래티튜드 280\r\n미시착 새상품\r\n사진에 박스상태 확인해 주세요\r\n택배거래시 일반택배만 가능해요\r\n5000원 입니다.',225000.00,'경기 성남시 분당구 판교동',37.3898680691971,127.09878136729,2,'2024-12-02 10:20:41',14,'SALE','Y'),(88,'Tissot 티쏘 PRC 200 크로노그래프 T461','시계중리중이라 저렴히 올려봅니다. 밧데리는 지난주에 새로 갈았습니다. 가죽줄은 새거입니다. 인기많은모델이고 사용감은 조금있지만 깨끗합니다. 사파이어 글래스라 유리는 기스없습니다. 박스하고 구성품 없습니다.\r\n중고라 반품x\r\n삼각지역쪽입니다.\r\n편하게 연락주세요.\r\n\r\nhttps://m.danawa.com/product/product.html?code=55461980&keyword=%ED%8B%B0%EC%8F%98+prc+200+t461&cateCode=18249508',120000.00,'경기 성남시 분당구 판교동',37.3898680691971,127.09878136729,2,'2024-12-02 10:21:07',14,'SALE','Y'),(89,'코스 청버킷햇','코스 남여공용 청 버킷햇 입니다\r\n구매 후 택제거만하고 착용은 안했습니다\r\n새상품 입니다\r\n현재 공홈 품절 제품',20000.00,'경기 평택시 고덕동',37.0495355509024,127.032124505836,2,'2024-12-02 10:21:49',15,'SALE','Y'),(90,'베네통 추리닝 세트 XXL','정품 베너통 트레이닝 세트 \r\n사이즈 XXL (상의 110 하의 35~37 ) 입니다\r\n사용감은 있지만 보플 , 튿어진곳 , 구멍난곳 없고 \r\n하자 1도 없습니다\r\n택배거래 ( 무료) 우선으로 거래 합니다\r\n가품이거나 위 내용과 틀릴시 전액 환불해드립니다',110000.00,'경기 평택시 고덕동',37.0495355509024,127.032124505836,2,'2024-12-02 10:22:16',15,'SALE','Y'),(91,'벤츠 레쟈 트롤리백 보스턴백 네이비','출고하며 받은제품입니다.\r\n바퀴달린 보스턴백입니다.\r\n비행기탑승시 핸드캐리 가능하고요\r\n\r\n미개봉 박스채 신품입니다.\r\n색상은 네이비이고 신발보관함 없습니다.\r\n\r\n거래는 택배거래만 합니다.\r\n01084646674 입니다.',75000.00,'경기 평택시 고덕동',37.0495355509024,127.032124505836,2,'2024-12-02 10:22:44',15,'SALE','Y'),(92,'마레디마리 롱 스커트(Pintuck Maxi Wool Skirt - Beige)','Pintuck Maxi Wool Skirt - Beige (s) 새상품\r\n\r\n사이즈가 너무 커서 새상품 판매합니다. \r\n\r\n쿨거래 시 약간 에눌 가능합니다.',325000.00,'경기 평택시 고덕동',37.0495355509024,127.032124505836,2,'2024-12-02 10:23:14',15,'SALE','Y'),(93,'고급 카멜 양가죽 롱 트렌치코트형 자켓(55)','어깨 38, 가슴 42, 총길이 102\r\n고급 카멜 양가죽 롱 트렌치코트형 자켓(55) 입니다.\r\n몇번 입지 않고 관리 잘해 사용감 없이 깨끗합니다.\r\n부드러운 양가죽 롱 트렌치코트형 자켓으로 딱 떨어지는 핏이 스타일리쉬 합니다.\r\n카멜 색감도 가을~초봄까지 잘 어울리는 색감이며, 안감도 있고 톡톡한 양가죽이 고급스러움을 더합니다.\r\n지금 입기 좋으실꺼에요.\r\n좋은 상품 좋은 가격으로 구입하세요.\r\n\r\n[필독!]\r\n* 반품, 환불 불가\r\n* 직거래불가,택배만가능(무료배송)',99000.00,'경기 용인시 처인구 역북동',37.2462035848036,127.188111660691,2,'2024-12-02 10:24:02',16,'SALE','Y'),(94,'셀린느 100% 캐시미어 자켓 (55사이즈)','청담동 플레그쉽 스토어에서 구매했습니다\r\n100% 캐시미어 소재입니다. 청바지에 입어도 너무 예쁩니다. 2-3번 입었습니다.',1100000.00,'경기 용인시 처인구 역북동',37.2462035848036,127.188111660691,2,'2024-12-02 10:24:57',16,'SOLD','Y'),(95,'[정품] 구찌 GUCCI 406994 사이즈8','브랜드 : 구찌 / GUCCI\r\n사용횟수 : 1회\r\n정품입니다.\r\n사이즈는 8이고  270mm.\r\n\r\n구두주인은 270인데\r\n발은 편하지만\r\n발등이 높아서 로퍼가 불편하고 안어울려서 내놓습니다!\r\n\r\n120만원돈 로퍼인데\r\n이사하면서 박스안에 넣어놓은 보증서랑 더스트를 못찾아서\r\n1회사용이지만 가격 내려서 올립니다... ㅠ\r\n\r\n직거래 가능하셔여 !',790000.00,'경기 용인시 처인구 역북동',37.2462035848036,127.188111660691,2,'2024-12-02 10:25:56',16,'SOLD','Y'),(96,'[L] 루이비통 반팔','description\r\n매장판 제품입니다!\r\n택 달려있는 새상품입니다!',650000.00,'경기 용인시 처인구 역북동',37.2462035848036,127.188111660691,2,'2024-12-02 10:26:39',16,'SALE','Y'),(97,'롤렉스','롤렉스 판매합니다. Date just 1973년 버전입니다. Foundwell에서 재해석한 제품이며, 상세 정보는 아래 링크에서 확인 부탁드립니다.\r\n\r\nhttps://foundwell.com/products/rolex-oyster-datejust#',3800000.00,'경기 용인시 처인구 역북동',37.2462035848036,127.188111660691,2,'2024-12-02 10:27:26',16,'SALE','Y'),(98,'샤넬향수 블루 퍼퓸 50ml','지난달 롯데백화점구매\r\n\r\n몇번안씀',90000.00,'서울 마포구 아현동',37.5544867895968,126.955727151387,2,'2024-12-02 10:28:13',18,'SALE','Y'),(99,'바나나리퍼블릭 비니 빈티지 모자','오래전 구매후 어울리지 않아 5번 미만으로 잠깐 착용만 해본 비니 입니다.\r\n색상은 올리브? 짙은 카키색 입니다.\r\n소재가 모 100%으로 재질이 좋고 상태도 보풀 없이 매우 좋은편 입니다. \r\n크기가 큰편이 아닌거 같아 여성분이나 머리가 보통 사이즈인 남성분이 어울리실거 같습니다.\r\n상태좋고 이쁜 빈티지 비니 데려가주세요~\r\n채팅 주시면 답장 드리겠습니다.',22000.00,'서울 마포구 아현동',37.5544867895968,126.955727151387,2,'2024-12-02 10:28:42',18,'SALE','Y'),(100,'버버리 호보백 미듐','버버리 정품) 호보백 미듐 거의새것 (더스트백 포함)\r\n\r\n90가까이 주고 구매, 정품 100%\r\n가방 컨디션 거의 새것. 1-2회 정도 들었음',450000.00,'서울 마포구 아현동',37.5544867895968,126.955727151387,2,'2024-12-02 10:29:11',18,'SOLD','Y'),(101,'조던1 유니버시티블루 235','조던1 유니버시티블루 235팝니다\r\n3회신었고 닦으면서 신어서 상태좋아요\r\n연락주세요',75000.00,'서울 마포구 아현동',37.5544867895968,126.955727151387,2,'2024-12-02 10:29:39',18,'SALE','Y'),(102,'루이비통 카드 지갑','루이비통 여자 카드 지갑\r\n\r\n구매 시기 : 2024/5/8\r\n\r\n사용 한두번 정도 했구 오염은 하나도 없어요.!\r\n\r\n외관상 새상품 처럼 깨끗하고 카드 넣은 부분만\r\n\r\n사용흔적 아주조금 있다 보시면 되요\r\n\r\n직거래 원하고 관심있으신 분 쪽지주세요\r\n\r\n(영수증이랑 박스 종이가방 포함이요)',470000.00,'서울 마포구 아현동',37.5544867895968,126.955727151387,2,'2024-12-02 10:30:12',18,'SALE','Y'),(103,'JPN 골드 ma-1','광택감이 돋보이는 ma-1입니다 \r\n깔롱부리고 싶은날 입으면 좋아요\r\n\r\n사이즈 : XL\r\n어깨 : 50\r\n총장 : 77\r\n가슴 : 63\r\n팔길이 : 70',40000.00,'서울 중구 약수동',37.5524332632787,127.009003709806,2,'2024-12-02 10:30:51',19,'SALE','Y'),(104,'반려동물 영양학 책','깨끗해요 문고리거래 선호해요',6000.00,'서울 중구 약수동',37.5524332632787,127.009003709806,5,'2024-12-02 12:08:13',19,'SALE','Y'),(105,'중고 자기계발도서 한권 팝니다','사이먼 시넥의 START WITH WHY\r\n이란 책이고 상태 매우 좋습니다\r\n저희동네쪽 직거래 였으면 좋겠습니다',8000.00,'서울 중구 약수동',37.5524332632787,127.009003709806,5,'2024-12-02 12:08:45',19,'SALE','Y'),(106,'흔한남매 책 팝니다','총 11권입니다! 개별구매도 가능합니다!\r\n쿨거래 하시면 네고 조금 해드려요\r\n더 궁금하신건 연락주세요.',120000.00,'서울 중구 약수동',37.5524332632787,127.009003709806,5,'2024-12-02 12:09:17',19,'SALE','Y'),(107,'생각하는 대로 해내는 시간 연금술사','깨끗하고 문제 없습니다 !\r\n직거래는 화곡역 1번출구에서 가능합니다.',10000.00,'서울 중구 약수동',37.5524332632787,127.009003709806,5,'2024-12-02 12:09:48',19,'SALE','Y'),(108,'마음 챙김의 시','사용감 있으나 다른건 문제 없습니다 !:) \r\n직거래는 화곡역 1번출구에서 가능합니다 !',7000.00,'서울 중구 약수동',37.5524332632787,127.009003709806,5,'2024-12-02 12:10:15',19,'SALE','Y'),(109,'2024 수산양식기사 필기(최석희 저)','사놓고 한번도 펼치지 않은 새 책\r\n거래 장소: 마곡 13단지 힐스테이트 정문',15000.00,'서울 중구 약수동',37.5524332632787,127.009003709806,5,'2024-12-02 12:10:42',19,'SALE','Y'),(110,'해커스 소방 관계법규','김정희 선생님 소방관계법규 \r\n한번도 사용안했습니다 ~! \r\n원가:54000원',39000.00,'서울 중구 약수동',37.5524332632787,127.009003709806,5,'2024-12-02 12:11:09',19,'SALE','Y'),(111,'수상한 시리즈 10권','수상한 시리즈 입니다.\r\n딸이 초등학교 5~6학년때  봐서 상태 좋습니다.',50000.00,'서울 중구 약수동',37.5524332632787,127.009003709806,5,'2024-12-02 12:11:40',19,'SALE','N'),(112,'레트로 갬성 MD 보카 어휘 책','세월의 흔적이 있는 책 입니다.\r\n약간의 형광펜과 필기 빼고 깨끗해요.\r\n정가의 가격보다 훨씬 저렴히 판매합니다.\r\n옛날 레트로 갬성 좋아하시는분께 드릴게요.\r\n\r\n다른 옷이랑 책, 상품권 등등 각종 용품도 판매중이니 둘러봐주세요.!!\r\n직거래 희망 지역 : 개화산역, 신방화역, 방화사거리, 새싹어린이교통공원',13000.00,'서울 서초구 방배1동',37.4833697664522,126.994491495079,5,'2024-12-02 12:12:27',20,'SALE','Y'),(113,'책) 신호와 소음','거의 새 책입니다\r\n정가 29000',15000.00,'서울 서초구 방배1동',37.4833697664522,126.994491495079,5,'2024-12-02 12:12:58',20,'SALE','Y'),(114,'여자의모든인생은20대에결정된다 실천편 - 남인숙','펼쳐보지 않은 새 책 입니다:)',5000.00,'서울 서초구 방배1동',37.4833697664522,126.994491495079,5,'2024-12-02 12:13:23',20,'SALE','Y'),(115,'소수몽키 투자다이어리','새거예요\r\n오염 하자 찍힘 일절 없습니다\r\n연베이지 색상이에요\r\n\r\n직거래 : 송파나루역, 석촌역, 잠실역, 석촌호수 송리단길 근방\r\n반택가능',5000.00,'서울 서초구 방배1동',37.4833697664522,126.994491495079,5,'2024-12-02 12:13:54',20,'SALE','Y'),(116,'철학자가 들려주는 철학이야기','내용이 좋아요! \r\n총 78권. \r\n22권이 없어요 마지막 사진 참고해주세요 \r\n한권씩 다 확인을 못했습니다 예민하신분은 피해주세요  반품은  힘들어요^^!\r\n\r\n필요하시면 “오!한국사” 책 서비스로 드립니다',50000.00,'서울 서초구 방배1동',37.4833697664522,126.994491495079,5,'2024-12-02 12:14:20',20,'SALE','Y'),(117,'중고서적 책 판매 / 원씽 실행이답이다 왜일하는가 대화의밀도','한권당 3,000원에 팝니다\r\n거의 새책입니다.',3000.00,'서울 서초구 방배1동',37.4833697664522,126.994491495079,5,'2024-12-02 12:15:12',20,'SALE','Y'),(118,'귀멸의칼날 전권+귀살대견문록,행복의 꽃, 공식캐릭터북','귀멸의칼날 전권+귀살대견문록,행복의 꽃, 공식캐릭터북\r\n거의 새것에\r\n가까운 상태 입니다\r\n1-23권 전권 + 견문록 2권, 행복의 꽃, 캐릭터북 2권입니다(스티커 미사용)',90000.00,'서울 서초구 방배1동',37.4833697664522,126.994491495079,5,'2024-12-02 12:15:40',20,'SALE','Y'),(119,'베르나르 베르베르 신 6권 일괄','6권 일괄 판매합니다\r\n문정시영 근처 직거래 가능하며 \r\n무게가 많이 나가서 반값은 안될것 같구\r\n택배 거래시\r\n구매자님부담이십니다',9000.00,'서울 금천구 독산2동',37.4661325181371,126.899775480394,5,'2024-12-02 12:16:17',21,'SALE','Y'),(120,'메이플스토리','메이플스로리 1~99\r\n너무너무 재미있게 읽은책입니다\r\n사용감있습니다\r\n지금도 소장하고 싶다는걸 집 정리로 판매합니다\r\n일괄 10만원입니다\r\n낱권 판매 x  \r\n중고거래이니 환불x\r\n직거래 희망 택배 착불입니다',100000.00,'서울 금천구 독산2동',37.4661325181371,126.899775480394,5,'2024-12-02 12:17:22',21,'SALE','Y'),(121,'해커스 토익 기출 보카 노랑이','해커스 토익 기출 보카 노랑이 판매합니다\r\n토익 공부할 때 영어 단어 외우는 게 정말 중요하더라고요!\r\n형광펜 자국은 10개 이하이고 사진 첨부해두었습니다\r\n나머지는 깨끗한 새책입니다\r\n직거래는 지하철 8호선 라인에서 가능합니다\r\n반택으로도 보내드려요!',9000.00,'서울 금천구 독산2동',37.4661325181371,126.899775480394,5,'2024-12-02 12:17:50',21,'SALE','Y'),(122,'함수형 자바스크립트 프로그래밍','유인동님의 함수형 자바스크립트 책입니다. 원가는 29,000 이고 상태 A급 낙서없습니다\r\n장소는 장지역, 판교(화,수,목) 거래 가능합니다. 네고없이 쿨거래 원합니다',7000.00,'서울 금천구 독산2동',37.4661325181371,126.899775480394,5,'2024-12-02 12:18:17',21,'SALE','Y'),(123,'시나공 GTQ 포토샵 CC버젼','□ 새책\r\n□ 원가격 22000원',13000.00,'서울 금천구 독산2동',37.4661325181371,126.899775480394,5,'2024-12-02 12:18:42',21,'SALE','Y');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-03 17:01:34