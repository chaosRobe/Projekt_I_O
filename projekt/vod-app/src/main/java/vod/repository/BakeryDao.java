package vod.repository;

import vod.model.Bakery;
import vod.model.Product;

import java.util.List;

public interface BakeryDao {

    List<Bakery> findAll();

    Bakery findById(int id);

    

    Bakery save(Bakery bakery);

    List<Bakery> findByProduct(Product p);
}
