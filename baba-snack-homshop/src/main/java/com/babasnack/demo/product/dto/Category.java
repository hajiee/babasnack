package com.babasnack.demo.product.dto;

public enum Category {
	CAT("고양이"),DOG("강아지");

	private final String koreanName;

    Category(String koreanName) {
        this.koreanName = koreanName;
    }

    public String getKoreanName() {
        return koreanName;
    }
}

// 출력
// Category cat = Category.CAT;
// Category dog = Category.DOG;
