package dev.danvega.runnerz.run;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
// Repository class that uses JdbcClient to interact with the "run" table
public class JdbcRunRepository implements RunRepository {

    private final JdbcClient jdbcClient;

    // Constructor injection for JdbcClient
    public JdbcRunRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    // Find and return all Run records from the database
    public List<Run> findAll() {
        return jdbcClient.sql("select * from run")
                .query(Run.class)
                .list();
    }

    // Find a single Run by its ID and return Optional<Run>
    public Optional<Run> findById(Integer id) {
        return jdbcClient.sql("SELECT id,title,started_on,completed_on,miles,location FROM Run WHERE id = :id")
                .param("id", id)
                .query(Run.class)
                .optional();
    }

    // Create a new Run record in the database
    public void create(Run run) {
        var updated = jdbcClient.sql(
                        "INSERT INTO Run(id,title,started_on,completed_on,miles,location) VALUES(?,?,?,?,?,?)")
                .params(List.of(
                        run.id(),
                        run.title(),
                        run.startedOn(),
                        run.completedOn(),
                        run.miles(),
                        run.location().toString()
                ))
                .update();

        // Assert ensures exactly one row was inserted
        Assert.state(updated == 1, "Failed to create run " + run.title());
    }

    // Update an existing Run by ID
    public void update(Run run, Integer id) {
        var updated = jdbcClient.sql(
                        "UPDATE run SET title = ?, started_on = ?, completed_on = ?, miles = ?, location = ? WHERE id = ?")
                .params(List.of(
                        run.title(),
                        run.startedOn(),
                        run.completedOn(),
                        run.miles(),
                        run.location().toString(),
                        id
                ))
                .update();

        // Ensures update was successful
        Assert.state(updated == 1, "Failed to update run " + run.title());
    }

    // Delete a Run by ID
    public void delete(Integer id) {
        var updated = jdbcClient.sql("DELETE FROM run WHERE id = :id")
                .param("id", id)
                .update();

        // Ensures deletion was successful
        Assert.state(updated == 1, "Failed to delete run " + id);
    }

    // Count all records in the "run" table
    public int count() {
        return jdbcClient.sql("select * from run")
                .query()
                .listOfRows()
                .size();
    }

    // Save multiple runs by calling create() for each one
    public void saveAll(List<Run> runs) {
        runs.stream().forEach(this::create);
    }

    // Find all runs by location value
    public List<Run> findByLocation(String location) {
        return jdbcClient.sql("select * from run where location = :location")
                .param("location", location)
                .query(Run.class)
                .list();
    }

}
