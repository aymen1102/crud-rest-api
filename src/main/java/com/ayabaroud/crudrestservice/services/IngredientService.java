package com.ayabaroud.crudrestservice.services;

import com.ayabaroud.crudrestservice.exceptions.ElementNotFoundException;
import com.ayabaroud.crudrestservice.model.Ingredient;
import com.ayabaroud.crudrestservice.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IngredientService {

    @Autowired
    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Transactional
    public List<Ingredient> getAll() {
        return ingredientRepository.getAll().stream()
                .collect(Collectors.toList());
    }

    @Transactional
    public Optional<Ingredient> getById(Long id) {
        Optional<Ingredient> ingredient = ingredientRepository.getById(id);
        if (!ingredient.isPresent()) {
            throw new ElementNotFoundException();
        }
        return ingredient;
    }

    @Transactional
    public Optional<Long> create(String name) {
        Ingredient ingredient = new Ingredient(name);
        return ingredientRepository.create(ingredient);
    }

    @Transactional
    public Optional<Ingredient> update(Ingredient ingredient) {
        return ingredientRepository.update(ingredient);
    }

    @Transactional
    public void delete(Ingredient ingredient) {
        ingredientRepository.delete(ingredient);
    }

    @Transactional
    public void deleteById(Long id) {
        ingredientRepository.deleteById(id);
    }

    //public void dropDB(){ ingredientRepository.dropDB();}
}
