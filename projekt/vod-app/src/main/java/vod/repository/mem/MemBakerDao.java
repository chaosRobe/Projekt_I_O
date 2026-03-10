package vod.repository.mem;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import vod.repository.BakerDao;
import vod.model.Baker;

import java.util.List;

@Repository
public class MemBakerDao implements BakerDao {
    @Override
    public List<Baker> findAll() {
        return SampleData.bakers;
    }

    @Override
    public Baker findById(int id) {
        return SampleData.bakers.stream().filter(d -> d.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Baker add(Baker d) {
        int max = SampleData.bakers.stream().max((d1, d2) -> d1.getId() - d2.getId()).get().getId();
        d.setId(++max);
        SampleData.bakers.add(d);
        return d;
    }
}
