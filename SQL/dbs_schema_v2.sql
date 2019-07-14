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
drop table teacherTT;
drop table studentTT;
drop table teaches;
drop table appointment;
drop table course;
drop table teacher;
drop table student;
drop table department;
drop table service;
drop table alogin;

/* Topological Order of Relations based on Foreign Key constraints
alogin
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

/* ALOGIN */
create table alogin(
	id varchar2(20) primary key,
	pw varchar2(20)
);
insert into alogin values ('admin', 'admin');

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
	student_pw varchar2(20),
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
	teacher_pw varchar2(20),
	fname varchar2(20),
	lname varchar2(20),
	salary numeric(10, 2),
	fc varchar2(20),
	dept_id varchar2(20),
	bonus numeric(10, 2),
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
	app_day varchar2(20),
	app_tslot varchar2(20),
	status char(1),
	service_id varchar2(20),
	student_id varchar2(20),
	teacher_id varchar2(20),
	course_id varchar2(20),
	check (app_day in ('Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday')),
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
	dept_id varchar2(20),
	day varchar2(20),
	tslot1 varchar2(20),
	tslot2 varchar2(20),
	tslot3 varchar2(20),
	foreign key(dept_id) references department(dept_id)
);
