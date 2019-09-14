package com.sam.backendv2.dao;

import org.apache.ibatis.annotations.*;
import com.sam.backendv2.entity.Role;

import java.util.List;


@Mapper
public interface RoleDao {

    String tableFields = "id,username,role_name";
    String tableName = "username_role";

    @Select("SELECT " + tableFields + " FROM " + tableName + " WHERE username =#{username}")
    Role selectAllByUsername(@Param("username") String username);

    @Select("SELECT " + tableFields + " FROM " +  tableName + " WHERE username LIKE #{username} ORDER BY #{field} #{order} LIMIT #{offset},#{rowcount}")
    List<Role> selectAllByUsernameLimit(@Param("username") String username, @Param("field") String field, @Param("order") String order, @Param("offset") int offset, @Param("rowcount") int rowcount);

    @Select("SELECT " + tableFields + " FROM " + tableName + " WHERE id =#{id}")
    Role selectAllById(int id);

    @Select("SELECT " + tableFields + " FROM " +  tableName + " ORDER BY #{field} #{order} LIMIT #{offset},#{rowcount}")
    List<Role> selectAllLimit(@Param("field") String field, @Param("order") String order, @Param("offset") int offset, @Param("rowcount") int rowcount);

    @Select("SELECT count(username)  FROM " +  tableName)
    Integer countAll();

    @Select("SELECT count(username)  FROM " +  tableName + " WHERE username LIKE #{username}")
    Integer countByUsername(String username);

    @Update("UPDATE " + tableName + " SET username = #{username}, role_name =#{role_name} WHERE id=#{id}")
    void updateRecordById(@Param("username") String username, @Param("role_name") String roleName, @Param("id") Integer id);

    @Insert("INSERT INTO " + tableName + "(username,role_name) VALUES(#{username}, #{role_name})")
    void createRecord(@Param("username") String username, @Param("role_name") String roleName);

    @Delete("DELETE FROM " + tableName + " WHERE id=#{id}")
    void deleteRecordById(int id);
}
