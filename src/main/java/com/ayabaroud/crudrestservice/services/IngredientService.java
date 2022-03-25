package com.ayabaroud.crudrestservice.services;

import com.ayabaroud.crudrestservice.dto.IngredientDto;
import com.ayabaroud.crudrestservice.exceptions.ElementNotFoundException;
import com.ayabaroud.crudrestservice.repository.IngredientRepository;
import com.ayabaroud.crudrestservice.model.Ingredient;
import com.ayabaroud.crudrestservice.repository.hibernate.IngredientHibernateRepository;
import com.ayabaroud.crudrestservice.repository.jdbc.IngredientJdbcRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;
    private final IngredientJdbcRepository ingredientJdbcRepository;
    private final IngredientHibernateRepository ingredientHibernateRepository;

    public IngredientService(IngredientRepository ingredientRepository,
                             IngredientJdbcRepository ingredientJdbcRepository,
                             IngredientHibernateRepository ingredientHibernateRepository) {
        this.ingredientRepository = ingredientRepository;
        this.ingredientJdbcRepository = ingredientJdbcRepository;
        this.ingredientHibernateRepository = ingredientHibernateRepository;
    }

    @Transactional
    public List<IngredientDto> getAll() {
        return ingredientHibernateRepository.getAll().stream()
                .map(IngredientDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Optional<IngredientDto> getById(Long id) {
        Optional<Ingredient> ingredient = ingredientRepository.getById(id);
        if (!ingredient.isPresent()) {
            throw new ElementNotFoundException();
        }
        return Optional.of(new IngredientDto(ingredient.get()));
    }

    @Transactional
    public Optional<Long> create(String name) {
        Ingredient ingredient = new Ingredient(name);
        return ingredientRepository.create(ingredient);
    }

    @Transactional
    public Optional<Ingredient> update(IngredientDto ingredientDto) {
        return ingredientRepository.update(ingredientDto.toIngredient());
    }

    @Transactional
    public void delete(IngredientDto ingredientDto) {
        ingredientRepository.delete(ingredientDto.toIngredient());
    }

    @Transactional
    public void deleteById(Long id) {
        ingredientRepository.deleteById(id);
    }
}
