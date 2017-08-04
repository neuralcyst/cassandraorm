package com.neuralcyst.cassandraorm;

import com.datastax.driver.core.Session;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.cassandraunit.CassandraCQLUnit;
import org.cassandraunit.dataset.cql.ClassPathCQLDataSet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

public abstract class CassandraOrmTest {

    @Inject
    private UserRepository userRepository;
    @Inject
    private User user1;
    @Inject
    private User user2;

    @Rule
    public CassandraCQLUnit cassandraCQLUnit = new CassandraCQLUnit(new ClassPathCQLDataSet(getDataSetName()));

    @Before
    public void before() {
        Injector injector = createInjector(cassandraCQLUnit.session);
        injector.injectMembers(this);
    }

    @Test
    public void crudUser() {
        long id = 1;
        long departmentId = 1;
        int age = 25;
        int newAge = 25;
        String name = "Test name";
        String newName = "New test name";

        user1.setId(id);
        user1.setDepartmentId(departmentId);
        user1.setName(name);
        user1.setAge(age);
        userRepository.create(user1);
        User userById = userRepository.findById(id);

        Assert.assertNotNull(userById);
        Assert.assertEquals(id, userById.getId());
        Assert.assertEquals(name, userById.getName());
        Assert.assertEquals(age, userById.getAge());

        user1.setName(newName);
        user1.setAge(newAge);
        userRepository.update(user1);
        User updatedUserById = userRepository.findById(id);

        Assert.assertNotNull(updatedUserById);
        Assert.assertEquals(id, updatedUserById.getId());
        Assert.assertEquals(newName, updatedUserById.getName());
        Assert.assertEquals(newAge, updatedUserById.getAge());

        userRepository.delete(id);
        User deletedUserById = userRepository.findById(id);

        Assert.assertNull(deletedUserById);
    }

    @Test
    public void findOlderThanInDepartment() {
        long departmentId = 1;
        long id1 = 1;
        long id2 = 2;
        String name1 = "Young guy";
        String name2 = "Old guy";
        int age1 = 20;
        int age2 = 40;

        user1.setId(id1);
        user1.setDepartmentId(departmentId);
        user1.setName(name1);
        user1.setAge(age1);

        userRepository.create(user1);

        user2.setId(id2);
        user2.setDepartmentId(departmentId);
        user2.setName(name2);
        user2.setAge(age2);

        userRepository.create(user2);

        List<User> olderThan18 = userRepository.findOlderThanInDepartment(departmentId, 18);
        Assert.assertNotNull(olderThan18);
        Assert.assertEquals(2, olderThan18.size());
        Assert.assertTrue(olderThan18.stream().anyMatch(user -> id1 == user.getId()));
        Assert.assertTrue(olderThan18.stream().anyMatch(user -> id2 == user.getId()));

        List<User> olderThan30 = userRepository.findOlderThanInDepartment(departmentId, 30);
        Assert.assertNotNull(olderThan30);
        Assert.assertEquals(1, olderThan30.size());
        Assert.assertTrue(olderThan30.stream().anyMatch(user -> id2 == user.getId()));

    }

    protected abstract Injector createInjector(Session session);
    protected abstract String getDataSetName();

}
