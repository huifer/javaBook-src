create table account
(
  id    int(11) not null auto_increment,
  money double,
  name  varchar(20),
  primary key (id)
);

insert into account (money, name)
values (1000 , "张三");
insert into account (money, name)
values (500, "李四");

select *
from account;
