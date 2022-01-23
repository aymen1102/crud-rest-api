package com.ayabaroud.crudrestservice.dto;

import com.ayabaroud.crudrestservice.model.RecipeIngredient;

import java.util.Objects;

public class RecipeIngredientDto {

    private Long id;
    private IngredientDto ingredient;
    private Long quantity;
    private String unit;

    /**
     * Mandatory for Spring
     */
    public RecipeIngredientDto() {

    }

    RecipeIngredientDto(RecipeIngredient recipeIngredient) {
        this.id = recipeIngredient.getId();
        this.ingredient = new IngredientDto(recipeIngredient.getIngredient());
        this.quantity = recipeIngredient.getQuantity();
        this.unit = recipeIngredient.getUnit();
    }

    RecipeIngredient toRecipeIngredient() {
        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setId(this.id);
        recipeIngredient.setIngredient(this.ingredient.toIngredient());
        recipeIngredient.setQuantity(this.quantity);
        recipeIngredient.setUnit(this.unit);
        return recipeIngredient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public IngredientDto getIngredient() {
        return ingredient;
    }

    public void setIngredient(IngredientDto ingredient) {
        this.ingredient = ingredient;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeIngredientDto that = (RecipeIngredientDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(ingredient, that.ingredient) &&
                Objects.equals(quantity, that.quantity) &&
                Objects.equals(unit, that.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ingredient, quantity, unit);
    }
}
