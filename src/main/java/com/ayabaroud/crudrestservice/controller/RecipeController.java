package com.ayabaroud.crudrestservice.controller;

import com.ayabaroud.crudrestservice.exceptions.ElementNotFoundException;
import com.ayabaroud.crudrestservice.model.Recipe;
import com.ayabaroud.crudrestservice.model.RecipeIngredient;
import com.ayabaroud.crudrestservice.services.RecipeService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@OpenAPIDefinition
@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/getAll")
    public List<Recipe> getAll() {
        return recipeService.getAll();
    }

    @GetMapping(value = "/getRecipeById/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable("id") final Long id) {
        Optional<Recipe> recipe = recipeService.getById(id);
        if (recipe.isPresent()) {
            return ResponseEntity.ok(recipe.get());
        }
        throw new ElementNotFoundException();
    }

    @PostMapping("/createRecipe")
    public ResponseEntity createRecipe(@RequestBody Recipe recipe) {
        Optional<Long> recipeId = recipeService.create(recipe);
        if (recipeId.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(recipeId.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
    }

    @PatchMapping(value = "/ingredients")
    public void addIngredients(@RequestParam("id") final Long id, @RequestBody List<RecipeIngredient> ingredients) {
        recipeService.addIngredients(id, ingredients);
    }

    @PutMapping("/updateRecipe")
    public void updateRecipe(@RequestBody Recipe recipe) {
        recipeService.update(recipe);
    }

    @DeleteMapping("/deleteRecipe")
    public void deleteRecipe(@RequestBody Recipe recipe) {
        recipeService.delete(recipe);
    }

    @DeleteMapping(value = "deleteRecipeById/{id}")
    public void deleteRecipeById(@PathVariable("id") final Long id) {
        recipeService.deleteById(id);
    }
}
