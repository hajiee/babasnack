package com.babasnack.demo.petphoto.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.babasnack.demo.entity.PetPhoto;

@Mapper
public interface PetPhotoDao {

@Insert("insert into petphoto values(image_seq.nextval,#{pet_name},#{username},#{pet_type},#{pet_age},#{pet_sex}")
public Integer save(PetPhoto petphoto);

@Select("select name from petphoto where pet_prono")
public List<String> findByPetPno(Long petpno);

@Update("update psprofile set psImg=#{pet_img} where username=#{username}")
public Integer PsChangePhoto(String petImg,String username);

} 
