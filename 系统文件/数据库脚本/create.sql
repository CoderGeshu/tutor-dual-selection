#######################################
# Drop tutor_dual_selection database
#######################################
DROP database IF EXISTS tutor_dual_selection;

##########################################
# Create tutor_dual_selection database
##########################################
CREATE database tutor_dual_selection;

##########################################
# Use tutor_dual_selection database
##########################################
use tutor_dual_selection;

########################
# Create admin table
########################
CREATE TABLE admin
(
   ano varchar(10) not null,
	password varchar(20) not null,
	aname varchar(30) not null,
	sex char(2) not null,
	tel char(11) null,
	typeno int not null, 
	PRIMARY KEY (ano),
	CONSTRAINT ck_admin_sex CHECK (sex in('男','女')),
	CONSTRAINT ck_admin_typeno CHECK (typeno in(0,1))
);

########################
# Create dept table
########################
CREATE TABLE dept
(
   dno varchar(10) not null,
	dname varchar(30) not null,
	ano varchar(10) not null,
	PRIMARY KEY (dno),
	CONSTRAINT fk_dept_admin FOREIGN KEY (ano) REFERENCES admin(ano)
);

########################
# Create major table
########################
CREATE TABLE major
(
   mno varchar(10) not null,
	mname varchar(30) not null,
	dno varchar(10) not null,
	PRIMARY KEY (mno),
	CONSTRAINT fk_major_dept FOREIGN KEY (dno) REFERENCES dept(dno)
);

########################
# Create tutor table
########################
CREATE TABLE tutor
(
   tno varchar(10) not null,
	password varchar(20) not null,
	tname varchar(30) not null,
	sex char(2) not null,
	birth date not null,
	title varchar(5) not null,
	tel char(11) null,
	email varchar(30) null,
	description text null,
	ability int not null DEFAULT 3,
	mno varchar(10) not null,
	PRIMARY KEY (tno),
	CONSTRAINT ck_tutor_sex CHECK (sex in('男','女')),
	CONSTRAINT ck_tutor_title CHECK (title in('副教授','教授')),
	CONSTRAINT fk_tutor_major FOREIGN KEY (mno) REFERENCES major(mno)
);

########################
# Create student table
########################
CREATE TABLE student
(
   sno varchar(10) not null,
	password varchar(20) not null,
	sname varchar(30) not null,
	sex char(2) not null,
	birth date not null,
	gpa decimal(4,3) not null,
	tel char(11) null,
	email varchar(30) null,
	description text null,
	mno varchar(10) not null,
	tno varchar(10) null,
	PRIMARY KEY (sno),
	CONSTRAINT ck_student_sex CHECK (sex in('男','女')),
	CONSTRAINT fk_student_major FOREIGN KEY (mno) REFERENCES major(mno),
	CONSTRAINT fk_student_tutor FOREIGN KEY (tno) REFERENCES tutor(tno)
);

########################
# Create selection table
########################
CREATE TABLE selection
(
	sno varchar(10) not null,
	tno varchar(10) not null,
	PRIMARY KEY(sno,tno),
	CONSTRAINT fk_selection_student FOREIGN KEY (sno) REFERENCES student(sno),
	CONSTRAINT fk_selection_tutor FOREIGN KEY (tno) REFERENCES tutor(tno)
);

########################
# Create voluntary table
########################
CREATE TABLE voluntary
(
	 sno varchar(10) not null,
	 tno varchar(10) not null,
	 status int not null DEFAULT 0
);

########################
# Create opentime table
########################
CREATE TABLE opentime
(
    starttime date not null,
	 endtime date not null
);

########################
# Create loginfo table
########################
CREATE TABLE loginfo
(
    account varchar(10) not null,
	 name varchar(30) not null,
	 logintime  datetime not null
);





