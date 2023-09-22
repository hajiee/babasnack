package com.babasnack.demo.pet.service;

import org.springframework.stereotype.Service;

import com.babasnack.demo.entity.Pet;
import com.babasnack.demo.pet.dao.PetDao;
import com.babasnack.demo.pet.dto.PetDto;

@Service
public class PetService {
    private final PetDao petDao;

    public PetService(PetDao petDao) {
        this.petDao = petDao;
    }

    public Integer savePet(PetDto.PsProfile petProfile) {
        Pet pet = new Pet();
        pet.setPetName(petProfile.getPetName());
        pet.setUsername(petProfile.getUsername());
        pet.setPetType(petProfile.getPetType());
        pet.setPetAge(petProfile.getPetAge());

        // boolean 값을 설정
        pet.setPetSex(petProfile.getPetSex());

        return petDao.save(pet);
    }

    public Pet getPetByName(String username) {
        return petDao.getPetByName(username);
    }

    


   // 추가적인 메서드에 대한 Service 코드를 작성해주세요
}
