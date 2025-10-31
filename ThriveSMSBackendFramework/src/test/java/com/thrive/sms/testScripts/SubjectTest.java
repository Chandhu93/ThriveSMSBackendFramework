package com.thrive.sms.testScripts;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.thrive.sms.auth.AuthUtility;

import static io.restassured.RestAssured.*;
import PojoUtility.PojoClassOfSubject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class SubjectTest {
	String token =  AuthUtility.getToken();
	PojoClassOfSubject pObj;
	String subjectName= "API_Testing";
	static String subjectId="";
	
	@Test
	public void createSubjectTest() {

		pObj = new PojoClassOfSubject(subjectName);

		Response resp=  given()
				.auth().oauth2(token)
				.contentType(ContentType.JSON)
				.body(pObj)
		.when()
		.post("http://49.249.28.218:8081/AppServer/Student_Management_System/view/login.php/admin/subject");
		resp.then()
		.assertThat().statusCode(201)
		.assertThat().time(Matchers.lessThan(1200L))
		.log().all();

		String actSubjectName= resp.jsonPath().get("subjectName");
		 subjectId= resp.jsonPath().get("subjectId");
		
		Assert.assertEquals(subjectName, actSubjectName);
	}

	@Test(dependsOnMethods = "createSubjectTest()")
	public void getSubjectTest() {
		
		Response resp = given()
				.auth().oauth2(token)
				.when()
				.get("http://49.249.28.218:8081/AppServer/Student_Management_System/view/login.php/admin/subject/"+subjectId);
				resp.then()
				.assertThat().statusCode(201)
				.assertThat().time(Matchers.lessThan(800L))
				.log().all();

				String actSubjectName= resp.jsonPath().get("subjectName");
				String actSubjectId= resp.jsonPath().get("subjectId");
				
				Assert.assertEquals(subjectName, actSubjectName);
				Assert.assertEquals(subjectId, actSubjectId);
		
	}
	
	@Test(dependsOnMethods = "getSubjectTest()")
	public void updateSubjectTest() {
		
		String updateSubjectName= "RESTAssured_Testing";
		pObj.setSubjectName(updateSubjectName);
		
		Response resp = given()
				.auth().oauth2(token)
				.contentType(ContentType.JSON)
				.body(pObj)
				.when()
				.patch("http://49.249.28.218:8081/AppServer/Student_Management_System/view/login.php/admin/subjectId/"+subjectId);
				resp.then()
				.assertThat().statusCode(200)
				.assertThat().time(Matchers.lessThan(800L))
				.log().all();

				String actSubjectName= resp.jsonPath().get("subjectName");
				String actSubjectId= resp.jsonPath().get("subjectId");
				
				Assert.assertEquals(subjectName, actSubjectName);
				Assert.assertEquals(subjectId, actSubjectId);
	}
	
	@Test(dependsOnMethods = "updateSubjectTest()")
	public void deleteClassroomTest() {
		
		Response resp = given()
				.auth().oauth2(token)
				.when()
				.delete("http://49.249.28.218:8081/AppServer/Student_Management_System/view/login.php/admin/admin/subject/"+subjectId);
				resp.then()
				.assertThat().statusCode(204)
				.assertThat().time(Matchers.lessThan(500L))
				.log().all();

	}
}










