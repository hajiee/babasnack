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
@RequestMapping("/product")
public class ProductAdminController {
	@Autowired
	private ProductAdminService productAdminService;
	@Autowired
	private ProductPhotoService productPhotoService;

	// 상품 등록 페이지로 이동
	@GetMapping("/product-write")
	public String showAddProductForm(Model model) {
		model.addAttribute("categories", Category.values());
		return "product/product-write"; // product-write.jsp로 변경
	}

	// 상품 등록 처리
	@PostMapping("/add")
	public ModelAndView addProducts(@ModelAttribute("productDto") ProductDto.WriteP productDto,
	        @RequestParam("photos") List<MultipartFile> uploadedPhotos) {
	    List<ProductPhoto> productPhotos = convertToProductPhotos(uploadedPhotos);
	    productDto.setProductPhotos(productPhotos);

	    List<MultipartFile> validPhotos = new ArrayList<>(); // 유효한 사진을 저장할 리스트

	    for (MultipartFile photo : uploadedPhotos) {
	        if (!photo.isEmpty()) {
	            ProductPhoto productPhoto = new ProductPhoto();
	            productPhoto.setProductImg(photo.getOriginalFilename());
	            productPhoto.setProductSaveImg(productPhotoService.saveFile(photo));
	            productPhotos.add(productPhoto);
	            validPhotos.add(photo); // 유효한 사진만 추가
	        }
	    }

	    Category category = productDto.getCategory();  // 이미 Category 열거형으로 선언된 값

	    // 상품 객체 생성 및 필드 값 설정
	    Product product = new Product();
	    // 다른 필드 값 설정
	    product.setProductName(productDto.getProductName());
	    product.setProductNotice(productDto.getProductNotice());
	    
		// category 필드 값 설정
		product.setCategory(category);

	    Long newProductId = productAdminService.addProduct(productDto, validPhotos);

	    System.out.println(newProductId);
	    // 새로 생성된 상품 번호를 상품 상세 페이지로 리다이렉트
	    //return new ModelAndView("redirect:/product/admin-product/" + newProductId);
	    return new ModelAndView("redirect:/product/admin-product/");
	}
	

	@GetMapping("/{pno}/product-edit")
	public String showEditProductForm(@PathVariable("pno") Long pno, Model model) {
	    Product product = productAdminService.getProductById(pno);
	    if (product == null) {
	        // 상품이 존재하지 않을 경우 에러 처리
	        return "error-page"; // 에러 페이지로 리다이렉트 또는 에러 메시지를 표시하는 방식으로 처리해야 합니다.
	    }
	    
	    model.addAttribute("product", product);
	    model.addAttribute("categories", Category.values());
	    return "product/product-edit";
	}

	// 상품 수정 처리
	@PostMapping("/{pno}/save")
	public ModelAndView editProduct(@PathVariable("pno") Long pno,
	                                @ModelAttribute("productDto") ProductDto.WriteP productDto,
	                                @RequestParam("photos") List<MultipartFile> uploadedPhotos) {
	    List<ProductPhoto> photos = new ArrayList<>();

	    for (MultipartFile photo : uploadedPhotos) {
	        if (!photo.isEmpty()) {
	            ProductPhoto productPhoto = new ProductPhoto();
	            productPhoto.setProductImg(photo.getOriginalFilename());
	            productPhoto.setProductSaveImg(productPhotoService.saveFile(photo));
	            photos.add(productPhoto);
	        }
	    }

	    Long updatedProductId = productAdminService.updateProduct(pno, productDto, uploadedPhotos);
	    return new ModelAndView("redirect:/product/admin-product/" + updatedProductId);
	}
	
	private List<ProductPhoto> convertToProductPhotos(List<MultipartFile> uploadedPhotos) {
        List<ProductPhoto> photos = new ArrayList<>();

        for (MultipartFile photo : uploadedPhotos) {
            if (!photo.isEmpty()) {
                ProductPhoto productPhoto = new ProductPhoto();
                productPhoto.setProductImg(photo.getOriginalFilename());
                productPhoto.setProductSaveImg(productPhotoService.saveFile(photo));
                photos.add(productPhoto);
            }
        }

        return photos;
    }

	// 상품 삭제 처리
	@PostMapping("/{pno}/delete")
	public ModelAndView deleteProduct(@PathVariable("pno") Long pno) {
		productAdminService.deleteProduct(pno);
		return new ModelAndView("redirect:/product/admin-product/");
	}
}
