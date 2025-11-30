package dev.danvega.runnerz.run;

import java.util.List;

// Wrapper record used to hold a list of Run objects.
// Helpful when reading JSON structured like:
// { "runs": [ ... ] }
public record Runs(List<Run> runs) {
}

