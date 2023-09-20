package com.babasnack.demo.pet.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access=AccessLevel.PRIVATE)
public class PetDto {
	
	@Data
	public static class PsProfile{
		private String petName;
		private String username;
		private String petType;
		private Long petAge;
		private Long petSex;
	}
}
