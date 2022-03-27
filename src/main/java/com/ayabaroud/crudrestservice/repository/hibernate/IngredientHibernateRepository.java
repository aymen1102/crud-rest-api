package com.ayabaroud.crudrestservice.repository.hibernate;

import com.ayabaroud.crudrestservice.model.Ingredient;
import com.ayabaroud.crudrestservice.repository.IngredientRepository;
import com.ayabaroud.crudrestservice.repository.hibernate.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class IngredientHibernateRepository implements IngredientRepository {

    public IngredientHibernateRepository() {
    }

    public List<Ingredient> getAll(){
        List<Ingredient> ingredientList = new ArrayList<>();
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        ingredientList = session.createQuery("from Ingredient",Ingredient.class).getResultList();
        return ingredientList;
    }

    public Optional<Ingredient> getById(Long id){
        Optional<Ingredient> ingredientOptional = null;
        Transaction transaction = null;
        try{
            SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            ingredientOptional = Optional.ofNullable(session.get(Ingredient.class, id));
            transaction.commit();
        } catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }
        return ingredientOptional;
    }

    public Optional<Long> create(Ingredient ingredient){
        Optional<Long> longOptional = null;
        Transaction transaction = null;
        try {
            SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            longOptional = Optional.ofNullable((long) session.save(ingredient));
            transaction.commit();
        } catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }
        return longOptional;
    }

    @Override
    public Optional<Ingredient> update(Ingredient ingredient) {
        return Optional.empty();
    }

    @Override
    public void delete(Ingredient ingredient) {

    }

    @Override
    public void deleteById(Long id) {

    }

}
