package com.ayabaroud.crudrestservice.services;

import com.ayabaroud.crudrestservice.exceptions.ElementNotFoundException;
import com.ayabaroud.crudrestservice.model.Ingredient;
import com.ayabaroud.crudrestservice.model.Recipe;
import com.ayabaroud.crudrestservice.model.RecipeIngredient;
import com.ayabaroud.crudrestservice.repository.IngredientRepository;
import com.ayabaroud.crudrestservice.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    @Autowired
    private final IngredientRepository ingredientRepository;
    @Autowired
    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository, IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
    }

    @Transactional
    public List<Recipe> getAll() {
        return recipeRepository.getAll().stream()
                .collect(Collectors.toList());
    }

    @Transactional
    public Optional<Recipe> getById(Long id) {
        Optional<Recipe> recipe = recipeRepository.getById(id);
        if (!recipe.isPresent()) {
            throw new ElementNotFoundException();
        }
        setupIngredients(recipe.get());
        return Optional.of(recipe.get());
    }

    @Transactional
    public Optional<Long> create(Recipe recipe) {
        setupIngredients(recipe);
        return recipeRepository.create(recipe);
    }

    @Transactional
    public void addIngredients(Long id, List<RecipeIngredient> ingredients) {
        Optional<Recipe> recipe = getById(id);
        if (recipe.isPresent()) {
            recipe.get().getIngredients().addAll(ingredients);
            setupIngredients(recipe.get());
            recipeRepository.update(recipe.get());
        }
    }

    @Transactional
    public void update(Recipe recipe) {
        recipeRepository.update(recipe);
    }

    private void setupIngredients(Recipe recipe) {
        recipe.getIngredients().forEach(recipeIngredient -> {
            Optional<Long> ingredientID = ingredientRepository.create(recipeIngredient.getIngredient());
            Optional<Ingredient> ingredient = ingredientRepository.getById(ingredientID.get());
            ingredient.ifPresent(recipeIngredient::setIngredient);
        });
    }

    @Transactional
    public void delete(Recipe recipe) {
        recipeRepository.delete(recipe);
    }

    @Transactional
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }
}
