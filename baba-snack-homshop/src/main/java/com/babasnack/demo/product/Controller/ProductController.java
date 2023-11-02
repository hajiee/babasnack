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
import com.babasnack.demo.product.dto.ProductDto.ReadP;
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
	
	@GetMapping("/product/product-dog" )
	public String showDogProductList(Model model) {
	     List<Product> dogProducts = productService.getProductListByCategory("dog"); // 개 카테고리 상품 목록을 가져옵니다.
	     model.addAttribute("products", dogProducts);
	     return "product/product-dog";
	}

	@GetMapping("/product/product-cat" )
	public String showCatProductList(Model model) {
	     List<Product> catProducts = productService.getProductListByCategory("cat"); // 고양이 카테고리 상품 목록을 가져옵니다.
	     model.addAttribute("products", catProducts);
	     return "product/product-cat";
	}

	// 상품 목록 조회 API 엔드 포인트(GET /products)
	@GetMapping("/product")
	public String getProductList(@RequestParam(value = "category", required = false, defaultValue = "") String category,
	                             @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
	                             Model model) {
	    List<Product> productList;

	    if (!category.isEmpty() && ("dog".equals(category) || "cat".equals(category))) {
	        productList = productService.getProductListByCategory(category);
	        if ("dog".equals(category)) {
	            return "product/product-dog";
	        } else if ("cat".equals(category)) {
	            return "product/product-cat";
	        }
	    } else if (!keyword.isEmpty()) {
	        productList = productService.getProductListByKeyword(keyword);
	    } else {
	        productList = productAdminService.getAllProducts();
	        category = ""; // 기본값으로 카테고리를 빈 문자열로 설정
	    }

	    model.addAttribute("products", productList);
	    model.addAttribute("category", category); // 카테고리 정보도 함께 전달

	    return "product/product-list";
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
	
	@GetMapping("/product/product-read")
	public String showProductDetails(@RequestParam("pno") Long pno, Model model) {
	    try {
	        ReadP product = productService.getProductDetail(pno);
	        if (product == null) {
	            // 상품이 존재하지 않을 경우 에러 처리
	            return "error-page"; // 에러 페이지로 리다이렉트 또는 에러 메시지를 표시하는 방식으로 처리해야 합니다.
	        }
	        model.addAttribute("product", product);
	        return "product/product-read"; // product-read.jsp로 변경
	    } catch (NumberFormatException e) {
	        // 숫자 형식의 상품 번호가 아닌 경우 에러 처리
	        return "error-page"; // 에러 페이지로 리다이렉트 또는 에러 메시지를 표시하는 방식으로 처리해야 합니다.
	    }
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
			ProductPage productPage = productService.page(pageno);
			model.addAttribute("start", 1L);
			model.addAttribute("end", productPage.getEnd() - productPage.getStart() + 1);
			model.addAttribute("prev", productPage.getPrev());
			model.addAttribute("next", productPage.getNext());
			model.addAttribute("pageno", pageno);
			model.addAttribute("products", productPage.getProducts());

			return "product/product-list"; 
	}
	
	// 관리자 상품 page
	@GetMapping("/product/admin-product/page/{pageno}")
	public String getProductAdminPage(@PathVariable Long pageno, Model model){
	    ProductPage productPage = productService.adminList(pageno); // adminList() 메서드 호출
	    model.addAttribute("start", productPage.getStart());
	    model.addAttribute("end", productPage.getEnd());
	    model.addAttribute("prev", productPage.getPrev());
	    model.addAttribute("next", productPage.getNext());
	    model.addAttribute("pageno", pageno);
	    model.addAttribute("products", productPage.getProducts());

	    return "product/admin-product/page";
	}
}
