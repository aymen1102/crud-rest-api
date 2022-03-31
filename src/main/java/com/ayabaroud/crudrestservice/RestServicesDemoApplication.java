package com.ayabaroud.crudrestservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashSet;
import java.util.Set;
import com.ayabaroud.crudrestservice.model.Ingredient;
import com.ayabaroud.crudrestservice.model.Recipe;
import com.ayabaroud.crudrestservice.model.RecipeIngredient;
import com.ayabaroud.crudrestservice.repository.RecipeRepository;
import com.ayabaroud.crudrestservice.services.IngredientService;
import com.ayabaroud.crudrestservice.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestServicesDemoApplication {

	@Autowired
    private RecipeService recipeService;
	
    public static void main(String[] args) {
        SpringApplication.run(RestServicesDemoApplication.class, args);
    }
    
    @Bean
    CommandLineRunner commandLineRunner(
            RecipeRepository recipeRepository) {
        return args -> {
        	Recipe recipe = new Recipe();
			recipe.setName("Pizza");
			recipe.setPicture("Picture of pizza");
			recipe.setDescription("pizza description");
        	Set<RecipeIngredient> recipeIngredients = new HashSet<RecipeIngredient>();

			/**
			 * Add ingredients
			 */
			RecipeIngredient recipeIngredient0 = new RecipeIngredient();
        	Ingredient tomate = new Ingredient();
			tomate.setName("Cheese");
        	recipeIngredient0.setIngredient(tomate);
        	recipeIngredient0.setQuantity(10L);
        	recipeIngredient0.setUnit("KG");
        	recipeIngredients.add(recipeIngredient0);
        	
        	RecipeIngredient recipeIngredient1 = new RecipeIngredient();
        	Ingredient cheese = new Ingredient();
			cheese.setName("Tomate");
        	recipeIngredient1.setIngredient(cheese);
        	recipeIngredient1.setQuantity(23L);
        	recipeIngredient1.setUnit("Unit of measure");
        	recipeIngredients.add(recipeIngredient1);
        	
        	recipe.setIngredients(recipeIngredients);
        	
        	// Ajouter des instructions
        	Set<String> instructions = new HashSet<String>();
        	instructions.add("Intruction 1");
        	instructions.add("Intruction 2");
        	recipe.setInstructions(instructions);
        	recipeService.create(recipe);
        };
    }

	public void setRecipeService(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

}
