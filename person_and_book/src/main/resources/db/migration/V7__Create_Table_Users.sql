CREATE TABLE IF NOT EXISTS users (
    id uuid,
    user_name varchar(255) default null,
    full_name varchar(255) default null,
    password varchar(255) default null,
    account_non_expired bit(1) default null,
    account_non_locked bit(1) default null,
    credentials_non_expired bit(1) default null,
    enabled bit(1) default null,
    CONSTRAINT tb_users_pk_id PRIMARY KEY (id),
    CONSTRAINT tb_users_unique_id UNIQUE (user_name)
);