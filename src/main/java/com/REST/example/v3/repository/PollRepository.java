package com.REST.example.v3.repository;

import com.REST.example.model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("pollRepoV3")
public interface PollRepository extends JpaRepository<Poll, Long> {
}
