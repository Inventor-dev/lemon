
CREATE TABLE IF NOT EXISTS sys_user
(
    user_id   BIGINT auto_increment comment '用户ID',
    username  VARCHAR not null comment '用户登录名',
    password  VARCHAR comment '密码',
    phone     VARCHAR(11) comment '手机号',
    user_code VARCHAR comment '用户编码',
    real_name NVARCHAR(20) comment '真实姓名',
    constraint sys_user_pk primary key (user_id)
);

CREATE UNIQUE INDEX IF NOT EXISTS sys_user_uindex_phone
    on sys_user (phone);

CREATE UNIQUE INDEX IF NOT EXISTS sys_user_uindex_username
    on sys_user (username);

CREATE UNIQUE INDEX IF NOT EXISTS sys_user_uindex_user_code
    on sys_user (user_code);

