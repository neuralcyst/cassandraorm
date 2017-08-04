package com.neuralcyst.cassandraorm;

import java.util.List;

public interface UserRepository<U extends User> {
    void create(U user);

    void update(U user);

    void delete(long id);

    U findById(long id);

    List<U> findOlderThanInDepartment(long department, int age);
}
