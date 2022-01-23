package com.ayabaroud.crudrestservice.repository;

import com.ayabaroud.crudrestservice.model.Ingredient;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class IngredientRepository {

    private static final String QUERY_FIND_ALL = "from Ingredient";

    @PersistenceContext
    private final EntityManager entityManager;

    public IngredientRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Ingredient> getAll() {
        Session session = entityManager.unwrap(Session.class);
        Query<Ingredient> query = session.createQuery(QUERY_FIND_ALL, Ingredient.class);
        return query.getResultList();
    }

    public Optional<Ingredient> getById(Long id) {
        Session session = entityManager.unwrap(Session.class);
        return Optional.of(session.get(Ingredient.class, id));
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
