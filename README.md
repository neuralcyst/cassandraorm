# CASSANDRA-ORM - DataSax Object Mapper vs Achilles
This is small test application created with goal to compare to cassandra orm:
- DataSax Object Mapper: http://docs.datastax.com/en/developer/java-driver/3.2/manual/object_mapper/
- Achilles ORM: https://github.com/doanduyhai/Achilles/wiki

## About test
Please check **src/test/java/com/neuralcyst/cassandraorm/CassandraOrmTest** to start.
This is generic test logic operating with two interfaces: **User** and **UserRepository**
Both of them has implementations for Object Mapper and Achilles as well.
You can find them in separate packages:
- src/main/java/com/neuralcyst/cassandraorm/achilles
- src/main/java/com/neuralcyst/cassandraorm/datasax