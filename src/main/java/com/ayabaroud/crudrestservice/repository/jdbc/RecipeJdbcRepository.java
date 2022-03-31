package com.ayabaroud.crudrestservice.repository.jdbc;

import com.ayabaroud.crudrestservice.model.Recipe;
import com.ayabaroud.crudrestservice.repository.RecipeRepository;

import java.util.List;
import java.util.Optional;

public class RecipeJdbcRepository implements RecipeRepository {
    @Override
    public List<Recipe> getAll() {
        return null;
    }

    @Override
    public Optional<Recipe> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Long> create(Recipe recipe) {
        return Optional.empty();
    }

    @Override
    public void update(Recipe recipe) {

    }

    @Override
    public void delete(Recipe recipe) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
