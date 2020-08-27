use race;
drop table if exists category;
create table `category` (
    `id` char(8) not null comment 'ID',
    `course_id` varchar(50) comment '课程ID',
    `name` varchar(50) comment '名称',
    primary key (`id`)
) engine=innodb default charset=utf8 comment='课程类别'