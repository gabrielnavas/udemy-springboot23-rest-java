CREATE TABLE IF NOT EXISTS users (
    id uuid,
    user_name varchar(255) default null,
    full_name varchar(255) default null,
    password varchar(255) default null,
    account_non_expired boolean default null,
    account_non_locked boolean default null,
    credentials_non_expired boolean default null,
    enabled boolean default null,
    CONSTRAINT tb_users_pk_id PRIMARY KEY (id),
    CONSTRAINT tb_users_unique_id UNIQUE (user_name)
);