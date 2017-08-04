package com.neuralcyst.cassandraorm.achilles;

import com.neuralcyst.cassandraorm.User;
import info.archinnov.achilles.annotations.*;

@Table(keyspace = AchillesConstants.KEYSPACE, table = "user")
public class UserAchillesImpl implements User {

    @PartitionKey
    private long id;

    @Column("department_id")
    private long departmentId;

    @Column
    private String name;

    @Column
    private int age;

    @Transient
    private int previousAge;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public long getDepartmentId() {
        return departmentId;
    }

    @Override
    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public void setAge(int age) {
        if (previousAge == 0) {
            previousAge = this.age;
        }
        this.age = age;
    }

    public Ages createAgesView() {
        Ages ages = new Ages();
        ages.setUserId(id);
        ages.setDepartmentId(departmentId);
        ages.setAge(age);
        return ages;
    }

    public Ages createPreviousAgesView() {
        Ages ages = new Ages();
        ages.setUserId(id);
        ages.setDepartmentId(departmentId);
        ages.setAge(previousAge == 0 ? age : previousAge);
        return ages;
    }
}
