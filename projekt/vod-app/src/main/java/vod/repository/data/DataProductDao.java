package vod.repository.data;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import vod.model.Baker;
import vod.model.Bakery;
import vod.model.Product;
import vod.repository.ProductDao;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Primary
public class DataProductDao implements ProductDao {
    private final ProductRepository productRepository;

    @Override
    public List<Product> findAll(){
        return productRepository.findAll();
    }

    @Override
    public Product findById(int id) {return productRepository.findById(id).orElse(null);}

    @Override
    public List<Product> findByBaker(Baker b){return productRepository.findByBaker(b);}

    @Override
    public List<Product> findByBakery(Bakery b){return productRepository.findAllByBakeriesContaining(b);}

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public Product add(Product product) {return productRepository.save(product);}
}
