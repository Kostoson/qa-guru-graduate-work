package api.tests;

import api.models.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
public class PetstoreTests {
    @Test
    void createUserTest() {
        ServiceMethods serviceMethods = new ServiceMethods();
        int id = 1;
        String username = "TestK";
        String firstName = "QA";
        String lastName = "Java";
        String email = "test@qa.guru";
        String password = "123";
        String phone = "78887776655";
        int userStatus = 1;
        CreateUserResponse response =
        step("Отправка валидного POST запроса на создание пользователя", () ->
            serviceMethods.createUser(id, username, firstName, lastName, email, password, phone, userStatus));
        step("Проверка тела ответа", () ->
        {
            Assertions.assertAll( () -> {
                    assertThat(response.getCode()).isEqualTo(200);
                    assertThat(response.getType()).isEqualTo("unknown");
                    assertThat(response.getMessage()).isEqualTo("1");
            });
        });
    }
    @Test
    void getUserByUserNamePositiveTest() {
        ServiceMethods serviceMethods = new ServiceMethods();
        int id = 345;
        String userName = "TestKos";
        String firstName = "QA";
        String lastName = "Java";
        String email = "test@qa.guru";
        String password = "123";
        String phone = "78887776655";
        int userStatus = 1;
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
    void getUserByUserNameNegativeTest() {
        ServiceMethods serviceMethods = new ServiceMethods();
        String userName = "NegativeUser";
        GetUserByUserNameNegativeResponse negativeResponse =
        step("Отправка валидного GET запроса на получение несуществующего пользователя", () ->
             serviceMethods.getNotExistingUserByUserName(userName));
        step("Проверка тела ответа", () ->
            {
             Assertions.assertAll(() -> {
                assertThat(negativeResponse.getCode()).isEqualTo(1);
                assertThat(negativeResponse.getType()).isEqualTo("error");
                assertThat(negativeResponse.getMessage()).isEqualTo("User not found");
                });
            });
    }
    @Test
    void petAddToTheStoreTest() {
        ServiceMethods serviceMethods = new ServiceMethods();

        int id = 123;
        String name = "Test";
        String status = "Test";
        int petId = 1;
        String petName = "Inoske";

        AddANewPetResponseBody responseBody =
        step("Отправка валидного POST запроса на добавление питомца в базу", () ->
             serviceMethods.petAddToTheStore(id, name, status, petId, petName));
        step("Проверка тела ответа", () ->
        {
            Assertions.assertAll(() -> {
                assertThat(responseBody.getId()).isEqualTo(id);
                assertThat(responseBody.getName()).isEqualTo(name);
                assertThat(responseBody.getStatus()).isEqualTo(status);
            });
        });
        // to do узнать как проверить объект category
        //to do узнать как передать массив в тело запроса и как потом его проверить
    }
    @Test
    void orderPurchasingThePetTest() {
        ServiceMethods serviceMethods = new ServiceMethods();
        String shipDate = "2023-07-07T10:13:16.017+0000";
        String status = "placed";
        int id = 1;
        int petId = 2;
        int quantity = 1;
        boolean complete = true;
        OrderPurchasingThePetResponseBody response =
        step("Отправка валидного POST запроса на создание заказа питомца", () ->
             serviceMethods.createOrderForAPet(shipDate, status, id, petId, quantity, complete));
        step("Проверка тела ответа", () ->
        {
            Assertions.assertAll(() -> {
                assertThat(response.getStatus()).isEqualTo(status);
                assertThat(response.getShipDate()).isEqualTo(shipDate);
                assertThat(response.getId()).isEqualTo(1);
                assertThat(response.getPetId()).isEqualTo(2);
                assertThat(response.getQuantity()).isEqualTo(1);
                assertThat(response.getComplete()).isEqualTo(complete);
            });
        });
    }
    @Test
    @Tag("API")
    void deletePurchasingThePetByIdTest() {
        ServiceMethods serviceMethods = new ServiceMethods();
        String shipDate = "2023-07-07T10:13:16.017+0000";
        String status = "placed";
        int id = 4;
        int petId = 3;
        int quantity = 1;
        boolean complete = true;
        int orderId = 4;
        step("Отправка валидного POST запроса на создание заказа питомца", () ->
             serviceMethods.createOrderForAPet(shipDate, status, id, petId, quantity, complete));
        DeletePurchasingThePetResponseBody response =
        step("Отправка валидного DELETE запроса на удаление существующего заказа питомца", () ->
              serviceMethods.deleteExistingPurchasingThePetById(orderId));
        step("Проверка тела ответа", () ->
        {
            Assertions.assertAll(() -> {
                assertThat(response.getCode()).isEqualTo(200);
                assertThat(response.getType()).isEqualTo("unknown");
                assertThat(response.getMessage()).isEqualTo("4");
            });
        });
    }
    @Test
    @Tag("API")
    void negativeDeletePurchasingThePetByIdTest() {
        ServiceMethods serviceMethods = new ServiceMethods();
        int orderId = 4;
        String message = "Order Not Found";
        DeletePurchasingThePetResponseBody response =
        step("Отправка валидного DELETE запроса на удаление не существующего заказа питомца", () ->
             serviceMethods.deleteNotExistingPurchasingThePetById(orderId));
        step("Проверка тела ответа", () ->
        {
            Assertions.assertAll(() -> {
                assertThat(response.getCode()).isEqualTo(404);
                assertThat(response.getType()).isEqualTo("unknown");
                assertThat(response.getMessage()).isEqualTo(message);
            });
        });
    }
}
