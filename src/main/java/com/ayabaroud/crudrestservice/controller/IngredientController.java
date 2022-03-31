package com.ayabaroud.crudrestservice.controller;

import com.ayabaroud.crudrestservice.exceptions.ElementNotFoundException;
import com.ayabaroud.crudrestservice.model.Ingredient;
import com.ayabaroud.crudrestservice.services.IngredientService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@OpenAPIDefinition
@RestController
@RequestMapping("/api/v1/ingredients")
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @CrossOrigin
    @GetMapping("/getAll")
    public List<Ingredient> getAll() {
        return ingredientService.getAll();
    }

    @CrossOrigin
    @GetMapping("/getIngredientById/{id}")
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable("id") final Long id) {
        Optional<Ingredient> ingredient = ingredientService.getById(id);
        if (ingredient.isPresent()) {
            return ResponseEntity.ok(ingredient.get());
        }
        throw new ElementNotFoundException();
    }

    @CrossOrigin
    @PostMapping("/createIngredient")
    public ResponseEntity createIngredient(@RequestBody final String name) {
        Optional<Long> ingredientId = ingredientService.create(name);
        if (ingredientId.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(ingredientId.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
    }

    @CrossOrigin
    @PutMapping("/updateIngredient")
    public ResponseEntity<Ingredient> updateIngredient(@RequestBody Ingredient ingredient) {
        Optional<Ingredient> ingredientOpt = ingredientService.update(ingredient);
        if (ingredientOpt.isPresent()) {
            return ResponseEntity.ok(ingredientOpt.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
    }

    @CrossOrigin
    @DeleteMapping("/deleteIngredient")
    public void deleteIngredient(@RequestBody Ingredient ingredient) {
        ingredientService.delete(ingredient);
    }

    @CrossOrigin
    @DeleteMapping("/deleteIngredientById/{id}")
    public void deleteIngredientById(@PathVariable("id") final Long id) {
        ingredientService.deleteById(id);
    }
}
