
/* insert the admin permission */

insert into user_permission (id_user, id_permission)
select
	(select id as id_user from "users" where user_name = 'admin'),
	(select id as id_permission from "permission" where description = 'ADMIN');

insert into user_permission (id_user, id_permission)
select
	(select id as id_user from "users" where user_name = 'admin'),
	(select id as id_permission from "permission" where description = 'MANAGER');

insert into user_permission (id_user, id_permission)
select
	(select id as id_user from "users" where user_name = 'admin'),
	(select id as id_permission from "permission" where description = 'COMMON_USER');


/* insert the manager permission */

insert into user_permission (id_user, id_permission)
select
	(select id as id_user from "users" where user_name = 'manager'),
	(select id as id_permission from "permission" where description = 'MANAGER');

insert into user_permission (id_user, id_permission)
select
	(select id as id_user from "users" where user_name = 'manager'),
	(select id as id_permission from "permission" where description = 'COMMON_USER');
