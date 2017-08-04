package com.neuralcyst.cassandraorm.achilles;

import info.archinnov.achilles.annotations.ClusteringColumn;
import info.archinnov.achilles.annotations.Column;
import info.archinnov.achilles.annotations.PartitionKey;
import info.archinnov.achilles.annotations.Table;

@Table(keyspace = AchillesConstants.KEYSPACE, table = "ages")
public class Ages {

    @PartitionKey
    @Column("department_id")
    private long departmentId;

    @ClusteringColumn
    @Column
    private int age;

    @ClusteringColumn(2)
    @Column("user_id")
    private long userId;

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
