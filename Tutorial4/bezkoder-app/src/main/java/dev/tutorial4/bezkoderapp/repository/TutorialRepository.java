package dev.tutorial4.bezkoderapp.repository;

import dev.tutorial4.bezkoderapp.model.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
    //Methods that we get:
    //findAll(), findById(), save(), delete()
}
