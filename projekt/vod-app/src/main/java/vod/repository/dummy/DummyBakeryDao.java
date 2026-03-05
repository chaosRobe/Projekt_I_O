package vod.repository.dummy;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import vod.repository.BakeryDao;
import vod.model.Bakery;
import vod.model.Product;


import java.util.List;
import java.util.stream.Collectors;

@Component
//@Primary
public class DummyBakeryDao implements BakeryDao {

    @Override
    public List<Bakery> findAll() {
        return List.of();
    }

    @Override
    public Bakery findById(int id) {
        return null;
    }

    @Override
    public List<Bakery> findByMovie(Product m) {
        return List.of();
    }
}
