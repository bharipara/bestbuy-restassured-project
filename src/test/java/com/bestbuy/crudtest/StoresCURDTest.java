package com.bestbuy.crudtest;

import com.bestbuy.model.model.StorePojo;
import com.bestbuy.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StoresCURDTest extends TestBase {


    static String name = "Minnetonka12";
    static String type = "BangBox";
    static String address = "135313 Ridgedale Dr";

    static String address2 = " ";
    static String city = "Hopkins";
    static String state = "MN";
    static String zip = "55355";
    static String hours = "Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9;  Sun: 10-8";

    static double lat = 44.969658;
    static double lng = -93.449539;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        RestAssured.basePath = "/stores";


    }
    @Test
    public void test001() {

        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setHours(hours);
        storePojo.setLat(lat);
        storePojo.setLat(lng);
        Response response = given()
//                .contentType(ContentType.JSON)
                .header("Content-Type","application/json")
                .header("Connection","keep-alive")
                .when()
                .body(storePojo)
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
                .pathParam("id", 8923)
                .get("/{id}");
        response.then().statusCode(200);
//                    .extract()
//                    .path(s1 + name + s2);
//            Assert.assertThat(productMap, hasValue(name));
//                    productId= (int) productMap.get("id");

    }

    @Test
    public void test003() {
        StorePojo storePojo =new StorePojo();
        storePojo.setCity("Watford");
        Response response = given()
                .header("Content-Type", "application/json")
                .pathParam("id", 8923)
                .when()
                .body(storePojo)
                .patch("/{id}");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void test004() {
        given()
                .header("Connection","keep-alive")
                .pathParam("id",8923)
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