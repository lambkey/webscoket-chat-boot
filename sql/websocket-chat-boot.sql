create database websocket_chat;

use websocket_chat;

create table t_user (
  id int primary key auto_increment,
  username varchar(255) not null comment '用户名',
  password varchar(255) not null comment '密码',
  created_time timestamp default current_timestamp comment '创建时间',
  updated_time timestamp default current_timestamp on update current_timestamp comment '更新时间',
  flag tinyint default 0 comment '0 正常， 1 删除'
);

insert into t_user (username, password) values ('张三', '123456');
insert into t_user (username, password) values ('李四', '123456');
insert into t_user (username, password) values ('王五', '123456');
insert into t_user (username, password) values ('赵六', '123456');


create table t_message (
  id int primary key auto_increment,
  from_id int not null comment '发送者id',
  to_id int not null comment '接收者id',
  content varchar(255) not null comment '内容',
  created_time timestamp default current_timestamp comment '创建时间'
);

insert into t_message (from_id, to_id, content) values (1, 2, '你好，李四');
insert into t_message (from_id, to_id, content) values (1, 2, '你好，李四，我是你爹');
insert into t_message (from_id, to_id, content) values (3, 1, '你好，张三2');
insert into t_message (from_id, to_id, content) values (4, 1, '你好，张三3');

insert into t_message (from_id, to_id, content) values (2, 1, '你好，张三1');
insert into t_message (from_id, to_id, content) values (2, 1, '你好，我是李四01');
insert into t_message (from_id, to_id, content) values (2, 1, '你好，我是李四02');
insert into t_message (from_id, to_id, content) values (2, 1, '你好，我是李四03');


select * from t_message;

select * from t_user;