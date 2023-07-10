package api.tests;

import api.models.*;

import java.util.ArrayList;
import java.util.List;

import static api.helpers.AllureListener.withCustomTemplates;
import static api.specs.CreateOrderForAPetSpecs.*;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ServiceMethods {
    public OrderPurchasingThePetResponseBody createOrderForAPet(String shipDate, String status, int id, int petId, int quantity, boolean complete) {
        OrderPurchasingThePetRequestBody request = new OrderPurchasingThePetRequestBody();
        request.setId(id);
        request.setPetId(petId);
        request.setQuantity(quantity);
        request.setShipDate(shipDate);
        request.setStatus(status);
        request.setComplete(complete);
        OrderPurchasingThePetResponseBody response=
                 given(createOrderRequestSpec)
                .body(request)
                .when()
                .post("store/order")
                .then()
                .spec(response200)
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
        CreateUserResponse response = given(createUserRequestSpec)
                .body(request)
                .when()
                .post("/user")
                .then()
                .spec(response200)
                .body(matchesJsonSchemaInClasspath("jsonschemes/create-user-response-json-schema.json"))
                .extract().as(CreateUserResponse.class);
        return response;
    }

    public AddANewPetResponseBody petAddToTheStore(int id, String name, String status, int petId, String petName, List<String> photoUrls, int tagId, String tagName) {
        AddANewPetRequestBody requestBody = new AddANewPetRequestBody();
        Tags tag = new Tags();
        Pet pet = new Pet();
        requestBody.setId(id);
        requestBody.setName(name);
        requestBody.setStatus(status);
        requestBody.setPhotoUrls(photoUrls);
        tag.setId(tagId);
        tag.setName(tagName);
        List<Tags> tags = new ArrayList();
        tags.add(tag);
        requestBody.setTags(tags);
        pet.setId(petId);
        pet.setName(petName);
        requestBody.setCategory(pet);
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
