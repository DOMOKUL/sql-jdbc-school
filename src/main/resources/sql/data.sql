ALTER SEQUENCE university.global_seq RESTART WITH 100000;

INSERT INTO students ( group_id , first_name, last_name) VALUES
(1, 'Callum', 'Mcfarland'),
(1, 'Ashely', 'Blevins'),
(1, 'Randall', 'Vang'),
(1, 'Barry', 'Wilson'),
(1, 'Alea', 'Knight'),
(1, 'Keiko', 'Olsen'),
(1, 'Aristotle', 'Atkinson'),
(1, 'Alexandra', 'Stephenson'),
(1, 'Lucius', 'Dotson'),
(1, 'Harper', 'Kirby'),
(2, 'Griffith', 'Buchanan'),
(2, 'Todd', 'Silva'),
(2, 'Cairo', 'Cruz'),
(2, 'Rina', 'Stuart'),
(2, 'Eagan', 'Pollard'),
(2, 'Emmanuel', 'Mccray'),
(2, 'Orlando', 'Holloway'),
(2, 'Brody', 'Mcgee'),
(3, 'Ezekiel', 'Pierce'),
(3, 'Phillip', 'Mccormick'),
(3, 'Allegra', 'Savage'),
(3, 'Hyacinth', 'Rodgers'),
(3, 'Suki', 'Ewing'),
(3, 'Yeo', 'Forbes'),
(3, 'Shaeleigh', 'Chaney'),
(3, 'Colby', 'Hutchinson'),
(3, 'Demetrius', 'Blackwell'),
(3, 'Imani', 'Lee'),
(3, 'MacKensie', 'Mccall'),
(3, 'Camden', 'Guzman'),
(3, 'Christian', 'Jones'),
(4, 'Drake', 'Curry'),
(4, 'Velma', 'Sherman'),
(4, 'Jakeem', 'Mann'),
(4, 'Reese', 'Rosa'),
(4, 'Giacomo', 'Dixon'),
(5, 'Fulton', 'Bryant'),
(5, 'Alexander', 'Bowen'),
(5, 'Jolene', 'Anderson');


INSERT INTO groups (group_id, name) VALUES
(1, 'SR-01'),
(2, 'SR-02'),
(3, 'SR-03'),
(4, 'SR-04'),
(5, 'SR-05');


INSERT INTO courses (course_id, name, description) VALUES
(1, 'Course #1', 'Some course #1' ),
(2, 'Course #2', 'Some course #2' ),
(3, 'Course #3', 'Some course #3' ),
(4, 'Course #4', 'Some course #4' ),
(5, 'Course #5', 'Some course #5' ),
(6, 'Course #6', 'Some course #6' ),
(7, 'Course #7', 'Some course #7' ),
(8, 'Course #8', 'Some course #8' ),
(9, 'Course #9', 'Some course #9' ),
(10, 'Course #10', 'Some course #10' ),
(11, 'Course #11', 'Some course #11' );
