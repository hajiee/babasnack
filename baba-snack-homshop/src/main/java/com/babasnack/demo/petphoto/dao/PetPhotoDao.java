package com.babasnack.demo.petphoto.dao;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.babasnack.demo.entity.Pet;
import com.babasnack.demo.entity.PetPhoto;

@Mapper
public interface PetPhotoDao {
    @Insert("INSERT INTO pet_photo (pet_prono, pet_name, username, pet_img, pet_save_img) " +
            "VALUES (#{petProno}, #{petName}, #{username}, #{petImg}, #{petSaveImg})")
    public Integer savePetPhoto(PetPhoto petphoto);

    @Select("SELECT pet_name FROM pet_photo WHERE pet_prono = #{petprono}")
    public Optional<Pet> findByPetPno(Long petprono);
    
    @Select("SELECT * FROM pet_photo WHERE username = #{username}")
    public List<PetPhoto> findPetPhotosByUsername(String username);

    @Select("SELECT * FROM pet_photo WHERE pet_type = #{petType}")
    public List<PetPhoto> findPetPhotosByType(String petType);

    @Select("SELECT * FROM pet_photo WHERE pet_age = #{petAge}")
    public List<PetPhoto> findPetPhotosByAge(String petAge);

    @Select("SELECT * FROM pet_photo WHERE pet_sex = #{petSex}")
    public List<PetPhoto> findPetPhotosBySex(String petSex);

    @Update("UPDATE psprofile SET psImg = #{petImg} WHERE username = #{username}")
    public Integer PsChangePhoto(String petImg, Long petprono);
    
    
}