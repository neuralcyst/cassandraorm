package com.neuralcyst.cassandraorm.datastax;

import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.google.inject.Inject;
import com.neuralcyst.cassandraorm.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

public class UserRepositoryDataStaxImpl implements UserRepository<UserDataStaxImpl> {

    private final MappingManager manager;
    private final Mapper<UserDataStaxImpl> userMapper;
    private final UserAccessor userAccessor;
    private final Mapper<Ages> agesMapper;
    private final AgesAccessor agesAccessor;


    @Inject
    private UserRepositoryDataStaxImpl(Session session) {
        this.manager = new MappingManager(session);
        this.userMapper = manager.mapper(UserDataStaxImpl.class);
        this.userAccessor = manager.createAccessor(UserAccessor.class);
        this.agesMapper = manager.mapper(Ages.class);
        this.agesAccessor = manager.createAccessor(AgesAccessor.class);
    }

    @Override
    public void create(UserDataStaxImpl user) {
        userMapper.save(user);
        agesMapper.save(user.createAgesView());
    }

    @Override
    public void update(UserDataStaxImpl user) {
        userMapper.save(user);
        agesMapper.delete(user.createPreviousAgesView());
        agesMapper.save(user.createAgesView());
    }

    @Override
    public void delete(long id) {
        UserDataStaxImpl user = findById(id);
        agesMapper.delete(user.createPreviousAgesView());
        userMapper.delete(user);
    }

    @Override
    public UserDataStaxImpl findById(long id) {
        return userMapper.get(id);
    }

    @Override
    public List<UserDataStaxImpl> findOlderThanInDepartment(long departmentId, int age) {
        List<Ages> ages = agesAccessor.findOlderThanInDepartment(departmentId, age).all();
        List<Long> userIds = ages.stream().map(Ages::getUserId).collect(Collectors.toList());
        return userAccessor.findByUserIds(userIds).all();
    }
}
