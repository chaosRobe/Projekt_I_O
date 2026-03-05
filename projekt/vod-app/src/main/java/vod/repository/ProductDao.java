package vod.repository;

import vod.model.Bakery;
import vod.model.Baker;
import vod.model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> findAll();

    Product findById(int id);

    List<Product> findByDirector(Baker d);

    List<Product> findByCinema(Bakery c);

    Product add(Product m);

}
