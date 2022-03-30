package com.ayabaroud.crudrestservice.repository.jdbc;

import com.ayabaroud.crudrestservice.model.Ingredient;
import com.ayabaroud.crudrestservice.repository.IngredientRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class IngredientJdbcRepository implements IngredientRepository {

    public String databaseURL = "jdbc:postgresql://localhost:5432/restservices";
    public String user = "postgres";
    public String password = "postgres";

    public IngredientJdbcRepository(String databaseURL, String user, String password) {
        this.databaseURL = databaseURL;
        this.user = user;
        this.password = password;
    }

    public List<Ingredient> getAll() {
        List<Ingredient> ingredientList = new ArrayList<>();
        try {
            /** Step 1 : Loading the database driver */
            Class.forName("org.postgresql.Driver");
            /** Step 2 : Make a connection with DB */
            Connection connection = DriverManager.getConnection(databaseURL,user, password);
            /** Step 3 : Make a statement */
            String request = "SELECT * FROM INGREDIENT";
            PreparedStatement preparedStatement = connection.prepareStatement(request);
            /** Step 4 : Execute the request */
            ResultSet resultSet = preparedStatement.executeQuery();
            /** Step 5 : loop the rersulset */
            while(resultSet.next()){
                Ingredient ingredient = new Ingredient();
                ingredient.setId(resultSet.getLong("ingredient_id"));
                ingredient.setName(resultSet.getString("name"));
                ingredientList.add(ingredient);
            }
        } catch (ClassNotFoundException | SQLException exception) {
            exception.printStackTrace();
        }
        return ingredientList;
    }


    public Optional<Ingredient> getById(Long id) {
        Optional<Ingredient> optionalIngredient = null;
        try {
            /** Step 1 : Loading the database driver */
            Class.forName("org.postgresql.Driver");
            /** Step 2 : Make a connection with DB */
            Connection connection = DriverManager.getConnection(databaseURL,user, password);
            /** Step 3 : Make a statement */
            String request = "SELECT * FROM INGREDIENT WHERE ingredient_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(request);
            preparedStatement.setLong(1, id);
            /** Step 4 : Execute the request */
            ResultSet resultSet = preparedStatement.executeQuery();
            /** Step 5 : loop the rersulset */
            Ingredient ingredient = new Ingredient();
            ingredient.setId(id);
            while(resultSet.next()){
                ingredient.setName(resultSet.getString("name"));
            }
            optionalIngredient = Optional.ofNullable(ingredient);
        } catch (ClassNotFoundException | SQLException exception) {
            exception.printStackTrace();
        }
        return optionalIngredient;
    }

    public Optional<Long> create(Ingredient ingredient){
        Optional<Long> OptionalId = null;
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(databaseURL,user,password);
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "INSERT INTO INGREDIENT (name) VALUES (?)");
            preparedStatement.setString(1,ingredient.getName());
            OptionalId = Optional.ofNullable((long) preparedStatement.executeUpdate());
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
            exception.printStackTrace();
        }
        return OptionalId;
    }

    public Optional<Ingredient> update(Ingredient ingredient){
        Optional<Ingredient> optionalIngredient = null;
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(databaseURL,user,password);
            String request = "UPDATE INGREDIENT SET name=? WHERE ingredient_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(request);
            preparedStatement.setString(1,ingredient.getName());
            preparedStatement.setLong(2,ingredient.getId());
            preparedStatement.executeUpdate();
            optionalIngredient = Optional.ofNullable(ingredient);
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
        return optionalIngredient;
    }

    public void delete(Ingredient ingredient){
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(databaseURL,user,password);
            String request = "DELETE FROM INGREDIENT WHERE ingredient_id=? AND name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(request);
            preparedStatement.setLong(1,ingredient.getId());
            preparedStatement.setString(2,ingredient.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    public void deleteById(Long id){
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(databaseURL,user,password);
            String request = "DELETE FROM INGREDIENT WHERE ingredient_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(request);
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }

}
