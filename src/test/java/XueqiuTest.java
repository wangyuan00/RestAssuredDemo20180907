import org.junit.Test;
import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;


public class XueqiuTest {
    @Test
    public void test01(){
        given()
                .queryParam("code","sogo")
                .header("Cookie","_ga=GA1.2.495015507.1530520722; device_id=5e973e897242c891a79c3a0ce3f1311b; s=fq11scvjmn; __utma=1.495015507.1530520722.1530599227.1530599227.1; __utmz=1.1530599227.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); aliyungf_tc=AQAAAN0gFVbmqQ0A4o14anMPWnM3eSyD; xq_a_token=584d0cf8d5a5a9809761f2244d8d272bac729ed4; xq_a_token.sig=x0gT9jm6qnwd-ddLu66T3A8KiVA; xq_r_token=98f278457fc4e1e5eb0846e36a7296e642b8138a; xq_r_token.sig=2Uxv_DgYTcCjz7qx4j570JpNHIs; u=751536116364687; _gid=GA1.2.79908254.1536116365; _gat_gtag_UA_16079156_4=1; Hm_lvt_1db88642e346389874251b5a1eded6e3=1536116365; Hm_lpvt_1db88642e346389874251b5a1eded6e3=1536116379")
        .when()
                .get("https://xueqiu.com/stock/search.json")
        .then()
                 .log().all()
                .statusCode(200)
                .body("stocks.name",hasItems("搜狗"))
                .body("stocks.code",hasItems("SOGO"));
    }
}
