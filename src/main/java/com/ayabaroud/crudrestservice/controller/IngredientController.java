package com.ayabaroud.crudrestservice.controller;

import com.ayabaroud.crudrestservice.dto.IngredientDto;
import com.ayabaroud.crudrestservice.exceptions.ElementNotFoundException;
import com.ayabaroud.crudrestservice.model.Ingredient;
import com.ayabaroud.crudrestservice.services.IngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/ingredients")
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @CrossOrigin
    @GetMapping
    public List<IngredientDto> getAll() {
        return ingredientService.getAll();
    }

    @CrossOrigin
    @GetMapping(value = "/{id}")
    public ResponseEntity<IngredientDto> getById(@PathVariable("id") final Long id) {
        Optional<IngredientDto> ingredientDto = ingredientService.getById(id);

        if (ingredientDto.isPresent()) {
            return ResponseEntity.ok(ingredientDto.get());
        }

        throw new ElementNotFoundException();
    }

    /**
     * @param name : Name of the ingredient (Not unique).
     * @return The id of the ingredient created
     */
    @CrossOrigin
    @PostMapping
    public ResponseEntity create(@RequestBody final String name) {
        Optional<Long> ingredientId = ingredientService.create(name);

        if (ingredientId.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(ingredientId.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
    }

    @CrossOrigin
    @PutMapping
    public ResponseEntity<Ingredient> update(@RequestBody IngredientDto ingredientDto) {
        Optional<Ingredient> ingredient = ingredientService.update(ingredientDto);

        if (ingredient.isPresent()) {
            return ResponseEntity.ok(ingredient.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
    }

    @CrossOrigin
    @DeleteMapping
    public void delete(@RequestBody IngredientDto ingredientDto) {
        ingredientService.delete(ingredientDto);
    }

    @CrossOrigin
    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable("id") final Long id) {
        ingredientService.deleteById(id);
    }
}
