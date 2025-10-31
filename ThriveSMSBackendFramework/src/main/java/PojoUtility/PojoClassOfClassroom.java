package PojoUtility;

public class PojoClassOfClassroom {
	
	private String classroomName;
	private int studentCount;
	
	public PojoClassOfClassroom() {}
	
	public PojoClassOfClassroom(String classroomName, int studentCount) {
		this.classroomName= classroomName;
		this.studentCount= studentCount;
	}

	public String getClassroomName() {
		return classroomName;
	}

	public void setClassroomName(String classroomName) {
		this.classroomName = classroomName;
	}

	public int getStudentCount() {
		return studentCount;
	}

	public void setStudentCount(int studentCount) {
		this.studentCount = studentCount;
	}
	

}
