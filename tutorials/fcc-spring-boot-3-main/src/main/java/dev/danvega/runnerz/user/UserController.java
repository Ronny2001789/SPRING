package dev.danvega.runnerz.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
class UserController {

    // This is the HTTP client used to call another service (e.g., another API)
    private final UserHttpClient client;

    // Constructor injection: Spring injects UserHttpClient automatically
    UserController(UserHttpClient client) {
        this.client = client;
    }

    // GET /users  → returns a list of all users
    @GetMapping("")
    List<User> findAll() {
        return client.findAll(); // Delegates the work to UserHttpClient
    }

    // GET /users/{id}  → returns a single user by their ID
    @GetMapping("/{id}")
    User findById(@PathVariable Integer id) {
        return client.findById(id); // Calls the external service to find one user
    }

}

