package vod.repository;

import vod.model.Baker;

import java.util.List;

public interface BakerDao {

    List<Baker> findAll();

    Baker findById(int id);

    Baker add(Baker d);


}
