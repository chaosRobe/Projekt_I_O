package vod.repository.data;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import vod.model.Baker;
import vod.repository.BakerDao;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Primary
public class DataBakerDao implements BakerDao {
    private final BakerRepository bakerRepository;

    @Override
    public List<Baker> findAll(){return bakerRepository.findAll();}

    @Override
    public Baker findById(int id){return bakerRepository.findById(id).orElse(null);}

    @Override
    public Baker add(Baker baker) {return bakerRepository.save(baker);}
}
