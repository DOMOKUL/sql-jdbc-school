SELECT g.name,
       s.group_id,
       COUNT(s.group_id)
FROM students s
         INNER JOIN groups g ON s.group_id = g.group_id
GROUP BY s.group_id, g.name
HAVING COUNT(s.group_id) <= ?