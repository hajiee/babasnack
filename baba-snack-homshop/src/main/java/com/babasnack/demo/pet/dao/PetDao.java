package com.babasnack.demo.pet.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
	public Pet findByPetSex(Long petSex);
	
}
