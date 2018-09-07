import org.junit.Test;
import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasItem;


public class SearchTest {
    @Test
    public void test01(){
//        信任的代理
//        useRelaxedHTTPSValidation();
        given()
//                .proxy("127.0.0.1",7778)
                .queryParam("keyWords","火龙果")
                .queryParam("showNum","6")
                .queryParam("page","1")
        .when()
                .get("https://check-h5.wanguowang.com/wap/search/searchInfo")
        .then()
                .log().all()
                .statusCode(200)
                .body("searchInfo.title",hasItem("仙蜜红·火龙果树"))
                .body("searchInfo.labelCode",hasItem("gold_bowl"))
                .body("searchInfo.find {it.goodsTimeId == 268}.time",equalTo(19))
               ;
    }


}
