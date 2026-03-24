package vod.repository.jpa;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import vod.model.Baker;
import vod.model.Bakery;
import vod.model.Product;
import vod.repository.ProductDao;

import java.util.List;

@Repository
public class JpaProductDao implements ProductDao {


    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Product> findAll(){
        return em.createQuery("select p from Product p").getResultList();
    }
    @Override
    public Product findById(int id){
        return em.find(Product.class,id);
    }
    @Override
    public List<Product> findByBaker(Baker b){
        return em.createQuery("select p from Product p where p.baker=:b").setParameter("b",b).getResultList();
    }
    @Override
    public List<Product> findByBakery(Bakery c){
        return em.createQuery("select p from Product p inner join p.bakeries bakery where bakery=:b").setParameter("b",c).getResultList();
    }
    @Override
    public Product add(Product m){
        em.persist(m);
        return m;
    }

}
