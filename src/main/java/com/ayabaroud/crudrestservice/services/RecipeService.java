package com.ayabaroud.crudrestservice.services;

import com.ayabaroud.crudrestservice.dto.RecipeDto;
import com.ayabaroud.crudrestservice.dto.RecipeIngredientDto;
import com.ayabaroud.crudrestservice.exceptions.ElementNotFoundException;
import com.ayabaroud.crudrestservice.repository.IngredientRepository;
import com.ayabaroud.crudrestservice.repository.entitymanager.RecipeEntityManagerRepository;
import com.ayabaroud.crudrestservice.model.Ingredient;
import com.ayabaroud.crudrestservice.model.Recipe;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    private final RecipeEntityManagerRepository recipeEntityManagerRepository;
    private final IngredientRepository ingredientRepository;

    public RecipeService(RecipeEntityManagerRepository recipeEntityManagerRepository, IngredientRepository ingredientRepository) {
        this.recipeEntityManagerRepository = recipeEntityManagerRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Transactional
    public List<RecipeDto> getAll() {
        return recipeEntityManagerRepository.getAll().stream().map(RecipeDto::new).collect(Collectors.toList());
    }

    @Transactional
    public Optional<RecipeDto> getById(Long id) {
        Optional<Recipe> recipe = recipeEntityManagerRepository.getById(id);
        if (!recipe.isPresent()) {
            throw new ElementNotFoundException();
        }
        setupIngredients(recipe.get());
        return Optional.of(new RecipeDto(recipe.get()));
    }

    @Transactional
    public Optional<Long> create(RecipeDto recipeDto) {
        Recipe recipe = recipeDto.toRecipe();
        setupIngredients(recipe);
        return recipeEntityManagerRepository.create(recipe);
    }

    @Transactional
    public void addIngredients(Long id, List<RecipeIngredientDto> ingredients) {
        Optional<RecipeDto> recipeDto = getById(id);
        if (recipeDto.isPresent()) {
            recipeDto.get().getIngredients().addAll(ingredients);
            Recipe recipe = recipeDto.get().toRecipe();
            setupIngredients(recipe);
            recipeEntityManagerRepository.update(recipe);
        }
    }

    @Transactional
    public void update(RecipeDto recipeDto) {
        Optional<RecipeDto> recipeDtoOpt = getById(recipeDto.getId());
        recipeEntityManagerRepository.update(recipeDto.toRecipe());
    }

    private void setupIngredients(Recipe recipe) {
        recipe.getIngredients().forEach(recipeIngredient -> {
            ingredientRepository.create(recipeIngredient.getIngredient());
            Optional<Ingredient> ingredient = ingredientRepository.getById(recipeIngredient.getIngredient().getId());
            ingredient.ifPresent(recipeIngredient::setIngredient);
        });
    }

    @Transactional
    public void delete(RecipeDto recipeDto) {
        recipeEntityManagerRepository.delete(recipeDto.toRecipe());
    }

    @Transactional
    public void deleteById(Long id) {
        recipeEntityManagerRepository.deleteById(id);
    }
}
