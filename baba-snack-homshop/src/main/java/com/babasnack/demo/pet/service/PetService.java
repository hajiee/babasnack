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

        // 문자열 값을 설정
        pet.setPetSex(petProfile.getPetSex());

        return petDao.save(pet);
    }

    public Pet getPetByName(String username) {
        return petDao.getPetByName(username);
    }

    public String[] getPetTypes() {
        return petDao.getPetTypes();
    }

    public String[] getPetSexes() {
        return petDao.getPetSex();
    }

    public boolean setPetSex(String petName, String sex) {
        Pet pet = petDao.findByPetName(petName);
        if (pet != null) {
            // 문자열 값을 Boolean으로 변환하여 설정
            Boolean petSex = Boolean.valueOf(sex);
            pet.setPetSex(petSex);
            petDao.save(pet);
            return true;
        }
        return false;
    }

    public Integer changePetAge(Long petAge, String petName) {
        return petDao.ChangePetAge(petAge, petName);
    }

    public Integer changePetType(String petType, String petName) {
        return petDao.ChangePetType(petType, petName);
    }
}
