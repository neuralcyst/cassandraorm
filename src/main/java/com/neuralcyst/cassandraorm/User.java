package com.neuralcyst.cassandraorm;

public interface User {
    long getId();
    void setId(long id);
    long getDepartmentId();
    void setDepartmentId(long departmentId);
    String getName();
    void setName(String name);
    int getAge();
    void setAge(int age);
}
