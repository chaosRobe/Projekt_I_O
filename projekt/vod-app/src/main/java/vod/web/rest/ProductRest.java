package vod.web.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;
import vod.model.Bakery;
import vod.model.Product;
import vod.service.BakeryService;
import vod.service.ProductService;

import java.util.List;
import java.util.Locale;
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/webapi")
public class ProductRest {
    private final BakeryService bakeryService;
    private final ProductService productService;
    private final MessageSource messageSource;
    private  final LocaleResolver localeResolver;

    @GetMapping("/products")
    List<Product> getProducts() {
        log.info("getProducts");
        List<Product> productList = productService.getAllProducts();
        log.info("{}", productList);
        return productList;
    }
    @GetMapping("/products/{id}")
    ResponseEntity<Product> getProduct(@PathVariable("id") int id) {
        log.info("getProduct {}", id);
        Product product = productService.getProductById(id);
        log.info("{} product found", product);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else {
            return ResponseEntity.ok(product);
        }
    }
    @GetMapping("/products/{productId}/bakeries")
    ResponseEntity<List<Bakery>> getBakeriesMakingProduct(@PathVariable("productId") int id){
        log.info("retrive bakeries {}", id);
        Product product = productService.getProductById(id);
        if(product == null){
            return ResponseEntity.notFound().build();
        }else {
            List<Bakery> bakeries = bakeryService.getBakeriesByProduct(product);
            log.info("there's {} bakeries making product {}", bakeries.size(), product.getName());
            return ResponseEntity.ok(bakeries);
        }
    }
}
