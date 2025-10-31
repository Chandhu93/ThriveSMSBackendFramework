package com.thrive.sms.testScripts;

import static io.restassured.RestAssured.given;

import java.util.Random;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.thrive.sms.auth.AuthUtility;

import PojoUtility.PojoClassOfStudent;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StudentTest {
	
	PojoClassOfStudent pObj;
	String token =  AuthUtility.getToken();
	
	String fullName= "amar";
	String add="Bengaluru";
	String email="amar@gmal.com";
	String phone="987543210" ;
	String dateOfBirth="27/7/1947";
	String gender="Male";
	
	static String studentId="";
			
	@Test
	public void  createStudent() {
		
		Random r = new Random();
		int randNum = r.nextInt(500);
		
		pObj = new PojoClassOfStudent(fullName+randNum, add, email, phone, dateOfBirth, gender);
		
		Response resp=  given()
				.auth().oauth2(token)
				.contentType(ContentType.JSON)
				.body(pObj)
		.when()
		.post("http://49.249.28.218:8081/AppServer/Student_Management_System/view/login.php/admin/student");
		resp.then()
		.assertThat().statusCode(201)
		.assertThat().time(Matchers.lessThan(1200L))
		.log().all();

		String actFullName= resp.jsonPath().get("fullName");
		 studentId= resp.jsonPath().get("subjectId");
		
		Assert.assertEquals(fullName, actFullName);
	}

	@Test(dependsOnMethods = "createStudent()")
	public void deleteStudent() {
	Response resp = given()
			.auth().oauth2(token)
			.when()
			.delete("http://49.249.28.218:8081/AppServer/Student_Management_System/view/login.php/admin/admin/subject/"+studentId);
			resp.then()
			.assertThat().statusCode(204)
			.assertThat().time(Matchers.lessThan(500L))
			.log().all();
	
}
}









