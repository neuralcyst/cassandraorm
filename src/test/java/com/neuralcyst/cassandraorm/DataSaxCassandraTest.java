package com.neuralcyst.cassandraorm;

import com.datastax.driver.core.Session;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.neuralcyst.cassandraorm.datasax.UserDataSaxImpl;
import com.neuralcyst.cassandraorm.datasax.UserRepositoryDataSaxImpl;

public class DataSaxCassandraTest extends CassandraOrmTest {
    @Override
    protected Injector createInjector(Session session) {
        return Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(User.class).to(UserDataSaxImpl.class);
                bind(UserRepository.class).to(UserRepositoryDataSaxImpl.class).in(Singleton.class);
                bind(Session.class).toInstance(session);
            }
        });
    }

    @Override
    protected String getDataSetName() {
        return "datasax_keyspace.cql";
    }
}
