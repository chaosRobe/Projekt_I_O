package vod.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import vod.model.Bakery;
import vod.repository.BakeryDao;
import vod.repository.ProductDao;
import vod.repository.mem.MemBakeryDao;
import vod.repository.mem.MemProductDao;
import vod.service.impl.BakeryServiceBean;

import java.util.List;

public class VodServiceMain {

    public static void main(String[] args) {
        System.out.println("Let's find bakeries!");

        // service preparation przygotowanie serwisu który używa repo jednego cinemaservice
        //chcemy wyswielic lisę kin, repo dają dostęp do nich, ale z punktu widzenia apliakcji
        //powiinismy komunikowac sie przez serwis
        //serwis jest taka fasadą ponad repozytoriami - architektura wielowarstwowa
        //BakeryDao cinemaDao = new MemBakeryDao();
        //ProductDao movieDao = new MemProductDao();
        ApplicationContext context = new AnnotationConfigApplicationContext("vod");
        BakeryService service = context.getBean(BakeryService.class);
        BakeryService service2 = context.getBean(BakeryService.class);
        //przygotowanie serwisu ma jedna impelemntacja, moze byc ich wiecej
        //ta impelemntacja potrzebuje w swoim konstruktorze dostarczyc obiekty dao
        //tu mamy znowu dwa intefejsy i trzeba sie zastanowic z których impelemntacji skorzystać
        //BakeryService service = new BakeryServiceBean(cinemaDao, movieDao);

        // service use
        //cinemaservice ma api zwraca wsyzstkie kina
      //  tylko że ten serwis trzeba przygotowac, trzeba pozyskać tą usługe.
        List<Bakery> bakeries = service.getAllBakeries();
        System.out.println(bakeries.size() + " bakery found:");
        bakeries.forEach(System.out::println);
        String foo = context.getBean(String.class);
        System.out.println("foo string: "+foo);
    }
}
