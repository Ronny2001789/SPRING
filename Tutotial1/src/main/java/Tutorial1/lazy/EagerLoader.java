package Tutorial1.lazy;

import org.springframework.stereotype.Component;

@Component
public class EagerLoader {

    public EagerLoader() {
        System.out.println("EagerLoader is loaded");
    }
}
