import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;

public class TestClassSecond {
    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3000;
    }
    @Test
    public void queryParamExampleById() {
        String idToGet = "2";
        String expectedTitle  = "With the Beatles";

        given().
                queryParam("id", idToGet).
                when().
                get("/albums").
                then().
                assertThat().
                body("title[0]", equalToIgnoringCase(expectedTitle));
    }

    @Test
    public void queryParamExampleById2() {
        int expectedId = 2;
        String titleToGet = "With the Beatles";
        given().
                queryParam("title", titleToGet).
                when().
                get("/albums").
                then().
                assertThat().
                body("id[0]", equalTo(expectedId));
    }

    @Test
    public void queryParamExampleByYear() {
        String expectedYear = "1965";
        int idToGet = 5;

        given().
                queryParam("id", idToGet).
                when().
                get("/albums").
                then().
                assertThat().
                body("year[0]", equalTo(expectedYear));
    }

    @Test
    public void queryParamExampleByTitle2() {
        String expectedTitle = "Help!";
        String yearToGet = "1965";

        given().
                queryParam("year", yearToGet).
                when().
                get("/albums").
                then().
                assertThat().
                body("title[0]", equalToIgnoringCase(expectedTitle));
    }
}
