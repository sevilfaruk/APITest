import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import java.util.HashMap;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

public class ApiTest {


    @Test
    public void GetAllGrocery()
    {
        RestAssured.baseURI = "http://localhost:3000/posts";
        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.request(Method.GET);

        String responseBody = response.getBody().asString();
        System.out.println("GetAllGrocery : Response GET Body is =>  " + responseBody);
        Integer statusCode=response.getStatusCode();
        System.out.println("GetAllGrocery : Response GET Status Code =>  " +statusCode);

        Assert.assertEquals(200, (int) statusCode);

    }

    @Test
    public void GetDataWithName(){
        RestAssured.baseURI = "http://localhost:3000/posts";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET);

        given()
                .contentType(ContentType.JSON).
                with()
                .pathParams("post", 1).
                when()
                .get("http://localhost:3000/posts/{post}").
                then()
                .body("name", containsString("apple"));

        Integer statusCode=response.getStatusCode();
        System.out.println("GetAllGrocery : Response GET Status Code =>  " +statusCode);

    }
    @Test
    public void PostData(){

        HashMap<String,String> postContent = new HashMap<>();
        postContent.put("id", "4");
        postContent.put("name", "banana");
        postContent.put("price", "12.3");
        postContent.put("stock", "3");

        given()
                .contentType(ContentType.JSON).
                with()
                .body(postContent).
                when()
                .post("http://localhost:3000/posts").
                then()
                .body("name", Is.is("banana"));

    }

    @Test
    public void CheckStatus400(){
        String requestBadBody = "{\n" +
                "  \"posts\": {\n" +
                "    \"id\": \"123\",\n" +
                "    \"name\": \"Hepsiburada\",\n" +
                "    \"price\": \"string\",\n" +
                "    \"stock\": \"string\"\n" +
                "  },\n" ;

        RestAssured.baseURI = "http://localhost:3000/posts";
        Response response = null;

        try {
            response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(requestBadBody)
                    .post("/java");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String responseBody = response.getBody().asString();
        System.out.println("Response POST Body is =>  " + responseBody);

        Integer statusCode=response.getStatusCode();
        System.out.println("Response POST Status Code =>  " +statusCode);

       // Assert.assertEquals(200, (int) statusCode);

    }
}
