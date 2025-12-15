package Tutorial1.service;

import org.springframework.stereotype.Service;

@Service
public class MyService {

    public String hello() {
        return "Hello Service";
    }
}
