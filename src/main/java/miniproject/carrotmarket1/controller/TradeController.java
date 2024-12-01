package miniproject.carrotmarket1.controller;

import jakarta.servlet.http.HttpSession;
import miniproject.carrotmarket1.entity.Category;
import miniproject.carrotmarket1.entity.Product;
import miniproject.carrotmarket1.entity.User;
import miniproject.carrotmarket1.service.ProductService;
import miniproject.carrotmarket1.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/products/myproduct")
public class TradeController {
    private TradeService tradeService;
    private ProductService productService;

    @Autowired
    public TradeController(TradeService tradeService, ProductService productService) {
        this.tradeService = tradeService;
        this.productService = productService;
    }


    /* 로그인된 id가 작성한 글만 뷰에 올리기*/
    @GetMapping("/{userId}")
    public String showMyProduct(@PathVariable Long userId, Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        List<Product> products = productService.findByUserId(userId);
        List<Product> trade = tradeService.findByBuyerId(userId);
        model.addAttribute("products", products);
        model.addAttribute("user", loggedInUser);
        model.addAttribute("trades", trade);

        return "products/myproduct";
    }
}
