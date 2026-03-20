package vod.web.ui;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vod.model.Baker;
import vod.model.Bakery;
import vod.model.Product;
import vod.service.BakeryService;
import vod.service.ProductService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final BakeryService bakeryService;
    private final ProductService productService;

    @GetMapping("/products")
    ///movies?cinemaId=3
    String getMovies(
            Model model,
            @RequestParam(value = "cinemaId", required = false) Integer cinemaId,
             @RequestParam(value = "bakerId", required = false) Integer bakerId){
        log.info("about to display movies list in cinema {}", cinemaId);
        if (cinemaId != null) {
            Bakery bakery = bakeryService.getBakeryById(cinemaId);
            List<Product> products = bakeryService.getProductsInBakery(bakery);
            model.addAttribute("products", products);
            model.addAttribute("title", "Products in bakery" + bakery.getName());
        } else if(bakerId != null) {
            Baker baker = productService.getBakerById(bakerId);
            List<Product> products = productService.getProductsByBaker(baker);
            model.addAttribute("products", products);
            model.addAttribute("title", "products made by baker:" + baker.getLastName());
        } else {
            List<Product> products = productService.getAllProducts();
            model.addAttribute("products", products);
            model.addAttribute("title", "products");
        }
        return "productsView";
    }
}
