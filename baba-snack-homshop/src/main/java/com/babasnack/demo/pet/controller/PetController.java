package com.babasnack.demo.pet.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.babasnack.demo.entity.Pet;
import com.babasnack.demo.pet.dto.PetDto;
import com.babasnack.demo.pet.service.PetService;

@Controller
@RequestMapping("/pets")
public class PetController {
    private final PetService petservice;

    public PetController(PetService petservice) {
        this.petservice = petservice;
    }

   @PostMapping("/")
   public ResponseEntity<String> savePets(@RequestBody PetDto.PsProfile profile) {
       Integer result = petservice.savePet(profile);
       if (result > 0) {
           return ResponseEntity.ok("Pets saved successfully");
       } else {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save pets");
       }
   }
   
   @GetMapping("/petname/{petName}")
   public ResponseEntity<Pet> getPetsByName(@PathVariable String name){
     	Pet pet=petservice.getPetByName(name); 
	if (pet != null){
		return ResponseEntity.ok().body(pet); 
	} else{
		return ResponseEntity.notFound().build(); 
	}
	
   }
   
  // 추가적인 메서드에 대한 Controller 코드를 작성해주세요.
}
