INSERT INTO Persons (idperson, firstname, lastname, dateofbirth)
VALUES (1, "TeacherName1", "TeacherLastName1", '19601001'),
(2, "TeacherName2", "TeacherLastName2", '19531101'),
(3, "TeacherName3", "TeacherLastName3", '19640111'),
(4, "TeacherName3", "TeacherLastName3", '19640111'),
(5, "Student1", "StudentLastName1", '19900111'),
(6, "Student2", "StudentLastName2", '19900111'),
(7, "Student3", "StudentLastName3", '19900111'),
(8, "Student4", "StudentLastName4", '19900111'),
(9, "Student5", "StudentLastName5", '19900111'),
(10, "Student6", "StudentLastName6", '19900111'),
(11, "Student7", "StudentLastName7", '19900111'),
(12, "Student8", "StudentLastName8", '19900111'),
(13, "Student9", "StudentLastName9", '19900111'),
(14, "Student10", "StudentLastName10", '19900111'),
(15, "Student11", "StudentLastName11", '19900111'),
(16, "Student12", "StudentLastName12", '19900111'),
(17, "Student13", "StudentLastName13", '19900111'),
(18, "Student14", "StudentLastName14", '19900111'),
(19, "Student15", "StudentLastName15", '19900111');

INSERT INTO Students (idperson, registrationnum)
VALUES (5,0001),
(6,0002),
(7,0003),
(8,0004),
(9,0005),
(10,0006),
(11,0007),
(12,0008),
(13,0009),
(14,0010),
(15,0011),
(16,0012),
(17,0013),
(18,0014),
(19,0015);

INSERT INTO Teachers(idperson)
VALUES (1),(2),(3);

INSERT INTO Courses (idcourse, name, idteacher, hoursaweek)
VALUES (1, 'Maths', 1, 5), (2, 'Programming', 2, 3),(3, 'Physics', 3, 4);

INSERT INTO Assistants (idcourse,idstudent)
VALUES (1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(1,14),
(2,7),(2,8),(2,9),(2,10),(2,11),(2,12),(2,13),(2,14),(2,15),(2,16),
(3,10),(3,11),(3,12),(3,13),(3,14),(3,15),(3,16),(3,17),(3,18),(3,19);

INSERT INTO Schedules (idcourse, dayofweek, starttime, endtime)
VALUES (1,1,'10:00','12:00'), (1,3,'11:00','14:00'),
(2,2,'08:00','09:00'), (2,4,'10:00','12:00'),
(3,5,'10:00','14:00');

INSERT INTO ExamTypes (idexamtype, description)
VALUES (1, 'Partial 1'), (2, 'Partial 2'), (3, 'Partial 3'), (4, 'Final');

INSERT INTO Exams (idcourse, idstudent, idexamtype, note)
VALUES (1,5,1,5),(1,6,1,6),(1,7,1,7.5),(1,8,1,8),(1,9,1,4),(1,10,1,2),(1,11,1,6),(1,12,1,3),(1,13,1,9),(1,14,1,9.8),
(1,5,2,3),(1,6,2,4),(1,7,2,6),(1,8,2,5),(1,9,2,4),(1,10,2,6),(1,11,2,3),(1,12,2,6),(1,13,2,4),(1,14,2,4),
(1,5,3,5),(1,6,3,3),(1,7,3,8),(1,8,3,6),(1,9,3,5),(1,10,3,4),(1,11,3,5),(1,12,3,7),(1,13,3,5),(1,14,3,5),
(1,5,4,7),(1,6,4,4),(1,7,4,5),(1,8,4,7),(1,9,4,7),(1,10,4,6),(1,11,4,4),(1,12,4,7),(1,13,4,3),(1,14,4,6);