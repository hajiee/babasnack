package com.babasnack.demo.reserve.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access=AccessLevel.PRIVATE)
public class ReserveDto {
	
	@Data
	public static class PsReserve{
	 private String username;
	 private Long firstReserve;
	 private Long reservePlus;

	    // 생성자, getter, setter 생략

	    // 필요에 따라 추가적인 메서드들을 구현할 수 있습니다.
	}
}
