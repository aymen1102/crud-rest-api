# Tutorial to make the project work locally :<br />
1 - Import the projet on your IDE<br />
2 - Build the project `mvn clean install`<br />
3 - Create a postgres database named `restservices` <br />
4 - Run the spring boot app<br />
5 - Use postman to try requests  :<br />
6 - Use OpenAPI/SWAGGER to notice all the requests `http://localhost:8091/swagger-ui/index.html#/`<br />
* find all ingredients : `GET` `localhost:8091/api/v1/ingredients/getAll`<br />
* find ingredient by Id using the path : `GET` `localhost:8091/api/v1/ingredients/getIngredientById/{id}`<br />
* add an ingredient : `POST` `localhost:8091/api/v1/ingredients/createIngredient` `{"name": "cheese"}`<br />
* delete an ingredient : `DELETE` `localhost:8091/api/v1/ingredients/deleteIngredient` <br />

