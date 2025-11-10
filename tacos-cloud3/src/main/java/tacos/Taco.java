package tacos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import  jakarta.validation.constraints.NotNull;
import  jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class Taco implements Serializable {

    private static final long serialVersionUID = 1L;

    // Represents an entity (for example, a Taco) with basic properties and validation rules.
    private Long id;  // Unique identifier for the entity

    // Automatically set to the current date/time when the object is created
    private Date createdAt = new Date();

    // The name of the entity; must not be null and must have at least 5 characters
    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;

    // A list of ingredient references that make up this entity (e.g., the ingredients in a taco)
    // Must contain at least one ingredient
    @Size(min = 1, message = "You must choose at least 1 ingredient")
    private List<IngredientRef> ingredients = new ArrayList<>();
    public void addIngredient(Ingredient taco) {
        this.ingredients.add(new IngredientRef(taco.getId()));
    }
}
