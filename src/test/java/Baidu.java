import org.junit.Test;
import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Baidu {
   @Test
   /*简单请求http://www.baidu.com*/
    public void testGetHtml(){
       given()
               .log().all().get("http://www.baidu.com")
              .then().log().all().statusCode(200);

   }

   @Test
    public void TestMp3(){
        given()
                .queryParam("wd","mp3")
        .when()
                .get("http://www.baidu.com/s")
        .then()
                .log().all()
                .statusCode(200)
//                对网页断言
                .body("html.head.title",equalTo("mp3_百度搜索"))
        ;



   }

}
