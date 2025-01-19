CREATE TABLE resource (
id SERIAL PRIMARY KEY,
name VARCHAR(256),
file_name VARCHAR(256),
file_data BYTEA,
upload_on TIMESTAMP
);

CREATE TABLE resource_revision (
id SERIAL PRIMARY KEY,
resource_id BIGINT,
FOREIGN KEY (resource_id) REFERENCES resource(id),
resource_name varchar(256),
file_name VARCHAR(256),
file_data BYTEA,
created_on TIMESTAMP
);

create table email_log(
id serial primary key,
send_to varchar(256),
subject varchar(256),
body varchar(256),
send_date timestamp
);