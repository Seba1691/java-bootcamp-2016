package BootCamp.Topic6_REST.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * 
 * This class represents all the user’s information.
 *
 */
public class User {

	private String username = null;
	private String password = null;
	private String firstName = null;
	private String lastName = null;
	private String email = null;
	
	public User(){
		
	}
	
	public User(String username, String password, String firstName, String lastName, String email) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	@JsonProperty("username")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@JsonProperty("firstName")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@JsonProperty("lastName")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@JsonProperty("email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonProperty("password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		User user = (User) o;
		return Objects.equals(username, user.username) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(email, user.email) && Objects.equals(password, user.password);
	}

	@Override
	public int hashCode() {
		return Objects.hash(username, firstName, lastName, email, password);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class User {\n");

		sb.append("  username: ").append(username).append("\n");
		sb.append("  firstName: ").append(firstName).append("\n");
		sb.append("  lastName: ").append(lastName).append("\n");
		sb.append("  email: ").append(email).append("\n");
		sb.append("  password: ").append(password).append("\n");
		sb.append("}\n");
		return sb.toString();
	}
}
