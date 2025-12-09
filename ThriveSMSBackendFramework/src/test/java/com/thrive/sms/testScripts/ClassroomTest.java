package com.thrive.sms.testScripts;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.thrive.sms.auth.AuthUtility;

import static io.restassured.RestAssured.*;
import PojoUtility.PojoClassOfClassroom;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ClassroomTest {
	
	String token =  AuthUtility.getToken();
	PojoClassOfClassroom pObj;
	String classrommId= "";
	String classroomName= "Class_XX";
	int studentCount = 45;
	
	@Test
	public void createClassroomTest() {

		pObj = new PojoClassOfClassroom(classroomName, studentCount);

		Response resp=  given()
				.auth().oauth2(token)
				.contentType(ContentType.JSON)
				.body(pObj)
				.when()
				.post("http://49.249.28.218:8081/AppServer/Student_Management_System/view/login.php/admin/classroom");
				resp.then()
				.assertThat().statusCode(201)
				.assertThat().contentType(ContentType.JSON)
				.assertThat().time(Matchers.lessThan(1200L))
				.log().all();

		String actClassrommId= resp.jsonPath().get(".classroomName");
		String actStudentCount= resp.jsonPath().get(".studentCount");
		classrommId= resp.jsonPath().get(".classrommId");
		
		Assert.assertEquals(classroomName, actClassrommId);
		Assert.assertEquals(actStudentCount, studentCount);
	}

	@Test(dependsOnMethods = "createClassroomTest()")
	public void getClassroomTest() {
		
		Response resp = given()
				.auth().oauth2(token)
 				.when()
				.get("http://49.249.28.218:8081/AppServer/Student_Management_System/view/login.php/admin/classroom/classroomId");
				resp.then()
				.assertThat().statusCode(200)
				.contentType(ContentType.JSON)
				.assertThat().time(Matchers.lessThan(800L))
				.log().all();

				String actClassrommId= resp.jsonPath().get(".classroomName");
				String actStudentCount= resp.jsonPath().get(".studentCount");
				classrommId= resp.jsonPath().get(".classrommId");
				
				Assert.assertEquals(classroomName, actClassrommId);
				Assert.assertEquals(actStudentCount, studentCount);
	}
	
	@Test(dependsOnMethods = "createClassroomTest()")
	public void updateClassroomTest() {
		
		int UpdatedStudentCount = 415;
		pObj.setStudentCount(UpdatedStudentCount);
		Response resp = given()
				.auth().oauth2(token)
				.contentType(ContentType.JSON)
				.body(pObj)
				.when()
				.patch("http://49.249.28.218:8081/AppServer/Student_Management_System/view/login.php/admin/classroom/classroomId");
				resp.then()
				.assertThat().statusCode(200)
				.contentType(ContentType.JSON)
				.assertThat().time(Matchers.lessThan(800L))
				.log().all();

				String actClassrommId= resp.jsonPath().get(".classroomName");
				String actStudentCount= resp.jsonPath().get(".studentCount");
				classrommId= resp.jsonPath().get(".classrommId");
				
				Assert.assertEquals(classroomName, actClassrommId);
				Assert.assertEquals(actStudentCount, studentCount);
	}
	
	@Test(dependsOnMethods  = "updateClassroomTest()")
	public void deleteClassroomTest() {
		Response resp = given()
				.auth().oauth2(token)
				.when()
				.delete("http://49.249.28.218:8081/AppServer/Student_Management_System/view/login.php/admin/admin/classroom/classroomId");
				resp.then()
				.assertThat().statusCode(204)
				.assertThat().time(Matchers.lessThan(500L))
				.log().all();
	}
}

