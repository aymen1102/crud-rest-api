package com.ayabaroud.crudrestservice;

import java.util.HashSet;
import java.util.Set;

import com.ayabaroud.crudrestservice.dto.IngredientDto;
import com.ayabaroud.crudrestservice.dto.RecipeDto;
import com.ayabaroud.crudrestservice.dto.RecipeIngredientDto;
import com.ayabaroud.crudrestservice.repository.entitymanager.RecipeRepository;
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
	private IngredientService ingredientService;
	@Autowired
    private RecipeService recipeService;
	
    public static void main(String[] args) {
        SpringApplication.run(RestServicesDemoApplication.class, args);
    }
    
    @Bean
    CommandLineRunner commandLineRunner(
            RecipeRepository recipeRepository) {
        return args -> {
        	RecipeDto recipeDto = new RecipeDto();

        	recipeDto.setName("Pizza");
        	recipeDto.setPicture("Picture of pizza");
        	recipeDto.setDescription("pizza description");
        	
        	Set<RecipeIngredientDto> recipeIngredients = new HashSet<RecipeIngredientDto>();

			/**
			 * Add ingredients
			 */
			RecipeIngredientDto recipeIngredient0 = new RecipeIngredientDto();
        	IngredientDto tomate = new IngredientDto();
        	tomate.setName("Tomate");
        	recipeIngredient0.setIngredient(tomate);
        	recipeIngredient0.setQuantity(10L);
        	recipeIngredient0.setUnit("KG");
        	recipeIngredients.add(recipeIngredient0);
        	//ingredientService.create(tomate.getName());
        	
        	RecipeIngredientDto recipeIngredient1 = new RecipeIngredientDto();
        	IngredientDto Cheese = new IngredientDto();
			Cheese.setName("Cheese");
        	recipeIngredient1.setIngredient(Cheese);
        	recipeIngredient1.setQuantity(23L);
        	recipeIngredient1.setUnit("Unit of measure");
        	recipeIngredients.add(recipeIngredient1);
        	
        	recipeDto.setIngredients(recipeIngredients);
        	//ingredientService.create(aubergine.getName());
        	
        	//ajouter des instructions
        	Set<String> instructions = new HashSet<String>();
        	instructions.add("Intruction 1");
        	instructions.add("Intruction 2");
        	recipeDto.setInstructions(instructions);
        	
        	recipeService.create(recipeDto);
        };
    }
    
	public void setIngredientService(IngredientService ingredientService) {
		this.ingredientService = ingredientService;
	}

	public void setRecipeService(RecipeService recipeService) {
		this.recipeService = recipeService;
	}
}
