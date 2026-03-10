package vod.repository.mem;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import vod.repository.BakeryDao;
import vod.model.Bakery;
import vod.model.Product;

import java.util.List;
import java.util.stream.Collectors;

@Repository("bakeryDao")
public class MemBakeryDao implements BakeryDao {

    @Override
    public List<Bakery> findAll() {
        return SampleData.bakeries;
    }

    @Override
    public Bakery findById(int id) {
        return SampleData.bakeries.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Bakery> findByMovie(Product m) {
        return SampleData.bakeries.stream().filter(c -> c.getProducts().contains(m)).collect(Collectors.toList());
    }
}
