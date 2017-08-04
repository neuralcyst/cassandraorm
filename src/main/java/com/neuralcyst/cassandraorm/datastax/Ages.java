package com.neuralcyst.cassandraorm.datastax;

import com.datastax.driver.mapping.annotations.ClusteringColumn;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

@Table(keyspace = DataStaxConstants.KEYSPACE, name = "ages")
public class Ages {

    @PartitionKey
    @Column(name = "department_id")
    private long departmentId;

    @ClusteringColumn
    private int age;

    @ClusteringColumn(1)
    @Column(name = "user_id")
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
