drop table if exists user_role;
drop table if exists appuser;
drop table if exists role;

create table role(id int not null auto_increment, role varchar(100) unique, primary key(id));
create table appuser(id int not null auto_increment, username varchar(100), password varchar(100),primary key(id));
create table user_role(u_id int references appuser(id), r_id int references role(id));