package com.REST.example.v2.repository;

import com.REST.example.model.Vote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("voteRepoV2")
public interface VoteRepository extends CrudRepository<Vote, Long> {
    @Query(
            value = "SELECT v.* FROM vote v JOIN option o ON v.option_id = o.option_id WHERE poll_id = ?",
            nativeQuery = true
    )
    Iterable<Vote> findAllByPollId(Long pollId);
}
