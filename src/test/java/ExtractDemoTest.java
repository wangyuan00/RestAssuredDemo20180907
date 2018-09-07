import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.*;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class ExtractDemoTest {
    public static String code;
    public static  Response response;
    public static RequestSpecification requestSpecification;
    public static ResponseSpecification responseSpecification;
    @BeforeClass
    public static void beforeTest(){
        useRelaxedHTTPSValidation();
//        RestAssured.proxy("127.0.0.1",7778);
//        默认域名
//        RestAssured.baseURI = "https://xueqiu.com";
//      RequestSpecification 请求Specification
        requestSpecification = new RequestSpecBuilder().build();
        requestSpecification .cookie("u", "2087663213") ;
        requestSpecification .header("User-Agent", "Xueqiu Android 10.2");
        requestSpecification.port(80);

        responseSpecification = new ResponseSpecBuilder().build();
        responseSpecification.statusCode(200);
        responseSpecification.body("error_code",equalTo(20085));

    }
    @Test
//    导出json返回值中的某一个值
    public  void loginXueqiu(){
        code=given()
//                复用上面requestSpecification
                .spec(requestSpecification)
                .header("User-Agent", "Xueqiu Android 10.2")
                .queryParam("_t", "1GENYMOTIONed534efb6ff44bbdae1e9192253f9a1a.2087663213.1523770568272.1523772396164")
                .queryParam("_s", "cdf894")
                .cookie("u", "2087663213")
                .cookie("xq_a_token","2c1b28b8a33f1d4f0474feaa11afd9200952cbd6")
                .formParam("grant_type", "password")
                .formParam("telephone", "15600534760")
                .formParam("password", "e10adc3949ba59abbe56e057f20f883e")
                .formParam("areacode", "86")
                .formParam("captcha", "")
                .formParam("client_id", "JtXbaMn7eP")
                .formParam("client_secret", "txsDfr9FphRSPov5oQou74")
                //.formParam("sid", "1GENYMOTIONed534efb6ff44bbdae1e9192253f9a1a")
                .when()
                    .post("/provider/oauth/token")
                .then()
                    .log().all()
                    .statusCode(400)
//               复用上面responseSpecification
                    .spec(responseSpecification)
//                对时间断言
                    .time(lessThan(1000L))
                //.body("error_code", equalTo("20082"))
                .extract()
                    .path("error_code");
        System.out.println(code);
    }
    @Test
//    导出response
    public void responseTest(){
        response = given()
                .header("User-Agent", "Xueqiu Android 10.2")
                .queryParam("_t", "1GENYMOTIONed534efb6ff44bbdae1e9192253f9a1a.2087663213.1523770568272.1523772396164")
                .queryParam("_s", "cdf894")
                .cookie("u", "2087663213")
                .cookie("xq_a_token","2c1b28b8a33f1d4f0474feaa11afd9200952cbd6")
                .formParam("grant_type", "password")
                .formParam("telephone", "15600534760")
                .formParam("password", "e10adc3949ba59abbe56e057f20f883e")
                .formParam("areacode", "86")
                .formParam("captcha", "")
                .formParam("client_id", "JtXbaMn7eP")
                .formParam("client_secret", "txsDfr9FphRSPov5oQou74")
                //.formParam("sid", "1GENYMOTIONed534efb6ff44bbdae1e9192253f9a1a")
                .when()
                    .post("/provider/oauth/token")
                .then()
                    .log().all()
                    .statusCode(400)
                .extract()
                    .response();
        System.out.println(response);
    }

    @Test
//    模拟发送json请求
    public void testPostJson(){
        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("a",1);
        map.put("b","testerhome");
        map.put("array",new String[] {"111","222"});
        given()
                .contentType(ContentType.JSON)
                .body(map)
        .when()
                .post("http://www.baidu.com")
        .then()
                .log().all()
                .statusCode(302);



    }
}
