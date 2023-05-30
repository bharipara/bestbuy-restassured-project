package com.bestbuy.crudtest;

import com.bestbuy.model.model.ProductPojo;
import com.bestbuy.model.utils.TestUtils;
import com.bestbuy.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ProductsCRUDTest extends TestBase {
    static String name = "Duracell - AAA Batteries (10-Pack)"  + TestUtils.getRandomValue();;
    static String type = "HardGood";
    static double price = 15.99;
    static int shipping = 0;
    static String upc = "041333424007";
    static String description = "Compatible with select electronic devices; AAA size; DURALOCK Power Preserve technology; 10-pack";
    static String manufacturer = "Duracell";
    static String model = "MN2400B4A";
    static String url = "http://www.bestbuy.com/site/duracell-aaa-batteries-10-pack/43900.p?id=1051384074145%26skuId=43900%26cmp=RMXCC";
    static String image = "http://img.bbystatic.com/BestBuy_US/images/products/4390/43900_sa.jpg";
    static int productId ;

    @Test
    public void test001() {

        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setShipping(shipping);
        productPojo.setUpc(upc);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);
        Response response = given()
//                .contentType(ContentType.JSON)
                .header("Content-Type","application/json")
                .header("Connection","keep-alive")
                .when()
                .body(productPojo)
                .post();
        response.then().log().all().statusCode(201);

    }

    @Test
    public void test002() {
        String s1 = "findAll{it.name == '";
        String s2 = "'}.get(0)";
        Response response= given()
//            HashMap<String, Object> productMap = given()
                .when()
                .pathParam("id", 9999680)
                .get("/{id}");
        response.then().statusCode(200);
//                    .extract()
//                    .path(s1 + name + s2);
//            Assert.assertThat(productMap, hasValue(name));
//                    productId= (int) productMap.get("id");

    }

    @Test
    public void test003() {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setPrice(19.99);
        Response response = given()
                .header("Content-Type", "application/json")
                .pathParam("id", 9999680)
                .when()
                .body(productPojo)
                .patch("/{id}");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void test004() {
        given()
                .header("Connection","keep-alive")
                .pathParam("id",9999680)
                .when()
                .delete("/{id}")
                .then()
                .statusCode(200);

//        given()
//                .pathParam("id",9999691 )
//                .when()
//                .get("/{id}")
//                .then().statusCode(404);

    }
}
