package dayx;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class JacksonLib {

    @DisplayName("Testing GET /api/Spartans endpoint")
    @Test
    public void testAllSpartans(){

        // given part -- RequestSpecification
        // you can add information like header, query param, path var, body
        // if this request need authentication , it also goes to give section
        // when part --- Send Request(GET POST PUT DELETE)
        //                  --Get response
        // then part -- ValidatableResponse
        // this is where assertions start
        // you can chain multiple assertions
        // if any assertions fail , whole test fail.

        Map<String,String> bodyMap = new HashMap<>();
        bodyMap.put("name","Adx");
        bodyMap.put("gender","Male");
        bodyMap.put("phone","1231231231");

        File jsonFile = new File("spartan.json");


        System.out.println(given()  // add all your request specific information like header, query param, path var, body
                .log().all()
                .contentType(ContentType.JSON).
                        body(jsonFile).
                        when()
                .post("http://54.174.216.245:8000/api/spartans").
                        prettyPeek().
                        then()
                .statusCode(is(201))
                .extract()
                .jsonPath().getObject("data", Spartan.class));

        ;

    }


}
