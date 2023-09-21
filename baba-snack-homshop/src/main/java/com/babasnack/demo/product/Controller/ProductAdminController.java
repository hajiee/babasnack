package com.babasnack.demo.product.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.babasnack.demo.entity.Product;
import com.babasnack.demo.entity.ProductPhoto;
import com.babasnack.demo.product.Service.ProductAdminService;
import com.babasnack.demo.product.dto.Category;
import com.babasnack.demo.product.dto.ProductDto;

@Controller
@RequestMapping("/product")
public class ProductAdminController {
	@Autowired
	private ProductAdminService productAdminService;

	// 상품 등록 페이지로 이동
	@GetMapping("/product-write")
	public String showAddProductForm(Model model) {
		model.addAttribute("categories", Category.values());
		return "product/product-write";
	}

	// 상품 등록 처리
	@PostMapping("/add")
	public ModelAndView addProduct(@ModelAttribute("productDto") ProductDto.WriteP productDto,
			@RequestParam("photos") List<ProductPhoto> photos) {
		Long productId = productAdminService.addProduct(productDto, photos);

		return new ModelAndView("redirect:/product/admin-product/" + productId);
	}

	// 상품 상세 페이지로 이동
	@GetMapping("/{pno}")
	public String showProductDetails(@PathVariable("pno") Long pno, Model model) {
		Product product = productAdminService.getProductById(pno);
		model.addAttribute("product", product);
		return "product/product-read";
	}

	// 상품 수정 페이지로 이동
	@GetMapping("/prouct-write/{pno}")
	public String showEditProductForm(@PathVariable("pno") Long pno, Model model) {
		Product product = productAdminService.getProductById(pno);
		model.addAttribute("product", product);
		model.addAttribute("categories", Category.values());
		return "product/prouct-write";
	}

	// 상품 수정 처리
	@PostMapping("/{pno}/edit")
	public ModelAndView editProduct(@PathVariable("pno") Long pno,
			@ModelAttribute("productDto") ProductDto.WriteP productDto,
			@RequestParam("photos") List<ProductPhoto> photos) {
		Long updatedProductId = productAdminService.updateProduct(pno, productDto, photos);
		return new ModelAndView("redirect:/product/admin-product/" + updatedProductId);
	}

	// 상품 삭제 처리
	@PostMapping("/{pno}/delete")
	public ModelAndView deleteProduct(@PathVariable("pno") Long pno) {
		productAdminService.deleteProduct(pno);
		return new ModelAndView("redirect:/product/admin-product/");
	}

	// Main으로 이동
	@GetMapping({ "/", "/main" })
	public String showAllProducts(Model model) {
		List<Product> products = productAdminService.getAllProducts();
		model.addAttribute("products", products);
		return "/";
	}
}
