package com.ayabaroud.crudrestservice.controller;

import com.ayabaroud.crudrestservice.dto.RecipeDto;
import com.ayabaroud.crudrestservice.dto.RecipeIngredientDto;
import com.ayabaroud.crudrestservice.exceptions.ElementNotFoundException;
import com.ayabaroud.crudrestservice.services.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @CrossOrigin
    @GetMapping
    public List<RecipeDto> getAll() {
        return recipeService.getAll();
    }

    @CrossOrigin
    @GetMapping(value = "/{id}")
    public ResponseEntity<RecipeDto> getById(@PathVariable("id") final Long id) {
        Optional<RecipeDto> recipeDto = recipeService.getById(id);

        if (recipeDto.isPresent()) {
            return ResponseEntity.ok(recipeDto.get());
        }

        throw new ElementNotFoundException();
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity create(@RequestBody RecipeDto recipe) {
        Optional<Long> recipeId = recipeService.create(recipe);

        if (recipeId.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(recipeId.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
    }

    @CrossOrigin
    @PatchMapping(value = "/ingredients")
    public void addIngredients(@RequestParam("id") final Long id, @RequestBody List<RecipeIngredientDto> ingredients) {
        recipeService.addIngredients(id, ingredients);
    }

    @CrossOrigin
    @PutMapping
    public void update(@RequestBody RecipeDto recipe) {
        recipeService.update(recipe);
    }

    @CrossOrigin
    @DeleteMapping
    public void delete(@RequestBody RecipeDto recipeDto) {
        recipeService.delete(recipeDto);
    }

    @CrossOrigin
    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable("id") final Long id) {
        recipeService.deleteById(id);
    }
}
