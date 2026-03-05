package vod.web.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vod.model.Bakery;
import vod.service.BakeryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BakeryRest {
    private final BakeryService bakeryService;
    @GetMapping("/bakeries")
    List<Bakery> getBakeries(){
        log.info("retrive bakeries");
        List<Bakery> bakeries = bakeryService.getAllBakeries();
        log.info("{} bakeries found", bakeries.size());
        return bakeries;
    }
}
