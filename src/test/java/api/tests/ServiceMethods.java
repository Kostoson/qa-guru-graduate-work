package api.tests;

import api.models.*;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.RestAssured.given;

public class ServiceMethods {
    public OrderPurchasingThePetResponseBody createOrderForAPet(String shipDate, String status, int id, int petId, int quantity, boolean complete) {
        OrderPurchasingThePetRequestBody request = new OrderPurchasingThePetRequestBody();
        request.setId(id);
        request.setPetId(petId);
        request.setQuantity(quantity);
        request.setShipDate(shipDate);
        request.setStatus(status);
        request.setComplete(complete);
        OrderPurchasingThePetResponseBody response= given()
                .log().uri()
                .log().body()
                .contentType("application/json")
                .body(request)
                .when()
                .post("https://petstore.swagger.io/v2/store/order")
                .then()
                .statusCode(200).log().body()
                .extract().as(OrderPurchasingThePetResponseBody.class);
        return response;
    }

    public CreateUserResponse createUser(int id, String username, String firstName, String lastName, String email, String password, String phone, int userStatus) {
        CreateUserRequest request = new CreateUserRequest();
        request.setId(id);
        request.setUsername(username);
        request.setFirstName(firstName);
        request.setLastName(lastName);
        request.setEmail(email);
        request.setPassword(password);
        request.setPhone(phone);
        request.setUserStatus(userStatus);
        CreateUserResponse response = given()
                .log().uri()
                .log().body()
                .contentType("application/json")
                .body(request)
                .when()
                .post("https://petstore.swagger.io/v2/user")
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("jsonschemes/create-user-response-json-schema.json"))
                .log().body()
                .extract().as(CreateUserResponse.class);
        return response;
    }

    public AddANewPetResponseBody petAddToTheStore(int id, String name, String status, int petId, String petName) {
        AddANewPetRequestBody requestBody = new AddANewPetRequestBody();
        Pet pet = new Pet();
        requestBody.setId(id);
        requestBody.setName(name);
        requestBody.setStatus(status);
        pet.setId(petId);
        pet.setName(petName);
        requestBody.setCategory(pet);
        AddANewPetResponseBody responseBody = given()
                .log().uri()
                .log().body()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("https://petstore.swagger.io/v2/pet")
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("jsonschemes/pet-add-to-the-store-response-json-schema.json"))
                .log().body()
                .extract().as(AddANewPetResponseBody.class);
        return responseBody;
    }

    public GetUserByUserNamePositiveResponse getExistingUserByUserName(String userName) {
         GetUserByUserNamePositiveResponse responseBody = given()
        .log().uri()
        .contentType("application/json")
        .when()
        .get("https://petstore.swagger.io/v2/user/" + userName)
        .then()
        .statusCode(200)
        .body(matchesJsonSchemaInClasspath("jsonschemes/get-user-positive-response-json-schema.json"))
        .log().body()
        .extract().as(GetUserByUserNamePositiveResponse.class);
        return responseBody;
    }
    public GetUserByUserNameNegativeResponse getNotExistingUserByUserName(String userName) {
         GetUserByUserNameNegativeResponse responseBody = given()
        .log().uri()
        .contentType("application/json")
        .when()
        .get("https://petstore.swagger.io/v2/user/" + userName)
        .then()
        .statusCode(404)
        .body(matchesJsonSchemaInClasspath("jsonschemes/get-user-negative-response-json-schema.json"))
        .log().body()
        .extract().as(GetUserByUserNameNegativeResponse.class);
        return responseBody;
    }

    public DeletePurchasingThePetResponseBody deleteExistingPurchasingThePetById(int orderId) {
        DeletePurchasingThePetResponseBody responseBody = given()
                .log().uri()
                .log().body()
                .contentType("application/json")
                .when()
                .delete("https://petstore.swagger.io/v2/store/order/" + orderId)
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("jsonschemes/delete-purchase-response-json-schema.json"))
                .log().body()
                .extract().as(DeletePurchasingThePetResponseBody.class);
        return responseBody;
    }
    public DeletePurchasingThePetResponseBody deleteNotExistingPurchasingThePetById(int orderId) {
        DeletePurchasingThePetResponseBody responseBody = given()
                .log().uri()
                .log().body()
                .contentType("application/json")
                .when()
                .delete("https://petstore.swagger.io/v2/store/order/" + orderId)
                .then()
                .statusCode(404)
                .body(matchesJsonSchemaInClasspath("jsonschemes/delete-purchase-response-json-schema.json"))
                .log().body()
                .extract().as(DeletePurchasingThePetResponseBody.class);
        return responseBody;
    }
}
