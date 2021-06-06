drop table if exists user;
create table user
(id IDENTITY not null  primary key,
name varchar(50) not null,
memo varchar(500) null,
create_time TIMESTAMP  not null
);