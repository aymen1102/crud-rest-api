package com.ayabaroud.crudrestservice.repository;

import com.ayabaroud.crudrestservice.model.Recipe;
import java.util.List;
import java.util.Optional;

public interface RecipeRepository {

     List<Recipe> getAll();

     Optional<Recipe> getById(Long id);

     Optional<Long> create(Recipe recipe);

     void update(Recipe recipe);

     void delete(Recipe recipe);

     void deleteById(Long id);

}
