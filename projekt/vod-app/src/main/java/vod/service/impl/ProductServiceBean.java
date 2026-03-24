package vod.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import vod.model.Bakery;
import vod.repository.BakeryDao;
import vod.repository.BakerDao;
import vod.repository.ProductDao;
import vod.model.Baker;
import vod.model.Product;
import vod.service.ProductService;

import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class ProductServiceBean implements ProductService {

    private static final Logger log = Logger.getLogger(ProductService.class.getName());
    private final PlatformTransactionManager transactionManager;



    private BakerDao bakerDao;
    private BakeryDao bakeryDao;
    private ProductDao productDao;

    @Autowired
    public ProductServiceBean(BakerDao bakerDao, BakeryDao bakeryDao, ProductDao productDao, PlatformTransactionManager transactionManager) {
        this.bakerDao = bakerDao;
        this.bakeryDao = bakeryDao;
        this.productDao = productDao;
        this.transactionManager = transactionManager;
    }

    public List<Product> getAllProducts() {
        log.info("searching all products...");
        return productDao.findAll();
    }

    public List<Product> getProductsByBaker(Baker d) {
        log.info("searching products by baker " + d.getId());
        return productDao.findByBaker(d);
    }

    public List<Product> getProductsInBakery(Bakery c) {
        log.info("searching products baked in bakeries " + c.getId());
        return productDao.findByBakery(c);
    }

    public Product getProductById(int id) {
        log.info("searching products by id " + id);
        return productDao.findById(id);
    }

    public List<Bakery> getAllBakeries() {
        log.info("searching all bakeries");
        return bakeryDao.findAll();
    }

    public List<Bakery> getBakeriesByProduct(Product m) {
        log.info("searching bakeries by product " + m.getId());
        return bakeryDao.findByProduct(m);
    }

    public Bakery getBakeryById(int id) {
        log.info("searching bakeries by id " + id);
        return bakeryDao.findById(id);
    }

    public List<Baker> getAllBakers() {
        log.info("searching all bakers");
        return bakerDao.findAll();
    }

    public Baker getBakerById(int id) {
        log.info("searching baker by id " + id);
        return bakerDao.findById(id);
    }

   /* @Override
    public Product addProduct(Product m) {
        log.info("about to add product " + m);
        return productDao.add(m);
    }*/

    /*
    @Override
    public Product addProduct(Product m) {
        log.info("about to add product " + m);
        TransactionStatus ts = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            m = productDao.add(m);
            if(m.getName().equals("GG")){
                throw new RuntimeException("GG");
            }
            transactionManager.commit(ts);
        }catch(RuntimeException e){
            transactionManager.rollback(ts);
            throw e;
        }
        return m;
    }
    */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Product addProduct(Product m) {
        log.info("about to add product " + m);
        m = productDao.add(m);
        if (m.getName().equals("GGs")) {
            throw new RuntimeException("GGs");
        }
        return m;
    }
    @Override
    public Baker addBaker(Baker d) {
        log.info("about to add baker " + d);
        return bakerDao.add(d);
    }

}
