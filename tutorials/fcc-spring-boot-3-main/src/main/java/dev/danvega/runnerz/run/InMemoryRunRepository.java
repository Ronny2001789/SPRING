package dev.danvega.runnerz.run;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
// Simple in-memory implementation of RunRepository.
// Stores all Run objects in a List instead of a real database.
class InMemoryRunRepository implements RunRepository {

    private static final Logger log = LoggerFactory.getLogger(InMemoryRunRepository.class);

    // List used as an in-memory "database"
    private final List<Run> runs = new ArrayList<>();

    // Return all stored runs
    public List<Run> findAll() {
        return runs;
    }

    // Return a single run by ID, or throw RunNotFoundException if not found
    public Optional<Run> findById(Integer id) {
        return Optional.ofNullable(
                runs.stream()
                        .filter(run -> run.id() == id)   // match by ID
                        .findFirst()
                        .orElseThrow(RunNotFoundException::new)
        );
    }

    // Create a new Run and store it in memory
    public void create(Run run) {
        // Create a new immutable Run instance (in case Run is a record)
        Run newRun = new Run(
                run.id(),
                run.title(),
                run.startedOn(),
                run.completedOn(),
                run.miles(),
                run.location()
        );

        runs.add(newRun);
    }

    // Update an existing Run by replacing the old object in the list
    public void update(Run newRun, Integer id) {
        Optional<Run> existingRun = findById(id);

        if (existingRun.isPresent()) {
            var oldRun = existingRun.get();
            log.info("Updating Existing Run: " + oldRun);

            // Replace old object with the new updated Run
            runs.set(runs.indexOf(oldRun), newRun);
        }
    }

    // Delete a Run by its ID
    public void delete(Integer id) {
        log.info("Deleting Run: " + id);

        // Remove all runs that match the given ID
        runs.removeIf(run -> run.id().equals(id));
    }

    // Return total number of runs stored in memory
    public int count() {
        return runs.size();
    }

    // Save (create) multiple runs
    public void saveAll(List<Run> runs) {
        runs.forEach(this::create);
    }

    // Find all runs matching a specific location
    public List<Run> findByLocation(String location) {
        return runs.stream()
                .filter(run -> Objects.equals(run.location(), location))
                .toList();
    }

    // Initialize the repository with two sample Run objects after construction
    @PostConstruct
    private void init() {
        runs.add(new Run(
                1,
                "Monday Morning Run",
                LocalDateTime.now(),
                LocalDateTime.now().plus(30, ChronoUnit.MINUTES),
                3,
                Location.INDOOR
        ));

        runs.add(new Run(
                2,
                "Wednesday Evening Run",
                LocalDateTime.now(),
                LocalDateTime.now().plus(60, ChronoUnit.MINUTES),
                6,
                Location.INDOOR
        ));
    }

}

