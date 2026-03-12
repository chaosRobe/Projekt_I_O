package vod.service;

import vod.model.Bakery;
import vod.model.Product;

import java.util.List;

public interface BakeryService {
//api zwraca nam wszystkie kina
    Bakery getBakeryById(int id);

    List<Bakery> getAllBakeries();

    List<Bakery> getBakeriesByProduct(Product m);

    List<Product> getProductsInBakery(Bakery c);

    Bakery addBakery(Bakery b);
}
