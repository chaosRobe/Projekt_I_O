package vod.web.rest;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.LocaleResolver;
import vod.model.Bakery;
import vod.model.Product;
import vod.service.BakeryService;
import vod.service.ProductService;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/webapi")
public class BakeryRest {
    private final BakeryService bakeryService;
    private final ProductService productService;
    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;
    private final BakeryValidator bakeryValidator;

    @InitBinder
    void initBinder(WebDataBinder binder) {binder.addValidators(bakeryValidator);}

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

    @PostMapping("/bakeries")
    ResponseEntity<?> createBakery(@Validated @RequestBody Bakery bakery, Errors errors, HttpServletRequest request){
        if(errors.hasErrors()){
            Locale locale = localeResolver.resolveLocale(request);
            String message = errors.getAllErrors().stream()
                    .map(er->messageSource.getMessage(er.getCode(),new Object[0],locale))
                    .reduce("error:\n",(accu,err)->accu+err+"\n");
            return ResponseEntity.badRequest().body(message);
        }
        log.info("create bakery {}", bakery);
        bakery = bakeryService.addBakery(bakery);
        log.info("bakery created {}", bakery);
        return ResponseEntity.status(HttpStatus.CREATED).body(bakery);
    }
    @GetMapping("/bakeries/{bakeryId}/products")
    ResponseEntity<List<Product>> getProductsMadeByBakery(@PathVariable("bakeryId") int id){
        log.info("retrive products from bakery {}", id);
        Bakery bakery = bakeryService.getBakeryById(id);
        if(bakery == null){
            return ResponseEntity.notFound().build();
        }else {
            List<Product> products = bakeryService.getProductsInBakery(bakery);
            log.info("there's {} products made by bakery {}", products.size(), bakery.getName());
            return ResponseEntity.ok(products);
        }
    }
}
