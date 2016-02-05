CREATE TABLE Persons
(
idperson INT NOT NULL, 
firstname VARCHAR(20), 
lastname VARCHAR(20), 
dateofbirth DATE,
CONSTRAINT pk_PersonID PRIMARY KEY (idperson)
);


CREATE TABLE Students (
idperson INT NOT NULL, 
registrationnum INT,
CONSTRAINT pk_StudentID PRIMARY KEY (idperson),
FOREIGN KEY (idperson) REFERENCES Persons(idperson)
);


CREATE TABLE Teachers (
idperson INT NOT NULL,
CONSTRAINT pk_TeacherID PRIMARY KEY (idperson),
FOREIGN KEY (idperson) REFERENCES Persons(idperson)
);


CREATE TABLE Courses (
idcourse INT NOT NULL, 
name VARCHAR(20), 
idteacher INT, 
hoursaweek INT,
CONSTRAINT pk_CourseID PRIMARY KEY (idcourse),
FOREIGN KEY (idteacher) REFERENCES Teachers(idperson)
);


CREATE TABLE Schedules 
(
idcourse INT NOT NULL, 
dayofweek INT NOT NULL, 
starttime TIME NOT NULL, 
endtime TIME NOT NULL,
CONSTRAINT pk_ScheduleID PRIMARY KEY (idcourse,dayofweek,starttime,endtime),
FOREIGN KEY (idcourse) REFERENCES Courses(idcourse),
CHECK (dayofweek>=0 AND dayofweek <= 6)
);


CREATE TABLE Assistants (
idstudent INT NOT NULL, 
idcourse INT NOT NULL,
CONSTRAINT pk_AssistantID PRIMARY KEY (idstudent,idcourse),
FOREIGN KEY (idstudent) REFERENCES Students(idperson),
FOREIGN KEY (idcourse) REFERENCES Courses(idcourse)
);

CREATE TABLE ExamTypes (
idexamtype INT NOT NULL,
description VARCHAR(20),
CONSTRAINT pk_ExamTypesID PRIMARY KEY (idexamtype)
);

CREATE TABLE Exams (
idstudent INT NOT NULL, 
idcourse INT NOT NULL,
idexamtype INT NOT NULL,
note FLOAT,
CONSTRAINT pk_ExamsID PRIMARY KEY (idstudent,idcourse,idexamtype),
FOREIGN KEY (idstudent, idcourse) REFERENCES Assistants (idstudent, idcourse),
FOREIGN KEY (idexamtype) REFERENCES ExamTypes(idexamtype),
CHECK (note>=0 AND note1 <=10)
);

CREATE FUNCTION DAYNAMEBYINT(s int)
RETURNS VARCHAR(10) DETERMINISTIC
RETURN DAYNAME(CONCAT("1970-09-2",s));