package BootCamp.Topic4_SQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HighSchool {

	private Connection dbConnection;

	public HighSchool(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}

	public void setConnection(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}

	public void listStudents(int idCourse) {
		String query = "SELECT C.name as 'Course', CONCAT(PT.LastName,', ',PT.FirstName) as 'Teacher', GROUP_CONCAT(PS.LastName,', ',PS.FirstName SEPARATOR '\n') as 'Students' " + "FROM    Courses as C JOIN Teachers as T ON (C.idteacher = T.idperson)"
				+ "   JOIN Persons as PT ON (T.idperson = PT.idperson)" + "   JOIN Assistants as A ON (C.idcourse = A.idcourse)" + "   JOIN Students as S ON (A.idstudent = S.idperson)" + "   JOIN Persons as PS ON (S.idperson = PS.idperson)" + "WHERE C.idcourse = " + idCourse + " "
				+ "ORDER BY PS.LastName;";
		try {
			Statement stmt = dbConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.first();
			System.out.println("Course: " + rs.getString("Course"));
			System.out.println("Teacher: " + rs.getString("Teacher"));
			System.out.println("Students: " + rs.getString("Students"));
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		HighSchool highSchool = new HighSchool(HighSchoolDBConnection.getInstance().getDBConnection());
		highSchool.listStudents(2);
	}
}
