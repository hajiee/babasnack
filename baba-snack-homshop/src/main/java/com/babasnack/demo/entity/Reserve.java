package com.babasnack.demo.entity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reserve {
	private String username;
	private Long firstReserve;
	private Long reservePlus;
	private LocalDate reserveDay;
	private Long ono;
	private Long amount;

	    public Reserve(String username, Long amount) {
	        this.username = username;
	        this.amount = amount;
	    }

	    // Getter와 Setter 메서드 생략
	    @Override
	    public String toString() {
	        return "Reserve{" +
	                "username='" + username + '\'' +
	                ", amount=" + amount +
	                '}';
	    }
}
