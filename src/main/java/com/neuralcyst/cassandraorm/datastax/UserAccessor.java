package com.neuralcyst.cassandraorm.datastax;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Query;

import java.util.List;

@Accessor
public interface UserAccessor {
    @Query("select * from datastax.user where id in ?")
    Result<UserDataStaxImpl> findByUserIds(List<Long> ids);
}
