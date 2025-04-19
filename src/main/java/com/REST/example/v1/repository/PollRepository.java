package com.REST.example.v1.repository;

import com.REST.example.model.Poll;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("pollRepoV1")
public interface PollRepository extends CrudRepository<Poll, Long> {
}
