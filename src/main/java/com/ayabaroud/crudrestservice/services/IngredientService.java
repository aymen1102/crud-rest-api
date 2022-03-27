package com.ayabaroud.crudrestservice.services;

import com.ayabaroud.crudrestservice.dto.IngredientDto;
import com.ayabaroud.crudrestservice.exceptions.ElementNotFoundException;
import com.ayabaroud.crudrestservice.repository.IngredientRepository;
import com.ayabaroud.crudrestservice.model.Ingredient;
import com.ayabaroud.crudrestservice.repository.hibernate.IngredientHibernateRepository;
import com.ayabaroud.crudrestservice.repository.jdbc.IngredientJdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IngredientService {

    @Autowired
    @Qualifier("ingredientJdbcRepository")
    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Transactional
    public List<IngredientDto> getAll() {
        return ingredientRepository.getAll().stream()
                .map(IngredientDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Optional<IngredientDto> getById(Long id) {
        Optional<Ingredient> ingredient = ingredientRepository.getById(id);
        if (!ingredient.isPresent()) {
            throw new ElementNotFoundException();
        }
        return Optional.ofNullable(new IngredientDto(ingredient.get()));
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
