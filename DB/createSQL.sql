
CREATE DATABASE hr;
/*
DROP TABLE JOBS;
DROP TABLE REGIONS;
DROP TABLE COUNTRIES;
DROP TABLE LOCATIONS;
DROP TABLE EMPLOYEES;
DROP TABLE DEPARTMENTS;
DROP TABLE JOB_HISTORY;
*/

USE hr;
-- Table Creation
 
create table JOBS
(job_id int not null,
job_title varchar(50),
min_salary int,
max_salary int,
primary key(job_id )
);

create table REGIONS
(region_id int not null,
region_name varchar(50),
primary key(region_id)
);

create table COUNTRIES
(country_id int not null,
country_name varchar(50),
region_id int not null,
foreign key (region_id) references REGIONS(region_id),
primary key(country_id)
); 

create table LOCATIONS
(location_id int not null,
street_address varchar(50),
postal_code varchar(50),
city varchar(50),
state_province varchar(50),
country_id int not null,
foreign key (country_id) references COUNTRIES(country_id),
primary key(location_id)
);

create table EMPLOYEES
(employee_id int not null,
first_name varchar(50),
last_name varchar(50),
email varchar(50),
phone_number varchar(50),
hire_date date,
salary int,
commission_pct int,
manager_id int,
department_id varchar(50),
job_id int not null,
INDEX h_date(hire_date),
foreign key (manager_id) references EMPLOYEES(employee_id),
foreign key (job_id) references JOBS(job_id),
primary key(employee_id)
);

create table DEPARTMENTS
(department_id int not null,
department_name varchar(50),
manager_id int not null,
location_id int not null,
foreign key (manager_id) references EMPLOYEES(employee_id),
foreign key (location_id) references LOCATIONS(location_id),
primary key(department_id)
);

create table JOB_HISTORY
(employee_id int not null,
start_date date,
end_date date,
job_id int not null,
department_id int not null,
INDEX s_date(start_date),
foreign key (employee_id) references EMPLOYEES(employee_id),
foreign key (start_date) references EMPLOYEES(hire_date),
foreign key (job_id) references JOBS(job_id),
foreign key (department_id) references DEPARTMENTS(department_id),
primary key(employee_id, start_date)
);

-- inserts
INSERT INTO JOBS(job_id, job_title, min_salary, max_salary) VALUES (1, 'manager', 10000, 100000);
INSERT INTO JOBS(job_id, job_title, min_salary, max_salary) VALUES (2, 'seller', 1000, 20000);
INSERT INTO JOBS(job_id, job_title, min_salary, max_salary) VALUES (3, 'ceo', 30000, 300000);
INSERT INTO JOBS(job_id, job_title, min_salary, max_salary) VALUES (4, 'cleaner', 100, 10000);
INSERT INTO JOBS(job_id, job_title, min_salary, max_salary) VALUES (5, 'cto', 20000, 200000);

INSERT INTO REGIONS(region_id, region_name) VALUES (1, 'Asia');
INSERT INTO REGIONS(region_id, region_name) VALUES (2, 'USA');
INSERT INTO REGIONS(region_id, region_name) VALUES (3, 'Europe');


INSERT INTO COUNTRIES(country_id, country_name, region_id) VALUES (1,'israel',1);
INSERT INTO COUNTRIES(country_id, country_name, region_id) VALUES (2,'china',1);
INSERT INTO COUNTRIES(country_id, country_name, region_id) VALUES (3,'japan',1);
INSERT INTO COUNTRIES(country_id, country_name, region_id) VALUES (4,'england',2);
INSERT INTO COUNTRIES(country_id, country_name, region_id) VALUES (5,'poland',2);
INSERT INTO COUNTRIES(country_id, country_name, region_id) VALUES (6,'france',2);
INSERT INTO COUNTRIES(country_id, country_name, region_id) VALUES (7,'NewYork',3);
INSERT INTO COUNTRIES(country_id, country_name, region_id) VALUES (8,'orlando',3);
INSERT INTO COUNTRIES(country_id, country_name, region_id) VALUES (9,'texas',3);

INSERT INTO LOCATIONS(location_id, street_address, postal_code, city, state_province, country_id) VALUES
(1, 'hazmora 11', '1111', 'rishon le zion', 'none', 1),
(2, 'yam 11', '2222', 'tel aviv', 'none', 1),
(3, 'hdkjsfhgk 5', '3333', 'jfdksj', 'none', 2),
(4, 'sasa', '4444', 'rishon le zion', 'none', 2),
(5, 'dfaf0 511', '5555', 'aaaaaa', 'fdsfsd', 3),
(6, 'fdsfsd 11', '6666', 'ssssssss', 'fdsfsd', 4),
(7, 'ggggddd 12', '7777', 'dddddd', 'none', 5),
(8, 'bgfgs 121', '8888', 'fsdfsd', 'none', 1),
(9, 'ddssss 116', '9999', 'fdssdn', 'fdsfds', 9),
(11, 'vfdfdg 1551', '1111', 'ffffff', 'vfdvfds', 9),
(12, 'sdfsf 113', '1212', 'erewr', 'fdsfds', 3),
(13, 'hytdfs 15', '1313', 'fffffff', 'dsjakn', 6);

INSERT INTO EMPLOYEES(employee_id, first_name, last_name, email, phone_number, hire_date, salary, commission_pct, manager_id, department_id, job_id) VALUES
(1,'gal', 'zaidman', 'gal@gmail.com', '0509980092', '2018-01-01', 290000, 25, null, 1, 3),
(2,'dudu', 'kirlich', 'dudu@gmail.com', '0539980092', '2018-01-01', 190000, 25, 1, 1, 5),
(3,'a', 'aa', 'a@gmail.com', '0509980091', '2018-01-01', 50000, 20, 1, 2, 1),
(4,'b', 'ss', 'b@gmail.com', '0509980093', '2018-01-01', 50001, 20, 3, 2, 1),
(5,'c', 'dd', 'v@gmail.com', '0509980094', '2018-01-01', 50002, 20, 3, 2, 1),
(6,'d', 'ff', 'cc@gmail.com', '0509980095', '2018-01-01', 20000, 10, 4, 3, 2),
(7,'e', 'gg', 'gccal@gmail.com', '0509980692', '2018-01-01', 19000, 10, 4, 3, 2),
(8,'f', 'hh', 'x@gmail.com', '0509980022', '2018-01-01', 19007, 10, 4, 3, 2),
(9,'g', 'jj', 's@gmail.com', '0509980032', '2018-01-01', 19006, 10, 4, 3, 2),
(10,'h', 'kk', 'd@gmail.com', '0509980492', '2018-01-01', 19005, 10, 4, 3, 2),
(11,'i', 'kk', 'g@gmail.com', '0509980592', '2018-01-01', 19004, 10, 4, 3, 2),
(12,'j', 'qq', 'h@gmail.com', '0509980692', '2018-01-01', 19003, 10, 4, 3, 2),
(13,'k', 'ee', 'j@gmail.com', '0509980792', '2018-01-01', 19002, 10, 4, 3, 2),
(14,'l', 'ee', 'k@gmail.com', '0509980892', '2018-01-01', 5001, 10, 5, 4, 2),
(15,'m', 'rr', 'l@gmail.com', '0509980992', '2018-01-01', 60000, 10, 5, 4, 4),
(17,'n', 'tt', 'n@gmail.com', '0509980192', '2018-01-01', 7000, 10, 5, 4, 4),
(18,'o', 'yy', 'm@gmail.com', '0509980292', '2018-01-01', 8000, 10, 5, 4, 4),
(19,'p', 'uu', 't@gmail.com', '0509983092', '2018-01-01', 9000, 10, 5, 4, 4),
(20,'q', 'uu', 'z@gmail.com', '0509984092', '2018-01-01', 10000, 10, 5, 4, 4);

INSERT INTO DEPARTMENTS(department_id, department_name, manager_id, location_id) VALUES
(1,'upper managment', 1, 1),
(2,'low managment', 3, 1),
(3,'sells', 4, 5),
(4,'cleaning', 5, 5);

INSERT INTO JOB_HISTORY(employee_id, start_date, end_date, job_id, department_id) VALUES
(1,'2018-01-01', NULL, 3, 1),
(2,'2018-01-01', NULL, 5, 1),
(3,'2018-01-01', NULL, 1, 2),
(4,'2018-01-01', NULL, 1, 2),
(5,'2018-01-01', NULL, 1, 2),
(6,'2018-01-01', NULL, 2, 3),
(7,'2018-01-01', NULL, 2, 3),
(8,'2018-01-01', NULL, 2, 3),
(9,'2018-01-01', NULL, 2, 3),
(10,'2018-01-01', NULL, 2, 3),
(11,'2018-01-01', NULL, 2, 3),
(12,'2018-01-01', NULL, 2, 3),
(13,'2018-01-01', NULL, 2, 3),
(14,'2018-01-01', NULL, 2, 4),
(15,'2018-01-01', NULL, 4, 4),
(17,'2018-01-01', NULL, 4, 4),
(18,'2018-01-01', NULL, 4, 4),
(19,'2018-01-01', NULL, 4, 4),
(20,'2018-01-01', NULL, 4, 4);



