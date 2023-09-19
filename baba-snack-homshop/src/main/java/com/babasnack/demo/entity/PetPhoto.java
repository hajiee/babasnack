package com.babasnack.demo.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetPhoto {

	private Long petProno;
	private String petName;
	private String username;
	private String petImg;
	private String petSaveImg;
}
