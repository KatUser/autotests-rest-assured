import org.junit.Test;

import static io.restassured.RestAssured.given;


public class TestClass {

    @Test
    public void checkEmployeeStatusIs200() {
        given().when().get("http://dummy.restapiexample.com/api/v1/employees")
                .then().assertThat().statusCode(200);
    }

    @Test
    public void checkEmployeeStatusIs404() {
        given().when().get("http://dummy.restapiexample.com/api/v1/employees-invalid")
                .then().assertThat().statusCode(404);
    }

}
