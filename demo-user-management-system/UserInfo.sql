select * from user_info;

drop table user_info;
CREATE TABLE user_info (
    user_id BIGSERIAL PRIMARY KEY,
    log_id integer REFERENCES activity_logs(log_id),
	gender VARCHAR(200),
    user_name VARCHAR(100),
    password VARCHAR(100),
    email VARCHAR(100),
	phone_number VARCHAR(100),
    name VARCHAR(100),
    date_of_birth DATE,
    address VARCHAR(200),
	last_login DATE
);


select * from 
