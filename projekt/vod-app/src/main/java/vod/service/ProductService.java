package vod.service;

import vod.model.Baker;
import vod.model.Product;

import java.util.List;

public interface ProductService {


    List<Product> getAllProducts();

    List<Product> getProductsByBaker(Baker d);

    Product getProductById(int id);

    Product addProduct(Product m);


    List<Baker> getAllBakers();

    Baker getBakerById(int id);

    Baker addBaker(Baker d);
}
