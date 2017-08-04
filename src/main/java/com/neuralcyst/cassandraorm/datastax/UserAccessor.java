package com.neuralcyst.cassandraorm.datasax;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Query;

import java.util.List;

@Accessor
public interface UserAccessor {
    @Query("select * from datasax.user where id in ?")
    Result<UserDataSaxImpl> findByUserIds(List<Long> ids);
}
