package com.babasnack.demo.product.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.babasnack.demo.entity.Product;
import com.babasnack.demo.product.Service.ProductService;
import com.babasnack.demo.product.dto.Category;

@Controller
@RequestMapping("/products")
public class ProductController {
	 @Autowired
	    private ProductService productService;
	 
	 // 상품목록 (고양이 카테고리)
	    @GetMapping("/product-cat")
	    public ModelAndView getCatProducts() {
	        List<Product> catProducts = productService.getCatProducts(Category.CAT);
	        ModelAndView modelAndView = new ModelAndView("product/cat");
	        modelAndView.addObject("products", catProducts);
	        return modelAndView;
	    }

	    // 상품목록 (강아지 카테고리)
	    @GetMapping("/product-dog")
	    public ModelAndView getDogProducts() {
	        List<Product> dogProducts = productService.getDogProducts(Category.DOG);
	        ModelAndView modelAndView = new ModelAndView("product/dog");
	        modelAndView.addObject("products", dogProducts);
	        return modelAndView;
	    }

	    // 상품 조회(상세정보)
	    @GetMapping("/product-read")
	    public ModelAndView getProductById(@PathVariable Long id) {
	        Product product = productService.getProductById(id);
	        ModelAndView modelAndView = new ModelAndView("product/detail"); // View의 이름
	        modelAndView.addObject("product", product);
	        return modelAndView;
	    }
	   
}
