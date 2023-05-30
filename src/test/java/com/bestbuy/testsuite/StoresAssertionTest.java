package com.bestbuy.testsuite;

import com.bestbuy.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.*;

public class StoresAssertionTest extends TestBase {

    static ValidatableResponse response;
    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;

        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
        // response.log().all();
    }

    @Test
     public void test001(){

         //Verify the if the total is equal to 1561

         response.body("total",equalTo(1561));
     }

     @Test

    public void test002(){
         // Verify the if the stores of limit is equal to 10
         response.body("limit",equalTo(10));
     }

     @Test
    public void test003(){
         // Check the single ‘Name’ in the Array list (Inver Grove Heights)
         response.body("data.name",hasItem("Inver Grove Heights"));
     }

     @Test
    public void test004(){
        // Check the multiple ‘Names’ in the ArrayList (Roseville, Burnsville, Maplewood)
        response.body("data.name",hasItems("Roseville", "Burnsville", "Maplewood"));
    }


    @Test
    public void test005() {
     // Verify the storied=7 inside storeservices of the third store of second services
       response.body("data[2].services[2]",hasKey("storeservices"));

    }

    @Test
    public void test006() {
    // Check hash map values ‘createdAt’ inside storeservices map where store name = Roseville
        response.body("data.findAll{it.name == 'Roseville'}",hasKey(hasEntry("storeservices","createdAt")));
    }

    @Test
    public void test007() {
    // Verify the state = MN of forth store
        response.body("data[3].state",equalTo("MN"));

    }

    @Test
    public void test008(){
        // Verify the store name = Rochester of 9th store
    response.body("data[8].name",equalTo("Rochester"));
    }

    @Test
    public void test009() {
// Verify the storeId = 11 for the 6th store
        //response.body("data[5].id",equalTo(11));
       response.body("data[5].services.storeservices.storeId",hasItems(11));
        }

    @Test
    public void test010(){
        // Verify the serviceId = 4 for the 7th store of forth service
        response.body("data[6].services[3].storeservices.serviceId", equalTo(4));
    }

}
