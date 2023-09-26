package com.babasnack.demo.reserve.dao;

import org.apache.ibatis.annotations.*;

import com.babasnack.demo.entity.Reserve;

@Mapper
public interface ReserveDao {

    @Select("SELECT * FROM reserve WHERE username = #{username}")
    Reserve getReserveByUsername(String username);

    @Insert("INSERT INTO reserve (username, reserve_amount) VALUES (#{username}, #{reserveAmount})")
    void createReserve(Reserve reserve);

    @Update("UPDATE reserve SET reserve_amount = #{reserveAmount} WHERE username = #{username}")
    void updateReserveByUsername(@Param("username") String username, @Param("reserveAmount") Long reserveAmount);
}
