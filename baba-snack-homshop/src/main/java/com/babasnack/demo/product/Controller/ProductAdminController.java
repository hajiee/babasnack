package com.babasnack.demo.product.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.babasnack.demo.product.Service.ProductAdminService;
import com.babasnack.demo.product.dto.ProductDto;

@Secured("ROLE_ADMIN")
@Controller
@RequestMapping("/products")
public class ProductAdminController {
	@Autowired
	private ProductAdminService productAdminService;
	
	 // 상품 등록, 수정, 삭제화면
    @GetMapping("/product-write")
    public ModelAndView showProductForm() {
        ModelAndView modelAndView = new ModelAndView("product/manage"); // 상품 관리 페이지 템플릿
        return modelAndView;
    }

    // 추가상품저장 > 상품관리페이지
    @PostMapping("/add")
    public String addProduct(@ModelAttribute("productDto") ProductDto.WriteP productDto) {
        productAdminService.addProduct(productDto.toProduct());
        return "redirect:/products/admin-product"; // 상품 관리 페이지로 리다이렉트
    }
    
    // 수정정보저장 > 상품관리페이지
    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute("productDto") ProductDto.WriteP productDto) {
    	productAdminService.updateProduct(productDto.toProduct());
        return "redirect:/products/admin-product"; // 상품 목록으로 리다이렉트
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        // ProductService에서 상품 및 관련된 사진과 리뷰 삭제
    	productAdminService.deleteProduct(id);
        return "redirect:/products/admin-product";  // 상품 목록 페이지로 리다이렉트
    }
}
