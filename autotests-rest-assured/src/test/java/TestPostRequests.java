import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.json.simple.JSONObject;

import javax.mail.Header;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

public class TestPostRequests {
    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3000;
    }
    @Test
    public void postNewAlbum1() {
      //  Header acceptJson = new Header("Accept", "application/json");
        JSONObject requestParams = new JSONObject();
        requestParams.put("artist", "Queen");
        requestParams.put("title", "Show must go on");
        requestParams.put("year", "1988");

        Response response =
                given().
                        contentType(ContentType.JSON).
                        body(requestParams.toString()).
                        when().
                        post("/albums").
                        then().
                        statusCode(201).
                        body("$", hasKey("id")).
                        body("title", equalTo("Show must go on")).
                        body("year", equalTo("1988")).
                        extract().response();
        given().
                contentType(ContentType.JSON).
                body(requestParams.toString()).
                when().
                delete("/albums/" + response.jsonPath().getInt("id")).
                then().
                statusCode(200);

        //try to get the album we just deleted
        given().
                when().
                get("/albums/" + response.jsonPath().getInt("id")).
                then().
                statusCode(404);
    }

    @Test
    public void postNewAlbum2() {
        JSONObject requestParam = new JSONObject();
        requestParam.put("artist", "ACDC");
        requestParam.put("year", "1980");
        requestParam.put("title", "Hell");

        Response response =
                given().
                        contentType(ContentType.JSON).
                        body(requestParam.toString()).
                        when().
                        post("/albums").
                        then().
                        statusCode(201).
                        body("$", hasKey("id")).
                        body("artist", equalTo("ACDC")).
                        body("year", equalTo("1980")).
                        body("title", equalTo("Hell")).
                        extract().response();

    }
}
