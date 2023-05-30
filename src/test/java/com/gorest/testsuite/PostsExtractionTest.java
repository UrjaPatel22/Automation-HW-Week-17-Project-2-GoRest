package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest {


    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()

                .when()
                .get("/posts?page=1&per_page=25")
                .then().statusCode(200);

    }
//    1. Extract the title

    @Test
    public void test001(){
        List<String> title=response.extract().path("title");
        System.out.println("The Value of title is " +title);
    }
//2. Extract the total number of record
@Test
public void test002(){
    List<String> id=response.extract().path("id");
    int records=id.size();
    System.out.println("Total Number of Record is " +records);
}


//3. Extract the body of 15th record

    @Test
    public void test003(){
        String body=response.extract().path("body[14]");
        System.out.println("The body record of 15 is " +body);
    }




//4. Extract the user_id of all the records

    @Test
    public void test004(){
        List<Integer> user_id=response.extract().path("user_id");
        System.out.println("user_id of all the records " +user_id);
    }

//5. Extract the title of all the records
@Test
public void test005(){
    List<String> title=response.extract().path("title");
    System.out.println("The title of all the records " +title);
}
//6. Extract the title of all records whose user_id = 5456
@Test
public void test006(){
    String title = response.extract().path("find{it.user_id==2223279}.title");
    System.out.println("the title of all records whose user_id==2223279 " +title);
}


//7. Extract the body of all records whose id = 2671
@Test
public void test007(){
   String body = response.extract().path("find{it.id==38825}.body");
    System.out.println("the body of all records whose id ==38825 " +body);
}



}
