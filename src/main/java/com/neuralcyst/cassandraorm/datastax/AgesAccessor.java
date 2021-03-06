package com.neuralcyst.cassandraorm.datastax;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;

@Accessor
public interface AgesAccessor {
    @Query("select * from datastax.ages where department_id = :d and age > :a")
    Result<Ages> findOlderThanInDepartment(@Param("d") long department_id, @Param("a") int age);
}
