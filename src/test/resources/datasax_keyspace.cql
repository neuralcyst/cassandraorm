
drop keyspace IF EXISTS datasax;

CREATE KEYSPACE datasax WITH REPLICATION = {
  'class': 'SimpleStrategy',
  'replication_factor': 1
};

CREATE TABLE datasax.user (
    id BIGINT,
    department_id BIGINT,
    name VARCHAR,
    age INT,
    PRIMARY KEY (id)) WITH caching = { 'keys' : 'ALL', 'rows_per_partition' : 'ALL' };

CREATE TABLE datasax.ages (
    department_id BIGINT,
    age INT,
    user_id BIGINT,
    PRIMARY KEY (department_id, age, user_id)) WITH caching = { 'keys' : 'ALL', 'rows_per_partition' : 'ALL' };