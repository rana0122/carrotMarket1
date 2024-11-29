package miniproject.carrotmarket1.controller;

import jakarta.servlet.http.HttpSession;
import miniproject.carrotmarket1.entity.Product;
import miniproject.carrotmarket1.entity.User;
import miniproject.carrotmarket1.service.ProductService;
import miniproject.carrotmarket1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public ProductController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
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
}
