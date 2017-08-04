package com.neuralcyst.cassandraorm.achilles;

import info.archinnov.achilles.annotations.CompileTimeConfig;
import info.archinnov.achilles.type.CassandraVersion;

@CompileTimeConfig(cassandraVersion = CassandraVersion.CASSANDRA_3_7)
public interface AchillesConfig {
}
