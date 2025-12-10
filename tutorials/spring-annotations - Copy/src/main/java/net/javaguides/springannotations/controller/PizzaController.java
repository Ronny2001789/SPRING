package net.javaguides.springannotations.controller;

import org.springframework.stereotype.Component;

@Component
public class PizzaController {

    public String getPizza() {
        return "Hot Pizza!";
    }
}