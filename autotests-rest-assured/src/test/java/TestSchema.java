import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class TestSchema {
    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3000;
    }
    @Test
    public void checkAlbumSchema() {
        given().
                when().
                get("albums/2").
                then().
               // log().ifValidationFails().
                assertThat().
                and().
                contentType(ContentType.JSON).
                body(matchesJsonSchemaInClasspath("album-schema.json"));

    }
}
