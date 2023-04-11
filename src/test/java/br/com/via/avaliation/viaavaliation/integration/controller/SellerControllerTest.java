package br.com.via.avaliation.viaavaliation.integration.controller;

import br.com.via.avaliation.viaavaliation.integration.AbstractIntegrationTest;
import br.com.via.avaliation.viaavaliation.mock.MockFactory;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.internal.mapping.Jackson1Mapper;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SellerControllerTest extends AbstractIntegrationTest {

    public static final int SERVER_PORT = 8888;
    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final String BASE_PATH = "/api/v1/seller";
    public static final String HEADER_PARAM_ORIGIN = "Origin";

    static MockFactory mockFactory;

    private static RequestSpecification specification;

    @BeforeAll
    public static void setUp() {
        mockFactory = new MockFactory();
    }

//    @Test
//    @Order(1)
//    public void testCreateSucess() throws IOException {
//        var sellerRequest = mockFactory.mockSellerRequest();
//
//        specification = new RequestSpecBuilder()
//                .addHeader(HEADER_PARAM_ORIGIN, "http://localhost:8080")
//                .setBasePath(BASE_PATH)
//                .setPort(SERVER_PORT)
//                .build();
//
//        var content = RestAssured.given().spec(specification)
//                .contentType(CONTENT_TYPE_JSON)
//                .body(sellerRequest)
//                .when()
//                .post()
//                .then()
//                .statusCode(201)
//                .extract()
//                .body()
//                .asString();
//
//        Assertions.assertNotNull(content);
//   }

    @Test
    @Order(1)
    public void testCreateWithFail() throws IOException {
        var sellerRequest = mockFactory.mockSellerRequest();
        sellerRequest.setBirthdate("999.999.999-99");
        specification = new RequestSpecBuilder()
                .addHeader(HEADER_PARAM_ORIGIN, "http://localhost:8080")
                .setBasePath(BASE_PATH)
                .setPort(SERVER_PORT)
                .build();

        var content = RestAssured.given().spec(specification)
                .contentType(CONTENT_TYPE_JSON)
                .body(sellerRequest)
                .when()
                .post()
                .then()
                .statusCode(400)
                .extract()
                .body()
                .asString();

        Assertions.assertNotNull(content);
    }

    @Test
    @Order(2)
    public void testGetAllSellers() throws IOException {

        specification = new RequestSpecBuilder()
                .addHeader(HEADER_PARAM_ORIGIN, "http://localhost:8080")
                .setBasePath(BASE_PATH)
                .setPort(SERVER_PORT)
                .build();

        var content = RestAssured.given().spec(specification)
                .contentType(CONTENT_TYPE_JSON)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();
        Assertions.assertNotNull(content);
    }
}
