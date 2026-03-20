package vod.repository.data;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vod.model.Bakery;
import vod.model.Product;
import vod.repository.BakeryDao;

import java.util.List;
@Repository
@Primary
@RequiredArgsConstructor
public class DataBakeryDao implements BakeryDao{

    private final BakeryRepository bakeryRepository;

    @Override
    public List<Bakery> findAll(){return bakeryRepository.findAll();}
    @Override
    public Bakery findById(int id){return bakeryRepository.findById(id).orElse(null);}


    @Override
    public Bakery save(Bakery bakery){return bakeryRepository.save(bakery);}
    @Override
    public List<Bakery> findByProduct(Product product){return bakeryRepository.findAllByProduct(product);}
}
