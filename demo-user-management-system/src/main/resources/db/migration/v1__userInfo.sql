-- select * from user_info;

-- drop table user_info;
CREATE TABLE user_info (
    user_id BIGSERIAL PRIMARY KEY,
    username VARCHAR(100)  NOT NULL,
    password VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL,
    gender VARCHAR(200) NOT NULL,
    email VARCHAR(100) NOT NULL,
	contact VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL,
    date_of_birth DATE NOT NULL,
    address VARCHAR(200) NOT NULL,
	last_login DATE
);



