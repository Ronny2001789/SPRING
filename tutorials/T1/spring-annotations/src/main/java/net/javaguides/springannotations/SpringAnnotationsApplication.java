package net.javaguides.springannotations;

import net.javaguides.springannotations.controller.PizzaController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringAnnotationsApplication {
    public static void main(String[] args) {
      var context = SpringApplication.run(SpringAnnotationsApplication.class, args);
        PizzaController pizzaController = context.getBean(PizzaController.class);
        System.out.println(pizzaController.getPizza());
    }
}