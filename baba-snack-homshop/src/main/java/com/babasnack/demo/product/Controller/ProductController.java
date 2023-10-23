package com.babasnack.demo.product.Controller;

import java.util.Collections;
import java.util.List;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.babasnack.demo.entity.Product;
import com.babasnack.demo.product.Service.ProductAdminService;
import com.babasnack.demo.product.Service.ProductService;
import com.babasnack.demo.product.dto.ProductPage;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;

	@Autowired
	private ProductAdminService productAdminService;

	// Main으로 이동
	@GetMapping({ "/", "/main" })
	public String showAllProducts(Model model) {
		List<Product> products = productAdminService.getAllProducts();
		model.addAttribute("products", products);
		return "/main";
	}

	// 상품 목록 조회 API 엔드 포인트(GET /products)
	@GetMapping("/product")
	public ModelAndView getProductList(@RequestParam(value = "category", required = false, defaultValue = "") String category) {
	    List<Product> productList;
	    
	    if (!category.isEmpty() && ("dog".equals(category) || "cat".equals(category))) {
	        productList = productService.getProductList(category);
	    } else {
	        productList = productAdminService.getAllProducts();
	        category = ""; // 기본값으로 카테고리를 빈 문자열로 설정
	    }
	    
	    ModelAndView modelAndView = new ModelAndView("product/product-list");
	    modelAndView.addObject("products", productList);
	    modelAndView.addObject("category", category); // 카테고리 정보도 함께 전달

	    return modelAndView;
	}
	
	// 상품명으로 상품 조회 API 엔드 포인트(GET /products/{productName})
	@GetMapping("/product/name/{productName}")
	public ModelAndView getProductByName(@PathVariable String productName) throws NotFoundException {
	    Product product = productService.getProductByProductName(productName);
	    if (product == null) {
	        // 상품이 존재하지 않는 경우 에러 처리
	        throw new NotFoundException("상품을 찾을 수 없습니다.");
	    }

	    ModelAndView modelAndView = new ModelAndView("product/product-list");
	    modelAndView.addObject("products", Collections.singletonList(product));

	    return modelAndView;
	}

	// 특정 상품 번호조회 API 엔드 포인트(GET /products/{pno})
	@GetMapping("/product/{pno}")
	public ModelAndView getProductByPno(@PathVariable Long pno) throws NotFoundException {
	    Product product = productService.getProductByPno(pno);
	    if (product == null) {
	        // 상품이 존재하지 않는 경우 에러 처리
	        throw new NotFoundException("상품을 찾을 수 없습니다.");
	    }

	    ModelAndView modelAndView = new ModelAndView("product/product-list");
	    modelAndView.addObject("products", Collections.singletonList(product));

	    return modelAndView;
	}

	// 한 페이지당 상품 수 조회 API 엔드 포인트(GET /products/page/{page}/{size})
	@GetMapping("/product/page/{page}/{size}")
	public ResponseEntity<List<Product>> getPageOne(@PathVariable int page, @PathVariable int size) {

		Long startRownum = ((long) page - 1L) * size + 1L;
		Long endRownum = startRownum + size - 1L;

		List<Product> productList= productService.getPageOne(startRownum, endRownum); 
    	
    	return ResponseEntity.ok(productList);
	}

	// 상품 page
	@GetMapping("/products/page/{pageno}")
	   public String getProductPage(@PathVariable Long pageno, Model model){
	       ProductPage productPage= productService.page(pageno); 
	       model.addAttribute("start", productPage.getStart()); 
	       model.addAttribute("end", productPage.getEnd()); 
	       model.addAttribute("prev", productPage.getPrev()); 
	       model.addAttribute("next", productPage.getNext());
	       model.addAttribute("pageno", pageno); 
	       model.addAttribute("products", productPage.getProducts());
	       
	      return "product/product-list"; 
	}
	
}
