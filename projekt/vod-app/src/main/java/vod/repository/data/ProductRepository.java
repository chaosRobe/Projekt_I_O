package vod.repository.data;

import org.springframework.data.jpa.repository.JpaRepository;
import vod.model.Baker;
import vod.model.Bakery;
import vod.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findByBaker(Baker b);

    List<Product> findAllByBakeriesContaining(Bakery b);
}
