package com.bestbuy.testsuite;

import com.bestbuy.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest extends TestBase {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);

    }

    //    1. Extract the limit
    @Test
    public void test001() {
        int limit = response.extract().path("limit");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");

    }


//2. Extract the total
    @Test
    public void test002() {
        int total = response.extract().path("total");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total is : " + total);
        System.out.println("------------------End of Test---------------------------");

    }
//3. Extract the name of 5th store
    @Test
    public void test003() {
       String name = response.extract().path("data[5].name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of the 5th store : " + name);
        System.out.println("------------------End of Test---------------------------");

    }
//4. Extract the names of all the store
    @Test
    public void test004() {
       List<String> storeName = response.extract().path("data.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of all stores : " + storeName);
        System.out.println("------------------End of Test---------------------------");

    }
//5. Extract the storeId of all the store
    @Test
    public void test005() {
        List<Integer> storeId = response.extract().path("data.storeId");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The storeId of all the store : " + storeId);
        System.out.println("------------------End of Test---------------------------");

    }

//6. Print the size of the data list
    @Test
    public void test006() {
        List<String> dataSize = response.extract().path("data");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The size of the data is : " + dataSize.size());
        System.out.println("------------------End of Test---------------------------");

    }
//7. Get all the value of the store where store name = St Cloud
    @Test
    public void test007() {
        List<HashMap<String, ?>> values  = response.extract().path("data.findAll{it.name == 'St Cloud'}");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of the store name St Cloud are : " + values);
        System.out.println("------------------End of Test---------------------------");

    }
//8. Get the address of the store where store name = Rochester
    @Test
    public void test008() {
        String address = response.extract().path("data[8].address");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The address of the store Rochester is : " + address);
        System.out.println("------------------End of Test---------------------------");

    }
//9. Get all the services of 8th store
    @Test
    public void test009() {
      List<String> servicesOfStore = response.extract().path("data.services[8]");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The services of 8th store are : " + servicesOfStore);
        System.out.println("------------------End of Test---------------------------");

    }
//10. Get storeservices of the store where service name = Windows Store
    @Test
    public void test010() {

        List<HashMap<String, ?>> serviceName = response.extract().path("data.find{it.services}.services.findAll{it.name ='Windows Store'}.storeservices");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The storeservices of the Window store is : " + serviceName);
        System.out.println("------------------End of Test---------------------------");

    }
//11. Get all the storeId of all the store
    @Test
    public void test011() {

            List<Integer> storeIds =   response.extract().path("data.services.storeservices.storeId");

            System.out.println("------------------StartingTest---------------------------");
            System.out.println("List Of store Id is: "+storeIds);
            System.out.println("------------------End of Test---------------------------");

        }

//12. Get id of all the store
    @Test
    public void test012() {
        List<Integer> listOfIds = response.extract().path("data.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + listOfIds);
        System.out.println("------------------End of Test---------------------------");

    }
//13. Find the store names Where state = ND
    @Test
    public void test013() {
        String stateND = response.extract().path("data[7].state");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The store name where state is : " + stateND);
        System.out.println("------------------End of Test---------------------------");

    }
//14. Find the Total number of services for the store where store name = Rochester
    @Test
    public void test014() {
        List<HashMap<String,?>> listOfServices = response.extract().path("data.findAll{it.name == 'Rochester'}.services");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The number of services for the store where store name Rochester are : " + listOfServices);
        System.out.println("------------------End of Test---------------------------");

    }
//15. Find the createdAt for all services whose name = “
//    Windows Store”
    @Test
    public void test015() {
        int limit = response.extract().path("limit");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");

    }
//            16. Find the name of all services Where store name = “Fargo”
    @Test
    public void test016() {

        List<HashMap<String, ?>> servicesFargo =   response.extract().path("data.findAll{it.name == 'Fargo'}.services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of all services where store name Fargo : " + servicesFargo);
        System.out.println("------------------End of Test---------------------------");

    }
//            17. Find the zip of all the store
    @Test
    public void test017() {
       List<Integer>  listOfZip = response.extract().path("data.zip");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The zip of all the store are : " + listOfZip);
        System.out.println("------------------End of Test---------------------------");

    }
//18. Find the zip of store name = Roseville
    @Test
    public void test018() {
        //long zipOfStore = response.extract().path("data[2].zip");
        List<Integer> zipOfStore = response.extract().path("data[2].zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The zip of the store name Roseville is : " + zipOfStore);
        System.out.println("------------------End of Test---------------------------");

    }
//19. Find the storeservices details of the service name = Magnolia Home Theater
    @Test
    public void test019() {
        List<HashMap<String, ?>> servicesMagnolia =   response.extract().path("data.findAll{it.name == 'Magnolia Home Theater'}.storeservices");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The storeservices of Magnolia Home Theater are : " + servicesMagnolia);
        System.out.println("------------------End of Test---------------------------");

    }
//20. Find the lat of all the stores
    @Test
    public void test020() {
       List<Integer> latOfStore = response.extract().path("data.lat");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The lat of all the stores : " + latOfStore);
        System.out.println("------------------End of Test---------------------------");

    }
}
