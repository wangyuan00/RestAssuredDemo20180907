import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.matcher.RestAssuredMatchers.*;

public class JsonDemoxmlDemoTest {
    @Test
    public void DemoJson(){
//      默认是由XML解析器处理，需要在发出请求前明确告诉RestAssured用此解析器
        RestAssured.registerParser("application/octet-stream", Parser.JSON);
        given()
         .when()
                .get("http://127.0.0.1:8080/jsonDemo1.json")
         .then()
//                .log().all()
//                .body("store.book.category",hasItems("reference"))
//                .body("store.book[0].author",equalTo("Nigel Rees"))
//                .body("store.bicycle.color",equalTo("red"))
//                .body("store.book.findAll { book -> book.price >= 5 && book.price <= 15}",equalTo(""))
             /*
             *  find 结果是一个值
             *  findAll 结果是数组
             *  浮点数后面要加f，如8.95f
             * */
                .body("store.book.find { book -> book.price == 8.95f}.price",equalTo(8.95f))

        ;
    }

    @Test
    public void DemoXml(){
        given()
        .when()
                .get("http://127.0.0.1:8080/xmlDemo.xml")
        .then()
            .log().all()
            .body("shopping.category[0].item[0].name",equalTo("Chocolate"))
            .body("shopping.category.item.size()",equalTo(5))
//          属性需要加@
            .body("shopping.category.findAll { it.@type == 'groceries' }.size()",equalTo(1))
            .body("shopping.category.item.findAll {it.price == 10}.name",equalTo("Chocolate"))
//           xml 支持 **， json 不支持
            .body("**.findAll {it.price == 10}.name",equalTo("Chocolate"))
        ;


    }
}
