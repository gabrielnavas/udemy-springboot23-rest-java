package com.api.integrationstests.controller.withjson;

import com.api.configs.TestConfigs;
import com.api.integrationstests.httpbodies.CreateUpdatePartialsPersonRequest;
import com.api.integrationstests.httpbodies.ResponsePerson;
import com.api.integrationstests.testcontainers.AbstractIntegrationTest;
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
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonControllerJsonTest extends AbstractIntegrationTest {

    private static RequestSpecification specification;

    private static ObjectMapper objectMapper;

    private static CreateUpdatePartialsPersonRequest personFake;
    private static ResponsePerson responsePerson;
    private static Faker faker;


    @BeforeAll
    public static void setup() {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES); //disable fails of the attributes without on object deserialization

        personFake = new CreateUpdatePartialsPersonRequest();
        responsePerson = null;
        faker = new Faker();
    }

    @Test
    @Order(1)
    void createPersonTest() throws IOException {
        mockPerson();

        specification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_LOCALHOST)
                .setBasePath("/api/person/v1")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        Response response = given().spec(specification)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(personFake)
                .when()
                .post()
                .then()
                .extract()
                .response();

        responsePerson = objectMapper.readValue(response.body().asString(), ResponsePerson.class);

        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());

        assertNotNull(responsePerson);

        assertNotNull(responsePerson.getId());
        assertNotNull(responsePerson.getFirstname());
        assertNotNull(responsePerson.getLastname());
        assertNotNull(responsePerson.getEmail());
        assertNotNull(responsePerson.getUsername());
        assertNotNull(responsePerson.getCreatedAt());
        assertNotNull(responsePerson.getUpdatedAt());
        assertNotNull(responsePerson.getBirthday());

        assertTrue(responsePerson.getId().length() > 0);

//        // TODO: verificar datas
//        Date now = new Date();
//        assertTrue(now.after(responsePerson.getCreatedAt()) || now.equals(responsePerson.getCreatedAt()));
//        assertTrue(now.after(responsePerson.getUpdatedAt()) || now.equals(responsePerson.getUpdatedAt()));

        assertEquals(personFake.getFirstname(), responsePerson.getFirstname());
        assertEquals(personFake.getLastname(), responsePerson.getLastname());
        assertEquals(personFake.getEmail(), responsePerson.getEmail());
        assertEquals(personFake.getUsername(), responsePerson.getUsername());
        assertEquals(personFake.getBirthday(), responsePerson.getBirthday());
        assertEquals(personFake.getFirstname(), responsePerson.getFirstname());
        assertEquals(personFake.getFirstname(), responsePerson.getFirstname());
    }


    @Test
    @Order(2)
    void createPersonWithWrongOriginTest() {
        mockPerson();

        specification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_WRONG)
                .setBasePath("/api/person/v1")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        Response response = given().spec(specification)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(personFake)
                .when()
                .post()
                .then()
                .extract()
                .response();

        assertEquals(HttpStatus.FORBIDDEN.value(), response.statusCode());
    }


    @Test
    @Order(3)
    void findPersonByIdTest() throws IOException {
        mockPerson();

        specification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_LOCALHOST)
                .setBasePath("/api/person/v1")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        Response response = given().spec(specification)
                .accept(ContentType.JSON)
                .pathParam("personId", responsePerson.getId())
                .when()
                .get("{personId}")
                .then()
                .extract()
                .response();

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        ResponsePerson responsePersonFind = objectMapper.readValue(response.body().asString(), ResponsePerson.class);

        assertNotNull(responsePersonFind);

        assertNotNull(responsePersonFind.getId());
        assertNotNull(responsePersonFind.getFirstname());
        assertNotNull(responsePersonFind.getLastname());
        assertNotNull(responsePersonFind.getEmail());
        assertNotNull(responsePersonFind.getUsername());
        assertNotNull(responsePersonFind.getCreatedAt());
        assertNotNull(responsePersonFind.getUpdatedAt());
        assertNotNull(responsePersonFind.getBirthday());

        assertTrue(responsePersonFind.getId().length() > 0);

//        // TODO: verificar datas
//        Date now = new Date();
//        assertTrue(now.after(responsePerson.getCreatedAt()) || now.equals(responsePerson.getCreatedAt()));
//        assertTrue(now.after(responsePerson.getUpdatedAt()) || now.equals(responsePerson.getUpdatedAt()));

        assertEquals(responsePerson.getFirstname(), responsePersonFind.getFirstname());
        assertEquals(responsePerson.getLastname(), responsePersonFind.getLastname());
        assertEquals(responsePerson.getEmail(), responsePersonFind.getEmail());
        assertEquals(responsePerson.getUsername(), responsePersonFind.getUsername());
        assertEquals(responsePerson.getBirthday(), responsePersonFind.getBirthday());
        assertEquals(responsePerson.getFirstname(), responsePersonFind.getFirstname());
        assertEquals(responsePerson.getFirstname(), responsePersonFind.getFirstname());
    }


    @Test
    @Order(4)
    void findPersonByIdWithWrongOriginTest() {
        mockPerson();

        UUID personIdFake = UUID.randomUUID();

        specification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_WRONG)
                .setBasePath(String.format("/api/person/v1/%s", personIdFake))
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        Response response = given().spec(specification)
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .extract()
                .response();

        assertEquals(HttpStatus.FORBIDDEN.value(), response.getStatusCode());
    }


    @Test
    @Order(5)
    void findAllPersonsTest() throws IOException {
        mockPerson();

        specification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_LOCALHOST)
                .setBasePath("/api/person/v1")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        Response response = given().spec(specification)
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .extract()
                .response();

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        ResponsePerson[] responsePersonsFind = objectMapper.readValue(response.body().asString(), ResponsePerson[].class);

        assertTrue(responsePersonsFind.length > 0);
    }


    @Test
    @Order(6)
    void findAllPersonsWithWrongOrigin() {
        mockPerson();

        specification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_WRONG)
                .setBasePath("/api/person/v1")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        Response response = given().spec(specification)
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .extract()
                .response();

        assertEquals(HttpStatus.FORBIDDEN.value(), response.getStatusCode());
    }


    @Test
    @Order(7)
    void deletePersonByIdTest() {
        mockPerson();

        specification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_LOCALHOST)
                .setBasePath("/api/person/v1")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        Response response = given().spec(specification)
                .accept(ContentType.JSON)
                .pathParam("personId", responsePerson.getId())
                .when()
                .delete("{personId}")
                .then()
                .extract()
                .response();

        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatusCode());
    }

    private void mockPerson() {
        String password = faker.internet().password();
        personFake.setFirstname(faker.name().firstName());
        personFake.setLastname(faker.name().lastName());
        personFake.setEmail(faker.internet().emailAddress());
        personFake.setPassword(password);
        personFake.setPasswordConfirmation(password);
        personFake.setBirthday(faker.date().birthday());
        personFake.setUsername(faker.name().username());
    }
}