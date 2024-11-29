package miniproject.carrotmarket1.service;

import miniproject.carrotmarket1.dao.MySQL.ProductDAO;
import miniproject.carrotmarket1.entity.Category;
import miniproject.carrotmarket1.entity.Product;
import miniproject.carrotmarket1.entity.ProductImage;
import miniproject.carrotmarket1.repository.CategoryRepository;
import miniproject.carrotmarket1.repository.ProductImageRepository;
import miniproject.carrotmarket1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final CategoryRepository categoryRepository;

    @Value("${file.upload-dir-item}")
    private String uploadDir;

   @Autowired
    public ProductService(ProductRepository productRepository,
                          ProductImageRepository productImageRepository,
                          CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
       this.productImageRepository = productImageRepository;
       this.categoryRepository = categoryRepository;
   }

    //게시글 전체 목록 조회
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    //판매중인 상품에 대한 게시글 목록 조회
    public List<Product> findAvailableItems() {
        return productRepository.findAvailableItems();
    }

    //xml 연동 테스트
    public  List<Product> findAvailableItemsByCategory(Long category) {
       return  productRepository.findProductsByConditions(category);
    }

    //ID로 상품 상세 조회
    public Product findItemById(Long id){
        return productRepository.findById(id);
    }

    // 게시글 저장 with 여러 사진 업로드
    @Transactional
    public void saveProductWithImages(Product product, List<MultipartFile> productImages) throws IOException {
        // 1. 기본 경로 설정
        Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();

        // 2. 업로드 디렉토리가 없으면 생성
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // 3. 현재 시간 설정 및 상품 상태 설정
        product.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        product.setStatus("SALE");

        // 4. 상품 정보 저장
        productRepository.insertProduct(product);

        // 5. 이미지 파일 처리 및 저장
        if (productImages != null && !productImages.isEmpty()) {

            for (MultipartFile imageFile : productImages) {
                if (!imageFile.isEmpty()) {
                    // 먼저 ProductImage 엔티티를 생성하고 저장
                    String originalFilename = imageFile.getOriginalFilename();
                    String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));

                    // 임시 이미지 URL 설정 (나중에 업데이트 됨)
                    ProductImage productImage = ProductImage.builder()
                            .productId(product.getId())
                            .uploadedAt(new Timestamp(System.currentTimeMillis()))
                            .build();

                    // ProductImage 저장하여 ID 생성
                    productImageRepository.insertProductImage(productImage);

                    // 생성된 ID를 사용하여 파일명 생성
                    String fileName = productImage.getId() + "_" + product.getId() + fileExtension;

                    // 파일 저장
                    Path filePath = uploadPath.resolve(fileName);
                    Files.copy(imageFile.getInputStream(), filePath, java.nio.file.StandardCopyOption.REPLACE_EXISTING);

                    // 실제 이미지 URL로 업데이트
                    productImage.setImageUrl("/itemimages/" + fileName);
                    productImageRepository.updateProductImage(productImage);  // 이미지 URL 업데이트
                }
            }
        }
    }

    public List<Category> getAllCategories() {
        // 모든 카테고리 조회
        return categoryRepository.findAll();
    }
}
