package BootCamp.Topic5_NoSQL;

import org.mongodb.morphia.annotations.Property;

/**
 * Teacher Java Object repesentation.
 */
public class Teacher {

	@Property("firstname")
	private String firstName;
	@Property("lastname")
	private String lastName;
	@Property("dateofbirth")
	private String dateOfBirth;
}
