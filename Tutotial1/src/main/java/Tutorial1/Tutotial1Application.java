package Tutorial1;

import Tutorial1.configurationProperties.AppProperties;
import Tutorial1.configurationProperties.AppPropertiesDemo;
import Tutorial1.controller.MyController;
import Tutorial1.controller.PizzaController;
import Tutorial1.repository.MyRepository;
import Tutorial1.service.MyService;
import Tutorial1.service.VegPizza;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Tutotial1Application {

    public static void main(String[] args) {

        var context = SpringApplication.run(Tutotial1Application.class, args);
        PizzaController pizzacontroller = (PizzaController) context.getBean("pizzaController");
        System.out.println(pizzacontroller.getPizza());

        MyController myController = (MyController) context.getBean("myController");
        System.out.println(myController.hello());

        MyService myService = (MyService) context.getBean("myService");
        System.out.println(myService.hello());

        MyRepository myRepository = (MyRepository) context.getBean("myRepository");
        System.out.println(myRepository.hello());
//
        context.getBean("lazyLoader");

        VegPizza vegPizza = (VegPizza) context.getBean("vegPizza");
        System.out.println(vegPizza.getPizza());

        AppPropertiesDemo appPropertiesDemo = context.getBean(AppPropertiesDemo.class);
        appPropertiesDemo.display();
    }

}
