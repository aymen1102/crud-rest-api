package com.ayabaroud.crudrestservice.repository.entitymanager;

import com.ayabaroud.crudrestservice.model.Recipe;
import com.ayabaroud.crudrestservice.repository.RecipeRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class RecipeEntityManagerRepository implements RecipeRepository {

    private static final String QUERY_FIND_ALL = "from Recipe";

    @PersistenceContext
    private final EntityManager entityManager;

    public RecipeEntityManagerRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Recipe> getAll() {
        Session session = entityManager.unwrap(Session.class);
        Query<Recipe> query = session.createQuery(QUERY_FIND_ALL, Recipe.class);
        return query.getResultList();
    }

    public Optional<Recipe> getById(Long id) {
        Session session = entityManager.unwrap(Session.class);
        return Optional.of(session.get(Recipe.class, id));
    }

    public Optional<Long> create(Recipe recipe) {
        Session session = entityManager.unwrap(Session.class);
        return Optional.of((long) session.save(recipe));
    }

    public void update(Recipe recipe) {
        Session session = entityManager.unwrap(Session.class);
        session.get(Recipe.class, recipe.getId());
        session.merge(recipe);
    }

    public void delete(Recipe recipe) {
        Session session = entityManager.unwrap(Session.class);
        Recipe recipeInDB = session.get(Recipe.class, recipe.getId());
        session.remove(recipeInDB);
    }

    public void deleteById(Long id) {
        Session session = entityManager.unwrap(Session.class);
        Recipe recipeInDB = session.get(Recipe.class, id);
        session.remove(recipeInDB);
    }
}
