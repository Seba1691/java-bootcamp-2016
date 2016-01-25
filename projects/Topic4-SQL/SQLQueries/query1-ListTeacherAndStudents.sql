"SELECT C.name as 'Course', CONCAT(PT.LastName,', ',PT.FirstName) as 'Teacher', GROUP_CONCAT(PS.LastName,', ',PS.FirstName SEPARATOR '\n') as 'Students' "+
"FROM    Courses as C JOIN Teachers as T ON (C.idteacher = T.idperson)"+
"   JOIN Persons as PT ON (T.idperson = PT.idperson)"+
"   JOIN Assistants as A ON (C.idcourse = A.idcourse)"+
"   JOIN Students as S ON (A.idstudent = S.idperson)"+
"   JOIN Persons as PS ON (S.idperson = PS.idperson)"+
"WHERE C.idcourse = 2 "+
"ORDER BY PS.LastName;"+