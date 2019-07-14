/* SERVICE */
insert into service values('ser001', 'doubts clearing', 5000);
insert into service values('ser002', 'paper seeing', 2000);
insert into service values('ser003', 'homework help', 3000);

/* DEPARTMENT */
insert into department values('dep001', 'CSE');
insert into department values('dep002', 'ECE');
insert into department values('dep003', 'EEE');

/* STUDENT initially empty */

/* TEACHER  initially empty */

/* COURSE */
insert into course (dept_id, course_id, course_name) values('dep001', 'cou011', 'MP');
insert into course (dept_id, course_id, course_name) values('dep001', 'cou012', 'FLAT');
insert into course (dept_id, course_id, course_name) values('dep001', 'cou013', 'DBS');

insert into course (dept_id, course_id, course_name) values('dep002', 'cou021', 'DSP');
insert into course (dept_id, course_id, course_name) values('dep002', 'cou022', 'ICS');
insert into course (dept_id, course_id, course_name) values('dep002', 'cou023', 'ANT');

insert into course (dept_id, course_id, course_name) values('dep003', 'cou031', 'SNS');
insert into course (dept_id, course_id, course_name) values('dep003', 'cou032', 'DSD');
insert into course (dept_id, course_id, course_name) values('dep003', 'cou033', 'ASD');

/* APPOINTMENT initally empty */

/* STUDENTTT */
insert into studentTT values ('dep001', 'Monday', 'cou011', null, 'cou012');
insert into studentTT values ('dep001', 'Tuesday', 'cou013', 'cou011', null);
insert into studentTT values ('dep001', 'Wednesday', null, 'cou012', 'cou013');
insert into studentTT values ('dep001', 'Thursday', 'cou011', 'cou012', null);
insert into studentTT values ('dep001', 'Friday', 'cou013', null, 'cou011');

insert into studentTT values ('dep002', 'Monday', 'cou021', null, 'cou022');
insert into studentTT values ('dep002', 'Tuesday', 'cou023', 'cou021', null);
insert into studentTT values ('dep002', 'Wednesday', null, 'cou022', 'cou023');
insert into studentTT values ('dep002', 'Thursday', 'cou021', 'cou022', null);
insert into studentTT values ('dep002', 'Friday', 'cou023', null, 'cou021');

insert into studentTT values ('dep003', 'Monday', 'cou031', null, 'cou032');
insert into studentTT values ('dep003', 'Tuesday', 'cou033', 'cou031', null);
insert into studentTT values ('dep003', 'Wednesday', null, 'cou032', 'cou033');
insert into studentTT values ('dep003', 'Thursday', 'cou031', 'cou032', null);
insert into studentTT values ('dep003', 'Friday', 'cou033', null, 'cou031');
