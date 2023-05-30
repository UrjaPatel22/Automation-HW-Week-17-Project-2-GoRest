package com.gorest.crudtest;

import com.gorest.model.UserPojo;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest extends TestBase {

    static String name = "testprime" + TestUtils.getRandomValue();
    static String gender = "male";
    static String email =  TestUtils.getRandomValue() + "testprime@gmail.com" ;

    static String status = "active";

@Test
    public void verifyUserCreatedSuccessfully(){
    UserPojo userPojo = new UserPojo();
    userPojo.setName(name);
    userPojo.setGender(gender);
    userPojo.setEmail(email);
    userPojo.setStatus(status);

    Response response=given()
            .header("Content-Type","application.json")
            .header("Authorization","Bearer 61aafcb694ad2a184e92adcee5aae71f2288cf87d88930daf6a8b86ae21da215")
            .contentType(ContentType.JSON)
            .body(userPojo)
            .when()
            .post();
    response.then().log().all().statusCode(201);
}

    @Test
    public void getUser() {
        Response response = given()
                .header("Content-Type","application/json")
                .header("Access","application/json")
                .header("Authorization","Bearer 61aafcb694ad2a184e92adcee5aae71f2288cf87d88930daf6a8b86ae21da215")
                .when()
                .get("/2331163");
        response.then().statusCode(200);
        response.prettyPrint();

    }


@Test
    public void verifyUserUpdateSuccessfully(){
    UserPojo userPojo = new UserPojo();
    userPojo.setName("John" + name + "Smith");
    userPojo.setGender("Female");
    userPojo.setEmail(email);
    userPojo.setStatus(status);

    Response response=given()
            .header("Content-Type","application.json")
            .header("Authorization","Bearer 61aafcb694ad2a184e92adcee5aae71f2288cf87d88930daf6a8b86ae21da215")
            .contentType(ContentType.JSON)
            .body(userPojo)
            .when()
            .put("/2331163");
    response.then().log().all().statusCode(200);
}
    @Test
    public void verifyUserDeleteSuccessfully() {
        String token ="61aafcb694ad2a184e92adcee5aae71f2288cf87d88930daf6a8b86ae21da215";
        Response response = given()
                .header("Content-Type","application/json")
                .header("Access","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .delete("/2331163");
        response.then().log().all().statusCode(204);


    }




}
