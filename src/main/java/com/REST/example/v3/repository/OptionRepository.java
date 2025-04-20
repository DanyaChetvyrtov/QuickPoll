package com.REST.example.v3.repository;

import com.REST.example.model.Option;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("optionRepoV3")
public interface OptionRepository extends CrudRepository<Option, Long> {
}
