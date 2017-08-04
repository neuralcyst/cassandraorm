
drop keyspace IF EXISTS achilles;

CREATE KEYSPACE achilles WITH REPLICATION = {
  'class': 'SimpleStrategy',
  'replication_factor': 1
};

CREATE TABLE achilles.user (
    id BIGINT,
    department_id BIGINT,
    name VARCHAR,
    age INT,
    PRIMARY KEY (id)) WITH caching = { 'keys' : 'ALL', 'rows_per_partition' : 'ALL' };

CREATE TABLE achilles.ages (
    department_id BIGINT,
    age INT,
    user_id BIGINT,
    PRIMARY KEY (department_id, age, user_id)) WITH caching = { 'keys' : 'ALL', 'rows_per_partition' : 'ALL' };