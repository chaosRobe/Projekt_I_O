package vod.web.ui;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vod.model.Bakery;
import vod.model.Product;
import vod.service.BakeryService;
import vod.service.ProductService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BakeryController {
    private final BakeryService bakeryService;
    private final ProductService productService;

    @GetMapping("/bakeries")
    String getBakeries(Model model, @RequestParam(value = "productId",required = false) Integer productId) {
        log.info("displaying bakeries");
        if (productId != null) {
            Product product = productService.getProductById(productId);
            List<Bakery> bakeries = bakeryService.getBakeriesByProduct(product);
            model.addAttribute("bakeries", bakeries);
            model.addAttribute("title", "Bakeries with product: " + product.getName());
        }else {
            List<Bakery> bakeries = bakeryService.getAllBakeries();
            model.addAttribute("bakeries", bakeries);
            model.addAttribute("title", "Bakeries");
        }
        return "bakeriesView";
    }
}
