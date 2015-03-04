use mybatis;
drop table if exists blog;

create table blog
(
   id               int primary key auto_increment,
   title            varchar(100),
   content          text,
   createtime       timestamp default current_timestamp
);