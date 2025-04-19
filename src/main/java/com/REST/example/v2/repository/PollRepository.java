package com.REST.example.v2.repository;

import com.REST.example.model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository("pollRepoV2")
public interface PollRepository extends JpaRepository<Poll, Long> {
}
