select * from user_info;
select * from activity_logs;

drop table user_info;
CREATE TABLE user_info (
    user_id BIGSERIAL PRIMARY KEY,
    log_id integer REFERENCES activity_logs(log_id),
    user_name VARCHAR(100),
    password VARCHAR(100),
    email VARCHAR(100),
    name VARCHAR(100),
    date_of_birth DATE,
    address VARCHAR(200)
);



