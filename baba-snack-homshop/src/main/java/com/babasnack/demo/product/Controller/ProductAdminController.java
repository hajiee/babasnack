package com.babasnack.demo.product.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.babasnack.demo.entity.Product;
import com.babasnack.demo.entity.ProductPhoto;
import com.babasnack.demo.product.Service.ProductAdminService;
import com.babasnack.demo.product.Service.ProductPhotoService;
import com.babasnack.demo.product.dto.Category;
import com.babasnack.demo.product.dto.ProductDto;

@Secured("ROLE_ADMIN")
@Controller
@RequestMapping
public class ProductAdminController {
	@Autowired
	private ProductAdminService productAdminService;
	@Autowired
	private ProductPhotoService productPhotoService;

	// 상품 등록 페이지로 이동
	@GetMapping("/product/product-write")
	public String showAddProductForm(Model model) {
		model.addAttribute("categories", Category.values());
		return "product/product-write"; // product-write.jsp로 변경
	}

	// 상품 등록 처리
	@PostMapping("/product/add")
	public ModelAndView addProduct(@ModelAttribute("productDto") ProductDto.WriteP productDto,
	        @RequestParam("productPhoto") List<MultipartFile> productPhotos) {
	    List<ProductPhoto> photos = new ArrayList<>();

	    for (MultipartFile photo : productPhotos) {
	        if (!photo.isEmpty()) {
	            ProductPhoto productPhoto = new ProductPhoto();
	            productPhoto.setProductImgNo(null); // Set the productImgNo as needed
	            productPhoto.setProductImg(photo.getOriginalFilename());
	            productPhoto.setProductSaveImg(productPhotoService.saveFile(photo));

	            
	            productPhoto.setProductImg(photo.getOriginalFilename());
	            photos.add(productPhoto);
	        }
	    }
	    Long productId = productAdminService.addProduct(productDto, photos);

	    return new ModelAndView("redirect:/product/admin-product/" + productId);
	}

	@GetMapping("/product/product-read/{pno}")
	public String showProductDetails(@PathVariable("pno") String pno, Model model) {
	    try {
	        Long productId = Long.parseLong(pno);
	        Product product = productAdminService.getProductById(productId);
	        model.addAttribute("product", product);
	        return "product/product-read"; // product-read.jsp로 변경
	    } catch (NumberFormatException e) {
	        // 숫자 형식의 상품 번호가 아닌 경우 에러 처리
	        return "error-page"; // 에러 페이지로 리다이렉트 또는 에러 메시지를 표시하는 방식으로 처리해야 합니다.
	    }
	}

	@GetMapping("/product/product-write/{pno}")
	public String showEditProductForm(@PathVariable("pno") String pno, Model model) {
	    try {
	        Long productId = Long.parseLong(pno);
	        Product product = productAdminService.getProductById(productId);
	        model.addAttribute("product", product);
	        model.addAttribute("categories", Category.values());
	        return "product/product-write"; 
	    } catch (NumberFormatException e) {
	       // 숫자 형식의 상품 번호가 아닌 경우 에러 처리
	       return "error-page"; // 에러 페이지로 리다이렉트 또는 에러 메시지를 표시하는 방식으로 처리해야 합니다.
	   }
	}

	// 상품 수정 처리
	@PostMapping("/product/{pno}/product-edit")
	public ModelAndView editProduct(@PathVariable("pno") Long pno,
			@ModelAttribute("productDto") ProductDto.WriteP productDto,
			@RequestParam("photos") List<ProductPhoto> photos) {
		Long updatedProductId = productAdminService.updateProduct(pno, productDto, photos);
		return new ModelAndView("redirect:/product/admin-product/" + updatedProductId);
	}

	// 상품 삭제 처리
	@PostMapping("/product/{pno}/delete")
	public ModelAndView deleteProduct(@PathVariable("pno") Long pno) {
		productAdminService.deleteProduct(pno);
		return new ModelAndView("redirect:/product/admin-product/");
	}
}
