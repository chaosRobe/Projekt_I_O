package vod.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import vod.model.Bakery;
import vod.model.Product;
import vod.repository.BakeryDao;
import vod.repository.ProductDao;
import vod.service.BakeryService;

import java.util.List;
import java.util.logging.Logger;

@Service
@Scope("prototype")
public class BakeryServiceBean implements BakeryService {

    private static final Logger log = Logger.getLogger(BakeryService.class.getName());

    private BakeryDao bakeryDao;
    private ProductDao productDao;

    //@Autowired
    public BakeryServiceBean(BakeryDao bakeryDao, ProductDao productDao) {
        log.info("creating bakery service bean");
        this.bakeryDao = bakeryDao;
        this.productDao = productDao;
    }

    @Override
    public Bakery getBakeryById(int id) {
        log.info("searching bakery by id " + id);
        return bakeryDao.findById(id);
    }

    @Override
    public List<Product> getProductsInBakery(Bakery c) {
        log.info("searching products baked in bakery " + c.getId());
        return productDao.findByCinema(c);
    }

    @Override
    public List<Bakery> getAllBakeries() {
        log.info("searching all bakeries");
        return bakeryDao.findAll();
    }

    @Override
    public List<Bakery> getBakeriesByProduct(Product m) {
        log.info("searching bakeries by product " + m.getId());
        return bakeryDao.findByMovie(m);
    }

}
