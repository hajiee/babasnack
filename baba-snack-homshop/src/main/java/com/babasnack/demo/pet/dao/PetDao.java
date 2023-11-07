package com.babasnack.demo.pet.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.babasnack.demo.entity.Pet;

@Mapper
public interface PetDao {
    @Insert("insert into pet values(#{petName},#{username},#{petType},#{petAge},#{petSex})")
    public Integer save(Pet pet);

    @Select("select * from pet where pet_name=#{petName} and rownum=1")
    public Pet findByPetName(String petName);

    @Select("select * from pet where pet_age=#{petAge} and rownum=1")
    public Pet findByPetAge(Long petAge);

    @Select("select * from pet where pet_sex=#{petSex} and rownum=1")
    public Pet findByPetSex(String petSex);

    @Select("select * from pet where username=#{username} and rownum=1")
    public Pet getPetByName(String username);

    @Update("update pet set pet_age=#{petAge} where pet_name=#{petName}")
    public Integer ChangePetAge(Long petAge, String petName);

    @Update("update pet set pet_type=#{petType} where pet_name=#{petName}")
    public Integer ChangePetType(String petType, String petName);

    @Update("update pet set pet_sex=#{petSex} where pet_name=#{petName}")
    public Integer ChangePetSex(boolean petSex, String petName);

    @Select("select distinct pet_type from pet")
    public String[] getPetTypes();
    
    
    @Select("select distinct pet_sex from pet")
    public String[] getPetSex();
}
