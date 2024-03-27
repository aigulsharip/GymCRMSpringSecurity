package com.example.gymcrmspringsecurity.repository;


import com.example.gymcrmspringsecurity.entity.trainer.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    boolean existsByUsername(String username);

    boolean existsByFirstNameAndLastName(String firstName, String lastName);

    Optional<Trainer> findByUsernameAndPassword(String username, String password);

    Optional<Trainer> findByUsername(String username);

    List<Trainer> findByUsernameIn(List<String> trainerUsernames);

}
