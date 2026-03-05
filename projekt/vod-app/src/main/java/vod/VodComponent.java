package vod;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
import vod.model.Bakery;
import vod.service.BakeryService;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class VodComponent implements CommandLineRunner,ApplicationListener<ContextRefreshedEvent> {

    private final BakeryService bakeryService;
    public VodComponent(BakeryService bakeryService) {
        this.bakeryService = bakeryService;
    }
    @PostConstruct
    void init() {
        log.info("in post construct");
    }
    @Override
    public void run(String... args) throws Exception {
        log.info("program arguments: {}", Arrays.toString(args));
    }
    @EventListener
    public void eventListener(ContextRefreshedEvent event) {log.info("on context refreshed (from annotated method)");}
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event){
        log.info("context refreshed");
        List<Bakery> bakeries = bakeryService.getAllBakeries();
        log.info("{} bakeries found", bakeries.size());
        bakeries.forEach(Bakery->log.info("{}", Bakery));
    }
}
