import org.junit.validator.PublicClassValidator;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class myFirstRestAssuredClass {

    final static String url="http://demo.guru99.com/V4/sinkministatement.php?CUSTOMER_ID=68195&PASSWORD=1234!&Account_No=1";

    public static void main(String args[]) {

      //  getResponseBody();
      //  getResponseStatus();
      //  getResponseHeaders();
       // getResponseTime();
       // getResponseContentType();
        getSpecificPartOfResponseBody();
    }

    //This will fetch the response body as is and log it. given and when are optional here
    public static void getResponseBody(){
        given().when().get(url).then().log()
                .all();

        given().queryParam("CUSTOMER_ID","68195")
                .queryParam("PASSWORD","1234!")
                .queryParam("Account_No","1") .when().get("http://demo.guru99.com/V4/sinkministatement.php").then().log().body();
    }

    public static void getResponseStatus(){
        int statusCode= given().queryParam("CUSTOMER_ID","68195")
                .queryParam("PASSWORD","1234!")
                .queryParam("Account_No","1")
                .when().get("http://demo.guru99.com/V4/sinkministatement.php").getStatusCode();
        System.out.println("The response status is "+statusCode);

        given().when().get(url).then().assertThat().statusCode(200);
    }

    public static void getResponseHeaders() {
        System.out.println("Response headers are : "
            + get(url).then().extract().headers());

    }

    public static void getResponseTime() {
        System.out.println(get(url).timeIn(MILLISECONDS));
    }

    public static void getResponseContentType() {
        System.out.println(get(url).then().extract().contentType());
    }

    public static void getSpecificPartOfResponseBody() {
        ArrayList<Integer> ages = when().get("https://dummy.restapiexample.com/api/v1/employees")
                .then().extract().path("data.employee_age") ;
        int sumOfAll = 0;
        for (Integer a : ages) {
            sumOfAll += a;
        }

        System.out.println(sumOfAll);
    }
}
