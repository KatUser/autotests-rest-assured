import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class TestAllAlbumsSchema {
    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3000;
    }
    @Test
    public void checkAllAlbumsSchema() {
        given().
                when().
                get("/albums").
                then().
                // log().ifValidationFails().
                assertThat().
                statusCode(200).
                and().
                contentType(ContentType.JSON).
                body(matchesJsonSchemaInClasspath("all-albums-schema.json"));
    }
}
