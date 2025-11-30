package dev.danvega.runnerz.run;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.time.Duration;
import java.time.LocalDateTime;

// Immutable record representing a Run activity.
// Includes validation, duration calculation, and pace calculation.
public record Run(
        Integer id,

        // Title must not be empty (Bean Validation)
        @NotEmpty
        String title,

        // Time when the run started
        LocalDateTime startedOn,

        // Time when the run finished
        LocalDateTime completedOn,

        // Miles must be a positive number
        @Positive
        Integer miles,

        // Location enum (INDOOR or OUTDOOR)
        Location location
) {

    // Compact constructor used for validation logic.
    // Runs automatically whenever a Run object is created.
    public Run {
        // Validate that the run's end time is after the start time
        if (!completedOn.isAfter(startedOn)) {
            throw new IllegalArgumentException(
                    "Completed On must be after Started On"
            );
        }
    }

    // Calculate the duration of the run
    public Duration getDuration() {
        return Duration.between(startedOn, completedOn);
    }

    // Calculate average pace (minutes per mile)
    public Integer getAvgPace() {
        return Math.toIntExact(getDuration().toMinutes() / miles);
    }
}

