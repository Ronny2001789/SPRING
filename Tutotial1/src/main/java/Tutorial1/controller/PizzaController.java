package Tutorial1.controller;

import Tutorial1.service.Pizza;
import Tutorial1.service.VegPizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//@Component("pizzaController")
public class PizzaController {

    // Field Injection
//    @Autowired
    private Pizza pizza;

    // Constructor Injection
//    @Autowired
    public PizzaController(Pizza pizza) {
        this.pizza = pizza;
    }


//    // Setter Injection
//    @Autowired
//    public void setVegPizza(Pizza pizza) {
//        this.vegPizza = vegPizza;
//    }

    public String getPizza() {
        return pizza.getPizza();
    }

    private void init() {
        System.out.println("Initializing PizzaController");
    }

    private void destroy() {
        System.out.println("Destroying PizzaController");
    }
}
