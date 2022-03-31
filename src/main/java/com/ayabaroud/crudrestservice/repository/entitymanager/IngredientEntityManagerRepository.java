package com.ayabaroud.crudrestservice.repository.entitymanager;

import com.ayabaroud.crudrestservice.model.Ingredient;
import com.ayabaroud.crudrestservice.repository.IngredientRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class IngredientEntityManagerRepository implements IngredientRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public IngredientEntityManagerRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Ingredient> getAll() {
        Session session = entityManager.unwrap(Session.class);
        Query<Ingredient> query = session.createQuery("from Ingredient", Ingredient.class);
        return query.getResultList();
    }

    public Optional<Ingredient> getById(Long id) {
        Session session = entityManager.unwrap(Session.class);
        return Optional.ofNullable(session.get(Ingredient.class, id));
    }

    public Optional<Long> create(Ingredient ingredient) {
        if (ingredient.getId() == null) {
            Session session = entityManager.unwrap(Session.class);
            return Optional.of((long) session.save(ingredient));
        }
        return Optional.empty();
    }

    public Optional<Ingredient> update(Ingredient ingredient) {
        Session session = entityManager.unwrap(Session.class);
        return Optional.of((Ingredient) session.merge(ingredient));
    }

    public void delete(Ingredient ingredient) {
        Session session = entityManager.unwrap(Session.class);
        Ingredient ingredientInDB = session.get(Ingredient.class, ingredient.getId());
        session.remove(ingredientInDB);
    }

    public void deleteById(Long id) {
        Session session = entityManager.unwrap(Session.class);
        Ingredient ingredientInDB = session.get(Ingredient.class, id);
        session.remove(ingredientInDB);
    }
}
