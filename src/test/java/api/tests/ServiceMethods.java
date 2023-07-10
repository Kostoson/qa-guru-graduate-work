package api.tests;

import api.models.*;
import static api.specs.CreateOrderForAPetSpecs.*;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ServiceMethods {
    public OrderPurchasingThePetResponseBody createOrderForAPet(OrderPurchasingThePetRequestBody requestBody) {
        OrderPurchasingThePetResponseBody response=
                 given(createOrderRequestSpec)
                .body(requestBody)
                .when()
                .post("store/order")
                .then()
                .spec(response200)
                .extract().as(OrderPurchasingThePetResponseBody.class);
        return response;
    }

public CreateUserResponse createUser(CreateUserRequest requestBody) {

    CreateUserResponse response = given(createUserRequestSpec)
            .body(requestBody)
            .when()
            .post("/user")
            .then()
            .spec(response200)
            .body(matchesJsonSchemaInClasspath("jsonschemes/create-user-response-json-schema.json"))
            .extract().as(CreateUserResponse.class);
    return response;
}

    public AddANewPetResponseBody petAddToTheStore (AddANewPetRequestBody requestBody) {

        AddANewPetResponseBody responseBody = given(petAddToTheStoreRequestSpec)
                .body(requestBody)
                .when()
                .post("/pet")
                .then()
                .spec(response200)
                .body(matchesJsonSchemaInClasspath("jsonschemes/pet-add-to-the-store-response-json-schema.json"))
                .extract().as(AddANewPetResponseBody.class);
        return responseBody;
    }

    public GetUserByUserNamePositiveResponse getExistingUserByUserName(String userName) {
         GetUserByUserNamePositiveResponse responseBody = given(getUserByUserNameRequestSpec)
        .when()
        .get("/user/" + userName)
        .then()
        .spec(response200)
        .body(matchesJsonSchemaInClasspath("jsonschemes/get-user-positive-response-json-schema.json"))
        .extract().as(GetUserByUserNamePositiveResponse.class);
        return responseBody;
    }

    public GetUserByUserNameNegativeResponse getNotExistingUserByUserName(String userName) {
         GetUserByUserNameNegativeResponse responseBody = given(getUserByUserNameRequestSpec)
        .when()
        .get("/user/" + userName)
        .then()
        .spec(response404)
        .body(matchesJsonSchemaInClasspath("jsonschemes/get-user-negative-response-json-schema.json"))
        .extract().as(GetUserByUserNameNegativeResponse.class);
        return responseBody;
    }

    public DeletePurchasingThePetResponseBody deleteExistingPurchasingThePetById(int orderId) {
        DeletePurchasingThePetResponseBody responseBody = given(deletePurchasingThePetRequestSpec)
                .when()
                .delete("/store/order/" + orderId)
                .then()
                .spec(response200)
                .body(matchesJsonSchemaInClasspath("jsonschemes/delete-purchase-response-json-schema.json"))
                .extract().as(DeletePurchasingThePetResponseBody.class);
        return responseBody;
    }

    public DeletePurchasingThePetResponseBody deleteNotExistingPurchasingThePetById(int orderId) {
        DeletePurchasingThePetResponseBody responseBody = given(deletePurchasingThePetRequestSpec)
                .when()
                .delete("https://petstore.swagger.io/v2/store/order/" + orderId)
                .then()
                .spec(response404)
                .body(matchesJsonSchemaInClasspath("jsonschemes/delete-purchase-response-json-schema.json"))
                .extract().as(DeletePurchasingThePetResponseBody.class);
        return responseBody;
    }
}
