package dev.danvega.runnerz.run;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/runs")
// REST Controller that handles HTTP requests for Run resources
class RunController {

    // The repository responsible for data access
    private final JdbcRunRepository runRepository;

    // Constructor injection
    RunController(JdbcRunRepository runRepository) {
        this.runRepository = runRepository;
    }

    // GET /runs
    // Returns all runs
    @GetMapping
    List<Run> findAll() {
        return runRepository.findAll();
    }

    // GET /runs/{id}
    // Returns a single run by its ID
    @GetMapping("/{id}")
    Run findById(@PathVariable Integer id) {
        Optional<Run> run = runRepository.findById(id);

        // If no run is found, return 404 Not Found
        if (run.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Run not found.");
        }
        return run.get();
    }

    // POST /runs
    // Creates a new Run and returns HTTP 201 Created
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void create(@Valid @RequestBody Run run) {
        runRepository.create(run);
    }

    // PUT /runs/{id}
    // Updates an existing Run and returns 204 No Content
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@Valid @RequestBody Run run, @PathVariable Integer id) {
        runRepository.update(run, id);
    }

    // DELETE /runs/{id}
    // Deletes a run and returns 204 No Content
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        runRepository.delete(id);
    }

    // GET /runs?location=INDOOR
    // Filters runs by location
    @GetMapping(params = "location") // Good practice for request param routes
    List<Run> findByLocation(@RequestParam String location) {
        return runRepository.findByLocation(location);
    }
}
