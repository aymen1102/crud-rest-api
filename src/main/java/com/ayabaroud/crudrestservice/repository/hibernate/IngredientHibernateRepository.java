package com.ayabaroud.crudrestservice.repository.hibernate;

import com.ayabaroud.crudrestservice.model.Ingredient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class IngredientHibernateRepository {

    private static Session session;
    private static SessionFactory sessionFactory;
    private static StandardServiceRegistry registry;

    public List<Ingredient> getAll(){
        List<Ingredient> ingredientList = new ArrayList<>();
        session = buildSessionFactory().openSession();
        session.beginTransaction();
        ingredientList = session.createQuery("from Ingredient",Ingredient.class).getResultList();
        return ingredientList;
    }

    /** This Method Is Used To Create The Hibernate's SessionFactory Object */
    private static SessionFactory buildSessionFactory() {
        if (sessionFactory == null) {
            try {
                // Create registry
                registry = new StandardServiceRegistryBuilder().configure().build();

                // Create MetadataSources
                MetadataSources sources = new MetadataSources(registry);

                // Create Metadata
                Metadata metadata = sources.getMetadataBuilder().build();

                // Create SessionFactory
                sessionFactory = metadata.getSessionFactoryBuilder().build();

            } catch (Exception e) {
                e.printStackTrace();
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
        }
        return sessionFactory;
    }

}
