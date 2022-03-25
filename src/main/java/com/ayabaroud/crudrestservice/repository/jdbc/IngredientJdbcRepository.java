package com.ayabaroud.crudrestservice.repository.jdbc;

import com.ayabaroud.crudrestservice.model.Ingredient;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class IngredientJdbcRepository {

    private static final String databaseURL = "jdbc:postgresql://localhost:5432/restservices";
    private static final String user = "postgres";
    private static final String password = "postgres";

    public static List<Ingredient> getAll() {
        List<Ingredient> ingredientList = new ArrayList<>();

        try {
            /** Step 1 : Loading the database driver */
            Class.forName("org.postgresql.Driver");
            /** Step 2 : Make a connection with DB */
            Connection connection = DriverManager.getConnection(databaseURL,user, password);
            /** Step 3 : Make a statement */
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM INGREDIENT");
            /** Step 4 : Execute the request */
            ResultSet resultSet = preparedStatement.executeQuery();
            /** Step 5 : loop the rersulset */
            while(resultSet.next()){
                Ingredient ingredient = new Ingredient();
                ingredient.setId(resultSet.getLong("ingredient_id"));
                ingredient.setName(resultSet.getString("name"));
                ingredientList.add(ingredient);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return ingredientList;
    }



}
