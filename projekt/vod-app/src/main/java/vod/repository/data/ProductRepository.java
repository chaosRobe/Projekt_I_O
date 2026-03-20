package vod.repository.data;

import org.springframework.data.jpa.repository.JpaRepository;
import vod.model.Product;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
