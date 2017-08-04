package com.neuralcyst.cassandraorm.achilles;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.google.inject.Inject;
import com.neuralcyst.cassandraorm.UserRepository;
import info.archinnov.achilles.generated.ManagerFactory;
import info.archinnov.achilles.generated.ManagerFactoryBuilder;
import info.archinnov.achilles.generated.manager.Ages_Manager;
import info.archinnov.achilles.generated.manager.UserAchillesImpl_Manager;

import java.util.List;


public class UserRepositoryAchillesImpl implements UserRepository<UserAchillesImpl> {

    private final UserAchillesImpl_Manager userManager;
    private final Ages_Manager agesManager;

    @Inject
    private UserRepositoryAchillesImpl(Session session) {
        Cluster cluster = session.getCluster();
        ManagerFactory managerFactory = ManagerFactoryBuilder
                .builder(cluster)
                .doForceSchemaCreation(false)
                .build();
        userManager = managerFactory.forUserAchillesImpl();
        agesManager = managerFactory.forAges();
    }

    @Override
    public void create(UserAchillesImpl user) {
        userManager.crud().insert(user).execute();
        agesManager.crud().insert(user.createAgesView()).execute();
    }

    @Override
    public void update(UserAchillesImpl user) {
        userManager.crud().update(user).execute();
        agesManager.crud().delete(user.createPreviousAgesView()).execute();
        agesManager.crud().insert(user.createAgesView()).execute();
    }

    @Override
    public void delete(long id) {
        UserAchillesImpl user = findById(id);
        agesManager.crud().delete(user.createPreviousAgesView());
        userManager.crud().deleteById(id).execute();
    }

    @Override
    public UserAchillesImpl findById(long id) {
        return userManager.crud().findById(id).get();
    }

    @Override
    public List<UserAchillesImpl> findOlderThanInDepartment(long departmentId, int age) {
        List<Ages> list = agesManager.dsl()
                .select()
                .allColumns_FromBaseTable()
                .where()
                .departmentId().Eq(departmentId)
                .age().Gt(age)
                .getList();
        long[] userIds = list.stream().mapToLong(Ages::getUserId).toArray();

        return userManager.dsl()
                .select()
                .allColumns_FromBaseTable()
                .where()
                .id().IN(userIds)
                .getList();
    }
}
