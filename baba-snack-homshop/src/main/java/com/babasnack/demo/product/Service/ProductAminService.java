package com.babasnack.demo.product.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babasnack.demo.product.dao.ProductAdminDao;

@Service
public class ProductAminService {
	@Autowired
	private ProductAdminDao productAdminDao;
}
