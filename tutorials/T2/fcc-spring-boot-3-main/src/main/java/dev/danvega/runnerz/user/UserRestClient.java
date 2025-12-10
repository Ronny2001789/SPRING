package dev.danvega.runnerz.user;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
class UserRestClient {

    // RestClient is Spring's new HTTP client used to call external REST APIs.
    private final RestClient restClient;

    // Constructor receives a RestClient.Builder from Spring
    // and configures a base URL for all requests.
    public UserRestClient(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("https://jsonplaceholder.typicode.com/") // Base API URL
                .build(); // Build the configured client
    }

    // Fetch all users (GET /users)
    List<User> findAll() {
        return restClient.get()
                .uri("/users") // Endpoint relative to the base URL
                .retrieve()    // Execute the request
                // Parse the JSON array into List<User> (requires ParameterizedTypeReference)
                .body(new ParameterizedTypeReference<>() {});
    }

    // Fetch a single user by ID (GET /users/{id})
    User findById(Integer id) {
        return restClient.get()
                .uri("/users/{id}", id) // Path variable binding
                .retrieve()             // Execute request
                .body(User.class);      // Convert JSON into a User object
    }
}
