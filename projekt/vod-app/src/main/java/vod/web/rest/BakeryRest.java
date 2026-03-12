package vod.web.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import vod.model.Bakery;
import vod.model.Product;
import vod.service.BakeryService;
import vod.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/webapi")
public class BakeryRest {
    private final BakeryService bakeryService;
    private final ProductService productService;
    @GetMapping("/bakeries")
    List<Bakery> getBakeries(@RequestParam(value = "phrase",required = false) String phrase, @RequestHeader(value = "custom-header",required = false) String customHeader,@CookieValue(value = "some-cookie",required = false) String someCookie) {
        log.info("retrive bakeries");
        log.info("phrase {}", phrase);
        log.info("custom-header {}", customHeader);
        log.info("some cookie {}", someCookie);
        List<Bakery> bakeries = bakeryService.getAllBakeries();
        log.info("{} bakeries found", bakeries.size());
        return bakeries;
    }
    @GetMapping("/bakeries/{id}")
    ResponseEntity<Bakery> getBakeries(@PathVariable("id") int id){
        log.info("retrive bakeries {}", id);
        Bakery bakeries = bakeryService.getBakeryById(id);
        log.info("bakeries found: {}", bakeries);
        if(bakeries != null){
            return ResponseEntity.status(200).body(bakeries);
        }else {
            return ResponseEntity.notFound().build();
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
    @PostMapping("/bakeries")
    ResponseEntity<Bakery> createBakery(@RequestBody Bakery bakery){
        log.info("create bakery {}", bakery);
        bakery = bakeryService.addBakery(bakery);
        log.info("bakery created {}", bakery);
        return ResponseEntity.status(HttpStatus.CREATED).body(bakery);
    }
}
