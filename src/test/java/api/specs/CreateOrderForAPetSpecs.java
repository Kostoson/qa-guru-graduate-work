package api.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.filter.log.LogDetail.*;
import static io.restassured.http.ContentType.JSON;
import static api.helpers.AllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;;

public class CreateOrderForAPetSpecs {
    public static RequestSpecification createOrderRequestSpec = with()
            .log().uri()
            .log().body()
            .filter(withCustomTemplates())
            .contentType(JSON)
            .baseUri("https://petstore.swagger.io")
            .basePath("/v2");

    public static RequestSpecification createUserRequestSpec = with()
            .log().uri()
            .log().body()
            .filter(withCustomTemplates())
            .contentType(JSON)
            .baseUri("https://petstore.swagger.io")
            .basePath("/v2");

    public static RequestSpecification petAddToTheStoreRequestSpec = with()
            .log().uri()
            .log().body()
            .filter(withCustomTemplates())
            .contentType(JSON)
            .baseUri("https://petstore.swagger.io")
            .basePath("/v2");

    public static RequestSpecification getUserByUserNameRequestSpec = with()
            .log().uri()
            .filter(withCustomTemplates())
            .contentType(JSON)
            .baseUri("https://petstore.swagger.io")
            .basePath("/v2");

    public static RequestSpecification deletePurchasingThePetRequestSpec = with()
            .log().uri()
            .filter(withCustomTemplates())
            .contentType(JSON)
            .baseUri("https://petstore.swagger.io")
            .basePath("/v2");

    public static ResponseSpecification response200 = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(200)
            .build();

    public static ResponseSpecification response404 = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(404)
            .build();
}
