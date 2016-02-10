package BootCamp.Topic5_NoSQL;

import org.mongodb.morphia.annotations.Property;

/**
 * Note Java Object repesentation. It includes the course and the concrete note on this course
 */
public class Note {
	
	@Property("note")
	private double note;
	
	@Property("course_name")
	private String course;

	public double getNote() {
		return note;
	}

	public void setNote(double note) {
		this.note = note;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}
	
}
