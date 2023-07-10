package api.tests;

import api.models.*;
import api.helpers.RandomUtils;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
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
        CreateUserRequest requestBody = CreateUserRequest.builder().id(id).username(userName).firstName(firstName).lastName(lastName).email(email).password(password).phone(phone).userStatus(userStatus).build();
        CreateUserResponse response =
        step("Отправка валидного POST запроса на создание пользователя", () ->
            serviceMethods.createUser(requestBody));
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
        CreateUserRequest requestBody = CreateUserRequest.builder().id(id).username(userName).firstName(firstName).lastName(lastName).email(email).password(password).phone(phone).userStatus(userStatus).build();
        step("Отправка валидного POST запроса на создание пользователя", () ->
            serviceMethods.createUser(requestBody));
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
        Pet category = new Pet();
        category.setId(randomUtils.getRandomId());
        category.setName(randomUtils.getRandomUserName());
        List<String> photoUrls = Arrays.asList("https://test.qa");
        api.models.Tags tags = new api.models.Tags();
        tags.setId(randomUtils.getRandomId());
        tags.setName(randomUtils.getRandomTag());
        List<api.models.Tags> tagsList = new ArrayList();
        tagsList.add(tags);
        AddANewPetRequestBody requestBody = AddANewPetRequestBody.builder().id(id).name(name)
                .category(category).photoUrls(photoUrls).tags(tagsList).status(status).build();
        AddANewPetResponseBody responseBody =
                step("Отправка валидного POST запроса на добавление питомца в базу", () ->
                        serviceMethods.petAddToTheStore(requestBody));
        step("Проверка тела ответа", () ->
        {
            Assertions.assertAll(() -> {
                assertThat(responseBody.getId()).isEqualTo(id);
                assertThat(responseBody.getName()).isEqualTo(name);
                assertThat(responseBody.getStatus()).isEqualTo(status);
                assertThat(responseBody.getCategory()).isEqualTo(category);
                assertThat(responseBody.getTags().get(0).getId()).isEqualTo(tags.getId());
                assertThat(responseBody.getTags().get(0).getName()).isEqualTo(tags.getName());
                assertThat(responseBody.getPhotoUrls()).isEqualTo(photoUrls);
            });
        });
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
        OrderPurchasingThePetRequestBody requestBody = OrderPurchasingThePetRequestBody.builder().shipDate(shipDate).status(status).id(id).petId(petId).quantity(quantity).complete(complete).build();
        OrderPurchasingThePetResponseBody response =
        step("Отправка валидного POST запроса на создание заказа питомца", () ->
             serviceMethods.createOrderForAPet(requestBody));
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
        OrderPurchasingThePetRequestBody requestBody = OrderPurchasingThePetRequestBody.builder().shipDate(shipDate).status(status).id(id).petId(petId).quantity(quantity).complete(complete).build();
        step("Отправка валидного POST запроса на создание заказа питомца", () ->
             serviceMethods.createOrderForAPet(requestBody));
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
