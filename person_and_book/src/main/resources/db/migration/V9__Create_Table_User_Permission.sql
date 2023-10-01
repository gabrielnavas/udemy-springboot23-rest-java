CREATE TABLE IF NOT EXISTS user_permission (
    id_user uuid,
    id_permission uuid,
    primary key(id_user, id_permission),
    constraint tb_user_permission_fk_id_user foreign key (id_user) references users (id),
    constraint tb_user_permission_fk_id_permission foreign key (id_permission) references permission (id)
)