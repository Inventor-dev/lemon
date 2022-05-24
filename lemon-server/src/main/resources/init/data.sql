insert into sys_user (user_id, username, password, phone, user_code, real_name)
select * from (
         select 0                                                                                       as `user_id`
              , 'admin'                                                                                 as `username`
              , '646OelhDayHJ769TcWtB@ce331dd57d27a8c4f7983f51e3bf0ecdf39b82b0f6a3406a2d77104c58bd897e' as `password`
              , null                                                                                    as `phone`
              , 'admin'                                                                                 as `user_code`
              , '管理员'                                                                                 as `real_name`
     )
where not exists(select user_id, username from sys_user);