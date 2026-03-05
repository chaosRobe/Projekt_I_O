package vod.repository.mem;

import org.springframework.stereotype.Component;
import vod.repository.ProductDao;
import vod.model.Bakery;
import vod.model.Baker;
import vod.model.Product;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MemProductDao implements ProductDao {
    @Override
    public List<Product> findAll() {
        return SampleData.products;
    }

    @Override
    public Product findById(int id) {
        return SampleData.products.stream().filter(m -> m.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Product> findByDirector(Baker d) {
       return SampleData.products.stream().filter(m -> m.getBaker() == d).collect(Collectors.toList());
    }

    @Override
    public List<Product> findByCinema(Bakery c) {
        return SampleData.products.stream().filter(m -> m.getBakeries().contains(c)).collect(Collectors.toList());
    }

    @Override
    public Product add(Product m) {
        int max = SampleData.products.stream().max((m1, m2) -> m1.getId() - m2.getId()).get().getId();
        m.setId(++max);
        SampleData.products.add(m);
        return m;
    }
}
