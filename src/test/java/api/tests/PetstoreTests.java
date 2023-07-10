package api.tests;

import api.models.*;
import api.helpers.RandomUtils;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Tags;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.qameta.allure.Allure.step;
import static io.qameta.allure.SeverityLevel.BLOCKER;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static org.assertj.core.api.Assertions.assertThat;

@Feature("Petstore")
@Tags({ @Tag("API"), @Tag("Regress")})
@DisplayName("Тестирование API сервиса Petstore")
public class PetstoreTests {
    RandomUtils randomUtils = new RandomUtils();
    @Test
    @Severity(BLOCKER)
    @DisplayName("Позитивный сценарий создания пользователя")
    void createUserTest() {
        ServiceMethods serviceMethods = new ServiceMethods();
        int id = randomUtils.getRandomId();
        String userName = randomUtils.getRandomUserName();
        String firstName = randomUtils.getRandomFirstName();
        String lastName = randomUtils.getRandomLastName();
        String email = randomUtils.getRandomEmail();
        String password = randomUtils.getRandomPass();
        String phone = randomUtils.getRandomPhoneNumber(11);
        int userStatus = randomUtils.getRandomInt(0, 5);
        CreateUserResponse response =
        step("Отправка валидного POST запроса на создание пользователя", () ->
            serviceMethods.createUser(id, userName, firstName, lastName, email, password, phone, userStatus));
        step("Проверка тела ответа", () ->
        {
            Assertions.assertAll( () -> {
                    assertThat(response.getCode()).isEqualTo(200);
                    assertThat(response.getType()).isEqualTo("unknown");
                    assertThat(response.getMessage()).isEqualTo(String.valueOf(id));
            });
        });
    }
    @Test
    @Severity(CRITICAL)
    @DisplayName("Позитивный сценарий получения пользователя")
    void getUserByUserNamePositiveTest() {
        ServiceMethods serviceMethods = new ServiceMethods();
        int id = randomUtils.getRandomId();
        String userName = randomUtils.getRandomUserName();
        String firstName = randomUtils.getRandomFirstName();
        String lastName = randomUtils.getRandomLastName();
        String email = randomUtils.getRandomEmail();
        String password = randomUtils.getRandomPass();
        String phone = randomUtils.getRandomPhoneNumber(11);
        int userStatus = randomUtils.getRandomInt(0, 5);
        step("Отправка валидного POST запроса на создание пользователя", () ->
            serviceMethods.createUser(id, userName, firstName, lastName, email, password, phone, userStatus));
        GetUserByUserNamePositiveResponse response =
        step("Отправка валидного GET запроса на получение существующего пользователя", () ->
            serviceMethods.getExistingUserByUserName(userName));
        step("Проверка тела ответа", () ->
        {
            Assertions.assertAll(() -> {
                assertThat(response.getId()).isEqualTo(id);
                assertThat(response.getUsername()).isEqualTo(userName);
                assertThat(response.getFirstName()).isEqualTo(firstName);
                assertThat(response.getLastName()).isEqualTo(lastName);
                assertThat(response.getEmail()).isEqualTo(email);
                assertThat(response.getPassword()).isEqualTo(password);
                assertThat(response.getPhone()).isEqualTo(phone);
                assertThat(response.getUserStatus()).isEqualTo(userStatus);
            });
        });
    }
        @Test
        @Severity(CRITICAL)
        @DisplayName("Негативный сценарий получения пользователя по несуществующему имени пользователя")
    void getUserByUserNameNegativeTest() {
        ServiceMethods serviceMethods = new ServiceMethods();
        String userName = randomUtils.getRandomUserName();
        String type = "error";
        String message = "User not found";
        GetUserByUserNameNegativeResponse negativeResponse =
        step("Отправка валидного GET запроса на получение несуществующего пользователя", () ->
             serviceMethods.getNotExistingUserByUserName(userName));
        step("Проверка тела ответа", () ->
            {
             Assertions.assertAll(() -> {
                assertThat(negativeResponse.getCode()).isEqualTo(1);
                assertThat(negativeResponse.getType()).isEqualTo(type);
                assertThat(negativeResponse.getMessage()).isEqualTo(message);
                });
            });
    }
    @Test
    @Severity(BLOCKER)
    @DisplayName("Позитивный сценарий добавления питомца в базу")
    void petAddToTheStoreTest() {
        ServiceMethods serviceMethods = new ServiceMethods();

        int id = randomUtils.getRandomId();
        String name = randomUtils.getRandomFirstName();
        String status = randomUtils.getRandomStatus();
        int petId = randomUtils.getRandomId();
        String petName = randomUtils.getRandomUserName();
        List<String> photoUrls = Arrays.asList("https://test.qa");
        int tagId = randomUtils.getRandomId();
        String tagName = randomUtils.getRandomTag();
        AddANewPetResponseBody responseBody =
        step("Отправка валидного POST запроса на добавление питомца в базу", () ->
             serviceMethods.petAddToTheStore(id, name, status, petId, petName, photoUrls, tagId, tagName));
        step("Проверка тела ответа", () ->
        {
            Assertions.assertAll(() -> {
                assertThat(responseBody.getId()).isEqualTo(id);
                assertThat(responseBody.getName()).isEqualTo(name);
                assertThat(responseBody.getStatus()).isEqualTo(status);
                /*assertThat(responseBody.getCategory()).isEqualTo(1);*/
                assertThat(responseBody.getTags()).isEqualTo(1);
            });
        });
        // to do узнать как проверить объект category
        //to do узнать как передать массив в тело запроса и как потом его проверить
    }
    @Test
    @Severity(BLOCKER)
    @DisplayName("Позитивный сценарий на добавление питомца к заказу")
    void orderPurchasingThePetTest() {
        ServiceMethods serviceMethods = new ServiceMethods();
        String shipDate = randomUtils.getRandomDate();
        String status = randomUtils.getRandomStatus();
        int id = randomUtils.getRandomId();
        int petId = randomUtils.getRandomId();
        int quantity = randomUtils.getRandomInt(0,4);
        boolean complete = randomUtils.getRandomBoolean();
        OrderPurchasingThePetResponseBody response =
        step("Отправка валидного POST запроса на создание заказа питомца", () ->
             serviceMethods.createOrderForAPet(shipDate, status, id, petId, quantity, complete));
        step("Проверка тела ответа", () ->
        {
            Assertions.assertAll(() -> {
                assertThat(response.getStatus()).isEqualTo(status);
                assertThat(response.getShipDate()).isEqualTo(shipDate);
                assertThat(response.getId()).isEqualTo(id);
                assertThat(response.getPetId()).isEqualTo(petId);
                assertThat(response.getQuantity()).isEqualTo(quantity);
                assertThat(response.getComplete()).isEqualTo(complete);
            });
        });
    }
    @Test
    @Severity(CRITICAL)
    @DisplayName("Позитивный сценарий на удаление заказа питомца")
    void deletePurchasingThePetByIdTest() {
        ServiceMethods serviceMethods = new ServiceMethods();
        String shipDate = randomUtils.getRandomDate();
        String status = randomUtils.getRandomStatus();
        int id = randomUtils.getRandomId();
        int petId = randomUtils.getRandomId();
        int quantity = randomUtils.getRandomInt(0, 4);
        boolean complete = randomUtils.getRandomBoolean();
        int orderId = id;
        String type = "unknown";
        step("Отправка валидного POST запроса на создание заказа питомца", () ->
             serviceMethods.createOrderForAPet(shipDate, status, id, petId, quantity, complete));
        DeletePurchasingThePetResponseBody response =
        step("Отправка валидного DELETE запроса на удаление существующего заказа питомца", () ->
              serviceMethods.deleteExistingPurchasingThePetById(orderId));
        step("Проверка тела ответа", () ->
        {
            Assertions.assertAll(() -> {
                assertThat(response.getCode()).isEqualTo(200);
                assertThat(response.getType()).isEqualTo(type);
                assertThat(response.getMessage()).isEqualTo(String.valueOf(orderId));
            });
        });
    }
    @Test
    @Severity(CRITICAL)
    @DisplayName("Негативный сценарий на удаление заказа питомца по несуществующему orderId")
    void negativeDeletePurchasingThePetByIdTest() {
        ServiceMethods serviceMethods = new ServiceMethods();
        int orderId = randomUtils.getRandomId();
        String message = "Order Not Found";
        String type = "unknown";
        DeletePurchasingThePetResponseBody response =
        step("Отправка валидного DELETE запроса на удаление не существующего заказа питомца", () ->
             serviceMethods.deleteNotExistingPurchasingThePetById(orderId));
        step("Проверка тела ответа", () ->
        {
            Assertions.assertAll(() -> {
                assertThat(response.getCode()).isEqualTo(404);
                assertThat(response.getType()).isEqualTo(type);
                assertThat(response.getMessage()).isEqualTo(message);
            });
        });
    }
}
