SELECT (SELECT COUNT(*)
        FROM   EXAMS as E 
        WHERE  E.idcourse = 1 AND E.idexamtype = 1 AND E.Note>=4) /
       (SELECT COUNT(*)
        FROM   EXAMS as E 
        WHERE  E.idcourse = 1 AND E.idexamtype = 1)*100 as 'Porcentage';