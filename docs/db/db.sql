use race;
drop table if exists category;
create table `category` (
    `id` char(8) not null comment 'ID',
    `course_id` varchar(50) comment '课程ID',
    `name` varchar(50) comment '名称',
    primary key (`id`)
) engine=innodb default charset=utf8 comment='课程类别'

drop table if exists video;
create table `video` (
    id char(8) NOT NULL DEFAULT '' COMMENT 'ID',
    title VARCHAR (50) NOT NULL COMMENT '标题',
    categoryId VARCHAR (8) COMMENT '课程id',
    createAt DATETIME(3) COMMENT '创建时间',
    updateAt DATETIME(3) COMMENT '修改时间',
    time INT COMMENT '时长',
	constraint category_pk foreign key (categoryId) references category(id) on delete cascade on update cascade
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='视频'