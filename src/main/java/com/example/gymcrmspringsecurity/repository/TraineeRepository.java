package com.example.gymcrmspringsecurity.repository;

import com.example.gymcrmspringsecurity.entity.trainee.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TraineeRepository extends JpaRepository<Trainee, Long> {
    boolean existsByUsername(String username);
    boolean existsByFirstNameAndLastName(String firstName, String lastName);
    Optional<Trainee> findByUsernameAndPassword(String username, String password);
    Optional<Trainee> findByUsername(String username);

}
