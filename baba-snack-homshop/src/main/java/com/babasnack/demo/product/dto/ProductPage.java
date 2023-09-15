package com.babasnack.demo.product.dto;

import java.util.List;

import com.babasnack.demo.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductPage {
	private Long prev;
	private Long start;
	private Long end;
	private Long next;
	private Long pageno;
	private List<Product> products;
}
