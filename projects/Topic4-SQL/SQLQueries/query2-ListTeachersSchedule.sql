SELECT CONCAT(PT.LastName,', ',PT.FirstName) as 'Teacher',
       group_concat(CONCAT(DAYNAMEBYINT(S.dayofweek),' ',date_format(S.startTime,'%h:%i'),' - ',date_format(S.endtime,'%h:%i'),': ',C.name) SEPARATOR '\n') as 'Schedule'
FROM    Teachers as T JOIN Persons as PT ON (T.idperson = PT.idperson)
   JOIN Courses as C ON (C.idteacher = T.idperson)
   JOIN Schedules as S ON (C.idcourse = S.idcourse)
WHERE T.idPerson = 2
ORDER BY S.dayofweek, S.startTime;