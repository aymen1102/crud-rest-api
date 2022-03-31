package com.ayabaroud.crudrestservice.repository;

import com.ayabaroud.crudrestservice.model.Ingredient;
import java.util.List;
import java.util.Optional;

public interface IngredientRepository {

     List<Ingredient> getAll();

     Optional<Ingredient> getById(Long id);

     Optional<Long> create(Ingredient ingredient);

     Optional<Ingredient> update(Ingredient ingredient);

     void delete(Ingredient ingredient);

     void deleteById(Long id);
}
