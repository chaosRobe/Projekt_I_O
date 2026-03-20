package vod.repository.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import vod.model.Bakery;
import vod.model.Product;
import vod.repository.BakeryDao;

import java.util.List;

@Repository

public class JpaBakeryDao implements BakeryDao {
    @PersistenceContext
    private EntityManager em;
    @Override
    public List<Bakery> findAll() {
        return em.createQuery("select b from Bakery b").getResultList();
    }

    @Override
    public Bakery findById(int id) {return em.find(Bakery.class, id);}

    @Override
    public List<Bakery> findByProduct(Product p){
        return em.createQuery("select b from Bakery b inner join b.products product where product=:product").setParameter("product", p).getResultList();
    }

    @Override
    public  Bakery save(Bakery bakery){
        em.persist(bakery);
        return bakery;
    }
}
