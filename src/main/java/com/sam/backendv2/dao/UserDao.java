package com.sam.backendv2.dao;
import com.sam.backendv2.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

@Mapper
public interface UserDao {

    @Select("SELECT id,username,password FROM user where username = #{username}")
    Optional<User> findByUsername(@Param("username") String username);
}
