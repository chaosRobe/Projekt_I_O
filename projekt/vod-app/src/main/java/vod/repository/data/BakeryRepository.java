package vod.repository.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vod.model.Bakery;
import vod.model.Product;

import java.util.List;


public interface BakeryRepository  extends JpaRepository<Bakery,Integer> {
    @Query("select b from Bakery b inner join b.products product where product=:product")
    List<Bakery> findAllByProduct(@Param("product")Product product);
}
