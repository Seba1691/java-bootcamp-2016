package BootCamp.Topic5_NoSQL;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;

/**
 * This class connects to MongoDB and performs queries 
 */
public class MongoDAO {

	private Datastore datastore;
	final Morphia morphia = new Morphia();

	public static void main(String[] args) {
		MongoDAO highSchool = new MongoDAO();
		highSchool.connect();
		List<Student> students = highSchool.approvedStudents("Programming");
		List<Course> courses = highSchool.getCoursesByTeacherName("TeacherName1");
	}
	
	/**
	 * Returns Courses ordered by name which are given by a teacher
	 * @param teacherFirstName name of the teacher that gives the course
	 * @return Courses list ordered by name
	 */
	private List<Course> getCoursesByTeacherName(String teacherFirstName) {
		return datastore.createQuery(Course.class).field("assigned_teacher.firstname").equal(teacherFirstName).order("name").asList();
	}
	/**
	 * Returns Students who have passed an course
	 * @param courseName name of the course that you are asking for
	 * @return Approved students list
	 */
	private List<Student> approvedStudents(String courseName) {
		BasicDBObject gt = new BasicDBObject();
		gt.put("$gt", 4);
		BasicDBObject eleMatch = new BasicDBObject();
		eleMatch.put("note", gt);
		eleMatch.put("course_name", courseName);
		
		Query<Student> query = datastore.createQuery(Student.class).filter("notes elem", eleMatch);
		return query.asList();		
	}
	
	/**
	 * Establishes the Mongo Data Base Connection
	 */
	private void connect() {
		morphia.mapPackage("org.mongodb.morphia.example");
		datastore = morphia.createDatastore(new MongoClient(), "high-school");
		datastore.ensureIndexes();
	}
	
//	private void queryStudents() {
//		List<Course> courses = datastore.createQuery(Course.class).asList();
//
//		// List<Student> students2 =
//		// datastore.createQuery(Student.class).field("notas.note").hasThisElement().greaterThan(8)/*.field("notas.course").equal(courses.get(1))*/.asList();
//		// datastore.find(Student.class);
//		Query<Note> q = datastore.createQuery(Note.class).field("course").equal(courses.get(1));
//		Query<Student> q33 = datastore.createQuery(Student.class).filter("notas elem", morphia.toDBObject(q));
//		List<Student> students11 = q33.asList();
//		// q.disableValidation();
//		// q.filter("notas elem",
//		// BasicDBObjectBuilder.start().push("note").add("$gt",
//		// "4").pop()/*.push("course").add("$regex", "DBRef('courses',
//		// '56ab530325d8d4202c5343cb')").pop()*/);
//		// List<Student> students3 = q.asList();
//		Object a = morphia.getMapper().getId(courses.get(1));
//
//		BasicDBObject gt = new BasicDBObject();
//		gt.put("$gt", 4);
//		BasicDBObject courseRef = new BasicDBObject();
//		// courseRef.put("$ref", "courses");
//		courseRef.put("$id", courses.get(1).getId());
//		BasicDBObject eleMatch = new BasicDBObject();
//		eleMatch.put("note", gt);
//		eleMatch.put("course", courseRef);
//		BasicDBObject up = new BasicDBObject();
//		up.put("$elemMatch", eleMatch);
//		BasicDBObject retrievedField = new BasicDBObject();
//		retrievedField.append("notas", up);
//
//		DBCollection dbcoll = datastore.getCollection(Student.class);
//		// DBObject object = dbcoll.findOne(retrievedField);
//		Query<Student> q2 = datastore.createQuery(Student.class).filter("notas", up);
//		List<Student> students5 = q2.asList();
//
//		/*
//		 * filter("rs elem", BasicDBObjectBuilder.start("_id",
//		 * objId).push("I").add("$nin",
//		 * Collections.singletonList(127)).pop().get());
//		 */
//		List<Student> students4 = datastore.createQuery(Student.class).filter("notas elem", eleMatch).asList();
//
//		// System.out.println(students);
//	}	
	

//	private void insert() {
//		Course c = new Course();
//		c.setName("Maths");
//		Course c2 = new Course();
//		c2.setName("Literature");
//		Course c3 = new Course();
//		c3.setName("Science");
//
//		Student s = new Student();
//		s.setFirstName("pepe");
//		s.setLastName("tito");
//		Note[] notas = new Note[3];
//		Note n = new Note();
//		n.setCourse(c);
//		n.setNote(4);
//		notas[0] = n;
//		n = new Note();
//		n.setCourse(c2);
//		n.setNote(6);
//		notas[1] = n;
//		n = new Note();
//		n.setCourse(c3);
//		n.setNote(8);
//		notas[2] = n;
//		s.setNotas(notas);
//
//		Student s2 = new Student();
//		s2.setFirstName("juan");
//		s2.setLastName("martin");
//		notas = new Note[3];
//		n = new Note();
//		n.setCourse(c);
//		n.setNote(5);
//		notas[0] = n;
//		n = new Note();
//		n.setCourse(c2);
//		n.setNote(7);
//		notas[1] = n;
//		n = new Note();
//		n.setCourse(c3);
//		n.setNote(9);
//		notas[2] = n;
//		s2.setNotas(notas);
//
//		datastore.save(c);
//		datastore.save(c2);
//		datastore.save(c3);
//		datastore.save(s);
//		datastore.save(s2);
//	}
}
