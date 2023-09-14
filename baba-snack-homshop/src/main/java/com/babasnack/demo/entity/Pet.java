package com.babasnack.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
	private String petName;
	private String username;
	private String petType;
	private Long petAge;
	private Boolean petSex;
}
