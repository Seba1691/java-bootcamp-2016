package BootCamp.Topic5_NoSQL;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

/**
 * Student Java Object repesentation. It includes information about himself and a list of its notes in the courses
 */
@Entity("students")
public class Student {

	@Id
	private ObjectId id;
	@Property("firstname")
	private String firstName;
	@Property("lastname")
	private String lastName;
	@Property("registrationnum")
	private int registrationNum;
	@Property("dateofbirth")
	private String dateOfBirth;

	@Embedded
	private Note[] notes;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getRegistrationNum() {
		return registrationNum;
	}

	public void setRegistrationNum(int registrationNum) {
		this.registrationNum = registrationNum;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Note[] getNotas() {
		return notes;
	}

	public void setNotas(Note[] notas) {
		this.notes = notas;
	}
	
}
