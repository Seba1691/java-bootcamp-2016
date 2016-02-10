package BootCamp.Topic5_NoSQL;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
/**
 * Course Java Object repesentation 
 */
@Entity("courses")
public class Course {

	@Id
	private String id = new ObjectId().toString();

	@Property("name")
	private String name;
	
	@Embedded("assigned_teacher")
	private Teacher assignedTeacher;	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Teacher getAssignedTeacher() {
		return assignedTeacher;
	}

	public void setAssignedTeacher(Teacher assignedTeacher) {
		this.assignedTeacher = assignedTeacher;
	}
}
