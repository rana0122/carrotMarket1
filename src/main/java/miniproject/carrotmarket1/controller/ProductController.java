package miniproject.carrotmarket1.controller;

import jakarta.servlet.http.HttpSession;
import miniproject.carrotmarket1.entity.Category;
import miniproject.carrotmarket1.entity.Product;
import miniproject.carrotmarket1.entity.User;
import miniproject.carrotmarket1.service.CategoryService;
import miniproject.carrotmarket1.service.ProductService;
import miniproject.carrotmarket1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final UserService userService;
    private final CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService, UserService userService, CategoryService categoryService) {
        this.productService = productService;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    //상품 목록 페이지
    @GetMapping
    public String listAllProducts(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false, defaultValue = "ALL") String status,
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "0") int page, // 페이지 번호 추가
            @RequestParam(defaultValue = "8") int size, // 한 페이지에 보여줄 아이템 수
            HttpSession session,
            Model model) {

        // 모든 카테고리 로드
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("status", status);
        model.addAttribute("keyword", keyword);

        Page<Product> products = null;
        Pageable pageable = PageRequest.of(page, size);

        // 세션에서 사용자 위치 정보 가져오기
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null && loggedInUser.getLatitude() != null && loggedInUser.getLongitude() != null) {
            double userLatitude = loggedInUser.getLatitude();
            double userLongitude = loggedInUser.getLongitude();
            double radiusKm = loggedInUser.getRadiusKm();

            // 필터 조건에 따라 반경 내 게시글 조회
            if (categoryId != null) {
                Category selectedCategory = categoryService.findById(categoryId);
                model.addAttribute("selectedCategory", selectedCategory);
                model.addAttribute("selectedCategoryId", categoryId);

                if ("SALE".equals(status)) {
                    products = (keyword != null && !keyword.isEmpty())
                            ? productService.findProductsWithinRadiusByCategoryAndKeyword(userLatitude, userLongitude, radiusKm, categoryId, keyword, pageable)
                            : productService.findAvailableProductsWithinRadiusByCategory(userLatitude, userLongitude, radiusKm, categoryId, pageable);
                } else {
                    products = (keyword != null && !keyword.isEmpty())
                            ? productService.findProductsWithinRadiusByCategoryAndKeyword(userLatitude, userLongitude, radiusKm, categoryId, keyword, pageable)
                            : productService.findProductsWithinRadiusByCategory(userLatitude, userLongitude, radiusKm, categoryId, pageable);
                }
            } else {
                if ("SALE".equals(status)) {
                    products = (keyword != null && !keyword.isEmpty())
                            ? productService.findAvailableProductsWithinRadiusByKeyword(userLatitude, userLongitude, radiusKm, keyword, pageable)
                            : productService.findAvailableProductsWithinRadius(userLatitude, userLongitude, radiusKm, pageable);
                } else {
                    products = (keyword != null && !keyword.isEmpty())
                            ? productService.findProductsWithinRadiusByKeyword(userLatitude, userLongitude, radiusKm, keyword, pageable)
                            : productService.findProductsWithinRadius(userLatitude, userLongitude, radiusKm, pageable);
                }
            }
        } else {
            // 기존 로직 사용
            if (categoryId != null) {
                Category selectedCategory = categoryService.findById(categoryId);
                model.addAttribute("selectedCategory", selectedCategory);
                model.addAttribute("selectedCategoryId", categoryId);

                if ("SALE".equals(status)) {
                    products = (keyword != null && !keyword.isEmpty())
                            ? productService.findByCategoryAndKeyword(categoryId, keyword, pageable)
                            : productService.findAvailableByCategoryId(categoryId, pageable);
                } else {
                    products = (keyword != null && !keyword.isEmpty())
                            ? productService.findByCategoryAndKeyword(categoryId, keyword, pageable)
                            : productService.findByCategoryId(categoryId, pageable);
                }
            } else {
                if ("SALE".equals(status)) {
                    products = (keyword != null && !keyword.isEmpty())
                            ? productService.findAvailableByKeyword(keyword, pageable)
                            : productService.findAvailableItems(pageable);
                } else {
                    products = (keyword != null && !keyword.isEmpty())
                            ? productService.findAllByKeyword(keyword, pageable)
                            : productService.findAll(pageable);
                }
            }
        }
        model.addAttribute("products", products);
        return "products/list";
    }


    //상품 목록 상세조회
    @GetMapping("/detail/{id}")
    public String showProduct(Model model, @PathVariable Long id, HttpSession session) {
        //게시글 조회
        Product product = productService.findItemById(id);
        //로그인 user  정보 조회
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        //판매자(게시글소유자) 정보 조회
        User user = userService.findById(product.getUserId());

        model.addAttribute("product", product);
        model.addAttribute("user", user);
        model.addAttribute("loggedInUser", loggedInUser);  // 로그인한 사용자 정보

        return "products/detail";
    }
    //========================게시글 생성==========================//
    //게시글 생성페이지
    @GetMapping("/write")
    public String showWritePage(HttpSession session, Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("category", categories); // 뷰에 전달
        model.addAttribute("product", new Product());
        return "products/write";

    }
    //게시글 생성 저장
    @PostMapping("/save")
    public String createProduct(@ModelAttribute Product product,
                                @RequestParam("productImages") List<MultipartFile> productImages,
                                HttpSession session) throws IOException {

        User loggedInUser = (User) session.getAttribute("loggedInUser");
        product.setUserId(loggedInUser.getId());

        // 로그인한 사용자의 위치 정보를 상품에 설정
        product.setLocation(loggedInUser.getLocation());
        product.setLatitude(loggedInUser.getLatitude());
        product.setLongitude(loggedInUser.getLongitude());

        //게시글 저장
        productService.saveProductWithImages(product, productImages);

        return "redirect:/products";
    }

    //게시글 수정 페이지
    @GetMapping("/edit/{id}")
    public String showEditPage(@PathVariable Long id, Model model, HttpSession session) {
        Product product = productService.findItemById(id);
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (!product.getUserId().equals(loggedInUser.getId())) {
            return "redirect:/products";
        }

        List<Category> categories = categoryService.findAll();
        model.addAttribute("category", categories);
        model.addAttribute("product", product);
        return "products/edit";
    }

    //게시글 수정 저장
    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id,
                                @ModelAttribute Product product,
                                @RequestParam(value = "productImages", required = false) List<MultipartFile> newImages,
                                @RequestParam(value = "deleteImageIds", required = false) List<Long> deleteImageIds,
                                HttpSession session) throws IOException {

        User loggedInUser = (User) session.getAttribute("loggedInUser");
        Product existingProduct = productService.findItemById(id);

        // 기존 상품이 없거나 로그인한 사용자가 상품 소유자가 아닌 경우
        if (existingProduct == null || !existingProduct.getUserId().equals(loggedInUser.getId())) {
            return "redirect:/products";
        }

        // 기존 상품의 userId를 유지
        product.setUserId(existingProduct.getUserId());

        productService.updateProductWithImages(id, product, newImages, deleteImageIds);
        return "redirect:/products/detail/" + id;
    }




}
