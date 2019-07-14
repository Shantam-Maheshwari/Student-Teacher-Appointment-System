/* drop University Database tables */
drop table prereq;

drop table time_slot;

drop table advisor;

drop table takes;

drop table student;

drop table teaches;

drop table section;

drop table instructor;

drop table course;

drop table department;

drop table classroom;

/* drop this project's tables */
drop teacherTT;
drop studentTT;
drop teaches;
drop appointment;
drop course;
drop teacher;
drop student;
drop department;
drop service;

/* Topological Order of Relations based on Foreign Key constraints
service
department
student
teacher
course
appointment
teaches
studentTT
teacherTT
*/

/* SERVICE */
create table service(
	service_id varchar2(20) primary key,
	service_name varchar2(20),
	service_pay numeric(10, 2)
);

/* DEPARTMENT */
create table department(
	dept_id varchar2(20) primary key,
	dept_name varchar2(20)
);

/* STUDENT */
create table student(
	student_id varchar2(20) primary key,
	fname varchar2(20),
	lname varchar2(20),
	grade int,
	attendance int,
	dept_id varchar(20),
	foreign key(dept_id) references department(dept_id)
);

/* TEACHER */
create table teacher(
	teacher_id varchar2(20) primary key,
	fname varchar2(20),
	lname varchar2(20),
	salary numeric(10, 2),
	fc varchar2(20),
	dept_id varchar2(20),
	foreign key(dept_id) references department(dept_id)
);

/* COURSE */
create table course(
	course_id varchar2(20) primary key,
	course_name varchar2(20),
	dept_id varchar2(20),
	foreign key(dept_id) references department(dept_id)
);

/* APPOINTMENT */
create table appointment(
	app_id varchar2(20) primary key,
	app_date date,
	status char(1),
	duration int,
	service_id varchar2(20),
	student_id varchar2(20),
	teacher_id varchar2(20),
	course_id varchar2(20),
	check (status in ('y', 'n')),
	foreign key(service_id) references service(service_id),
	foreign key(student_id) references student(student_id),
	foreign key(teacher_id) references teacher(teacher_id),
	foreign key(course_id) references course(course_id)
);

/* TEACHES */
create table teaches(
	teacher_id varchar2(20),
	course_id varchar2(20),
	foreign key(teacher_id) references teacher(teacher_id),
	foreign key(course_id) references course(course_id)
);

/* STUDENTTT */
create table studentTT(
	dept_id varchar2(20),
	day varchar2(20),
	tslot1 varchar2(20),
	tslot2 varchar2(20),
	tslot3 varchar2(20),
	foreign key(dept_id) references department(dept_id)
);

/* TEACHERTT */
create table teacherTT(
	teacher_id varchar2(20),
	day varchar2(20),
	tslot1 varchar2(20),
	tslot2 varchar2(20),
	tslot3 varchar2(20),
	foreign key(teacher_id) references teacher(teacher_id)
);

	
	