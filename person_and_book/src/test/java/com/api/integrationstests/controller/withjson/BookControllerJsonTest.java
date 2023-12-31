package com.api.integrationstests.controller.withjson;

import com.api.configs.TestConfigs;
import com.api.integrationstests.httpbody.CreateUpdatePartialsBookRequest;
import com.api.integrationstests.httpbody.ResponseBook;
import com.api.testcontainers.AbstractIntegrationTest;
import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.DeserializationFeature;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookControllerJsonTest extends AbstractIntegrationTest {


    private static RequestSpecification specification;
    private static ObjectMapper objectMapper;
    private static CreateUpdatePartialsBookRequest bookFake;
    private static ResponseBook responseBook;
    private static Faker faker;


    @BeforeAll
    public static void setup() {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES); //disable fails of the attributes without on object deserialization

        bookFake = null;
        responseBook = null;
        faker = new Faker();
    }

    @Test
    @Order(1)
    void createBookTest() throws IOException {
        bookFake = createNewBookRequest();

        specification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_LOCALHOST)
                .setBasePath("/api/book/v1")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        Response response = given().spec(specification)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(bookFake)
                .when()
                .post()
                .then()
                .extract()
                .response();

        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());

        responseBook = objectMapper.readValue(response.body().asString(), ResponseBook.class);

        assertNotNull(responseBook);

        assertNotNull(responseBook.getId());
        assertNotNull(responseBook.getAuthor());
        assertNotNull(responseBook.getLaunchDate());
        assertNotNull(responseBook.getTitle());
        assertNotNull(responseBook.getPrice());

        assertEquals(bookFake.getAuthor(), responseBook.getAuthor());
        assertEquals(bookFake.getLaunchDate(), responseBook.getLaunchDate());
        assertEquals(bookFake.getTitle(), responseBook.getTitle());
        assertEquals(bookFake.getPrice(), responseBook.getPrice());
    }

    @Test
    @Order(2)
    void createBookWithWrongOriginTest() {
        specification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_WRONG)
                .setBasePath("/api/book/v1")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        Response response = given().spec(specification)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(bookFake)
                .when()
                .post()
                .then()
                .extract()
                .response();

        assertEquals(HttpStatus.FORBIDDEN.value(), response.getStatusCode());
    }

    @Test
    @Order(3)
    void getBookByIdTest() throws IOException {
        specification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_LOCALHOST)
                .setBasePath("/api/book/v1")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        Response response = given().spec(specification)
                .accept(ContentType.JSON)
                .pathParam("bookId", responseBook.getId())
                .when()
                .get("{bookId}")
                .then()
                .extract()
                .response();

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        ResponseBook responseBookFind = objectMapper.readValue(response.body().asString(), ResponseBook.class);

        assertNotNull(responseBookFind);

        assertNotNull(responseBookFind.getId());
        assertNotNull(responseBookFind.getAuthor());
        assertNotNull(responseBookFind.getLaunchDate());
        assertNotNull(responseBookFind.getTitle());
        assertNotNull(responseBookFind.getPrice());

        assertEquals(bookFake.getAuthor(), responseBookFind.getAuthor());
        assertEquals(bookFake.getLaunchDate(), responseBookFind.getLaunchDate());
        assertEquals(bookFake.getTitle(), responseBookFind.getTitle());
        assertEquals(Math.ceil(bookFake.getPrice()), Math.ceil(responseBookFind.getPrice()));
    }

    @Test
    @Order(4)
    void getBookByIdWithWrongOriginTest() throws IOException {
        CreateUpdatePartialsBookRequest bookToUpdate = createNewBookRequest();

        specification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_WRONG)
                .setBasePath("/api/book/v1")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        UUID bookFakeId = UUID.randomUUID();

        Response response = given().spec(specification)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("bookId", bookFakeId)
                .body(bookToUpdate)
                .when()
                .get("{bookId}")
                .then()
                .extract()
                .response();

        assertEquals(HttpStatus.FORBIDDEN.value(), response.getStatusCode());
    }

    @Test
    @Order(5)
    void updatePartialsBookTest() throws IOException {
        CreateUpdatePartialsBookRequest bookToUpdate = createNewBookRequest();

        specification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_LOCALHOST)
                .setBasePath("/api/book/v1")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        Response response = given().spec(specification)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("bookId", responseBook.getId())
                .body(bookToUpdate)
                .when()
                .patch("{bookId}")
                .then()
                .extract()
                .response();

        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatusCode());
    }

    @Test
    @Order(6)
    void updatePartialsBookWithWrongOriginTest() throws IOException {
        CreateUpdatePartialsBookRequest bookToUpdate = createNewBookRequest();

        specification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_WRONG)
                .setBasePath("/api/book/v1")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        UUID bookFakeId = UUID.randomUUID();

        Response response = given().spec(specification)
                .contentType(ContentType.JSON)
                .pathParam("bookId", bookFakeId)
                .body(bookToUpdate)
                .when()
                .patch("{bookId}")
                .then()
                .extract()
                .response();

        assertEquals(HttpStatus.FORBIDDEN.value(), response.getStatusCode());
    }

    @Test
    @Order(7)
    void deleteBookTest() {
        specification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_LOCALHOST)
                .setBasePath("/api/book/v1")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        Response response = given().spec(specification)
                .pathParam("bookId", responseBook.getId())
                .when()
                .delete("{bookId}")
                .then()
                .extract()
                .response();

        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatusCode());
    }

    @Test
    @Order(7)
    void deleteBookWithWrongOriginTest() {
        specification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_WRONG)
                .setBasePath("/api/book/v1")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        UUID bookFakeId = UUID.randomUUID();

        Response response = given().spec(specification)
                .pathParam("bookId", responseBook.getId())
                .when()
                .delete("{bookId}")
                .then()
                .extract()
                .response();

        assertEquals(HttpStatus.FORBIDDEN.value(), response.getStatusCode());
    }

    private CreateUpdatePartialsBookRequest createNewBookRequest() {
        CreateUpdatePartialsBookRequest bookFake = new CreateUpdatePartialsBookRequest();
        bookFake.setAuthor(faker.book().author());
        bookFake.setPrice(faker.random().nextDouble());
        bookFake.setTitle(faker.book().title());
        bookFake.setLaunchDate(faker.date().birthday());
        return bookFake;
    }
}
