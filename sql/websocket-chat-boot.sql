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