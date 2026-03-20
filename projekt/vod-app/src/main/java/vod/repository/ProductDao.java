package vod.repository;

import vod.model.Bakery;
import vod.model.Baker;
import vod.model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> findAll();

    Product findById(int id);

    List<Product> findByBaker(Baker d);

    List<Product> findByBakery(Bakery c);

    Product add(Product m);

}
