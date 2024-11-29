package miniproject.carrotmarket1.controller;

import jakarta.servlet.http.HttpSession;
import miniproject.carrotmarket1.entity.Category;
import miniproject.carrotmarket1.entity.Product;
import miniproject.carrotmarket1.entity.User;
import miniproject.carrotmarket1.service.CategoryService;
import miniproject.carrotmarket1.service.ProductService;
import miniproject.carrotmarket1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
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
    public String listAllProducts(Model model) {
        List<Product> products = productService.findAll();
        Long categoryId= 1L;
        //xml 연동 테스트
        List<Product> productsFiltered = productService.findAvailableItemsByCategory(categoryId);
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
}
