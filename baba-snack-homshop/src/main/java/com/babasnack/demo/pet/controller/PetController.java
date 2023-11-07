package com.babasnack.demo.pet.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.babasnack.demo.entity.Pet;
import com.babasnack.demo.pet.dto.PetDto;
import com.babasnack.demo.pet.service.PetService;

@Controller
@RequestMapping("/member")
public class PetController {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping("/user-profile")
    public ResponseEntity<String> savePets(@RequestBody PetDto.PsProfile profile) {
        Integer result = petService.savePet(profile);
        if (result > 0) {
            return ResponseEntity.ok("반려동물이 성공적으로 저장되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("반려동물 저장에 실패하였습니다.");
        }
    }

    @GetMapping("/pet/{petName}")
    public ResponseEntity<Pet> getPetsByName(@PathVariable String petName) {
        Pet pet = petService.getPetByName(petName);
        if (pet != null) {
            return ResponseEntity.ok().body(pet);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/pet/types")
    public ResponseEntity<String[]> getPetTypes() {
        String[] types = petService.getPetTypes();
        if (types != null) {
            return ResponseEntity.ok().body(types);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/pet/sexes")
    public ResponseEntity<String[]> getPetSexes() {
        String[] sexes = petService.getPetSexes();
        if (sexes != null) {
            return ResponseEntity.ok().body(sexes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/pet/{petName}/sex")
    public ResponseEntity<String> setPetGender(@PathVariable String petName, @RequestBody String gender) {
        boolean success = petService.setPetSex(petName, gender);
        if (success) {
            return ResponseEntity.ok("반려동물 성별이 성공적으로 업데이트되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("반려동물 성별 업데이트에 실패하였습니다.");
        }
    }
    
    @PostMapping("/pet/{petName}/type")
    public ResponseEntity<String> setPetType(@PathVariable String petName, @RequestBody String type) {
        Integer success = petService.changePetType(type, petName);
        if (success > 0) {
            return ResponseEntity.ok("반려동물 종류가 성공적으로 업데이트되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("반려동물 종류 업데이트에 실패하였습니다.");
        }
    }
}
