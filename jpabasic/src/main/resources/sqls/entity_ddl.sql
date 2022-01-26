drop table if exists member;
create table member
(
    id   bigint auto_increment,
    name varchar(50),
    age tinyint,
    role_type enum('USER', 'ADMIN'),
    created_date date,
    last_modified_date date,
    description tinyblob,
    primary key (id)
);
