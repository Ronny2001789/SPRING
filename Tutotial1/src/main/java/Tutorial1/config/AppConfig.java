package Tutorial1.config;

import Tutorial1.controller.PizzaController;
import Tutorial1.service.NonVegPizza;
import Tutorial1.service.Pizza;
import Tutorial1.service.VegPizza;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
@Lazy
public class AppConfig {

    @Bean(name = "vegPizza") // Naming optional as the bean name will be the same as the method name.
    @Lazy // You can also use @Lazy to load the bean individually
    public Pizza vegPizza() {
        return new VegPizza();
    }

    @Bean
    public Pizza nonVegPizza() {
        return new NonVegPizza();
    }

    @Bean(initMethod = "init", destroyMethod = "destroy") // Methods to call when the bean is created and destroyed.
    public PizzaController pizzaController() {
        return new PizzaController(vegPizza());// Change between veg and non veg pizza
    }
}
