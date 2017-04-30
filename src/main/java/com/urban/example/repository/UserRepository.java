package com.urban.example.repository;

import com.urban.example.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by hurban
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>{

    Optional<User> findByUsername(String username);
}
