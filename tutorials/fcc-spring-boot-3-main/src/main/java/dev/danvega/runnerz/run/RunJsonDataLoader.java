package dev.danvega.runnerz.run;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
// Loads initial Run data from a JSON file when the application starts.
// This class runs automatically because it implements CommandLineRunner.
public class RunJsonDataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(RunJsonDataLoader.class);

    // ObjectMapper is used to convert JSON into Java objects
    private final ObjectMapper objectMapper;

    // Repository where run data will be stored
    private final RunRepository runRepository;

    // Constructor injection, using a specific bean name with @Qualifier
    public RunJsonDataLoader(ObjectMapper objectMapper,
                             @Qualifier("jdbcRunRepository") RunRepository runRepository) {
        this.objectMapper = objectMapper;
        this.runRepository = runRepository;
    }

    // This method runs automatically at application startup
    @Override
    public void run(String... args) throws Exception {

        // Only load JSON data if repository is empty
        if (runRepository.count() == 0) {
            try (InputStream inputStream =
                         TypeReference.class.getResourceAsStream("/data/runs.json")) {

                // Convert the JSON file into a Runs wrapper object
                Runs allRuns = objectMapper.readValue(inputStream, Runs.class);

                log.info("Reading {} runs from JSON data and saving to repository.",
                        allRuns.runs().size());

                // Save all Runs into the repository
                runRepository.saveAll(allRuns.runs());

            } catch (IOException e) {
                // Wrap and rethrow exception if the file cannot be read
                throw new RuntimeException("Failed to read JSON data", e);
            }

        } else {
            // Do not re-load JSON if data already exists
            log.info("Not loading Runs from JSON because the repository already contains data.");
        }
    }
}
