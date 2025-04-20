package com.REST.example.v3.repository;

import com.REST.example.model.MyUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepositoryV3")
public interface UserRepository extends CrudRepository<MyUser, Long> {
    MyUser findByUsername(String username);
}
