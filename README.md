###### Task 7 - SQL & JDBC

Create an application sql-jdbc-school that inserts/updates/deletes data in the database using JDBC. Use PostgreSQL DB.

Tables (given types are Java types, use SQL analogs that fit the most:

groups( group_id int, group_name string )

students( student_id int, group_id int, first_name string, last_name string )

courses( course_id int, course_name string, course_description string )

**1.Create SQL files with data:**

a. create user and database. Assign all privileges on the database to the user. (DB and user should be created before application runs)

b. create a file with tables creation

**2.Create a java application**

a. Give user a possibility to run SQL script with tables creation from previously created files.
a. Give user a possibility to recreate database (if tables already exist - drop them).

b. Generate test data:

* 10 groups with randomly generated names. The name should contain 2 characters, hyphen, 2 numbers

* Create 10 courses (math, biology, etc)

* 200 students. Take 20 first names and 20 last names and randomly combine them to generate students.

* Randomly assign students to groups. Each group could contain from 10 to 30 students. It is possible that some groups will be without students or students without groups

* Create relation MANY-TO-MANY between tables STUDENTS and COURSES. Randomly assign from 1 to 3 courses for each student

**3.Write SQL Queries, it should be available from the application menu:**

a. Find all groups with less or equals student count

b. Find all students related to course with given name

c. Add new student

d. Delete student by STUDENT_ID

e. Add a student to the course (from a list)

f. Remove the student from one of his or her courses

g. Show all students

h. Create a group

i. Create a course

**Example of results:**    

Menu:

![menu](https://git.foxminded.com.ua/nikita.strokach/sql-jdbc-school/uploads/9c59595b7d838bb9f12bf2a372ef6430/menu.png)

![first-case](https://git.foxminded.com.ua/nikita.strokach/sql-jdbc-school/uploads/35cea6b18cdc97c63e71b4ab499ef60a/first-case.PNG)

![second-case](https://git.foxminded.com.ua/nikita.strokach/sql-jdbc-school/uploads/4a7d26e4fb550e6c8d41301d6546f49f/second-case.PNG)

![third-case](https://git.foxminded.com.ua/nikita.strokach/sql-jdbc-school/uploads/f9b6777e2a3a5a9953d91389177b1aa9/third-case.PNG)

![fourth-case](https://git.foxminded.com.ua/nikita.strokach/sql-jdbc-school/uploads/0eea10bdd50d1b8c506359a9025f3936/fourth-case.PNG)

![fifth-case](https://git.foxminded.com.ua/nikita.strokach/sql-jdbc-school/uploads/268f6d05c11e2ddf1dca9254d06a6bf6/fifth-case.PNG)

![sixth-case](https://git.foxminded.com.ua/nikita.strokach/sql-jdbc-school/uploads/c899492b1eea72303b6de4d271b4acf2/sixth-case.PNG)

![seventh-case](https://git.foxminded.com.ua/nikita.strokach/sql-jdbc-school/uploads/7ed99594fe59c99138fd0a941f0575f2/seventh-case.PNG)

![eighth-case](https://git.foxminded.com.ua/nikita.strokach/sql-jdbc-school/uploads/90af9798fce076a86244de28f4c2ba97/eighth-case.PNG)

![ninth-case](https://git.foxminded.com.ua/nikita.strokach/sql-jdbc-school/uploads/f3b289d86b24518ae4e6b3b19be7320a/ninth-case.PNG)