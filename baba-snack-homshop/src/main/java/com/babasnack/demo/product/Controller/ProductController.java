package com.babasnack.demo.product.Controller;

import java.util.List;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.babasnack.demo.entity.Product;
import com.babasnack.demo.product.Service.ProductService;
import com.babasnack.demo.product.dto.ProductDto;

@Controller
public class ProductController {
	private final ProductService productService;

    @Autowired 
    public ProductController(ProductService productService) {
        this.productService = productService; 
    }

    // 상품 목록 조회 API 엔드 포인트(GET /products)
    @GetMapping("/product")
    public ModelAndView getProductList(@RequestParam("category") String category) {
        List<Product> productList = productService.getProductList();

        String viewName;
        if ("dog".equals(category)) {
            viewName = "product-dog";
        } else if ("cat".equals(category)) {
            viewName = "product-cat";
        } else {
            viewName = "product-list"; // 기본적으로는 product-list로 설정
        }

       ModelAndView modelAndView = new ModelAndView(viewName);
       modelAndView.addObject("products", productList);

       return modelAndView;
    }

	// 특정 상품 조회 API 엔드 포인트(GET /products/{pno})
    @GetMapping("/product/{pno}")
    public ModelAndView getProduct(Long pno) throws NotFoundException {
        ProductDto.ReadP productDetail = productService.getProductDetail(pno);
        
        ModelAndView modelAndView = new ModelAndView("product-details");
        modelAndView.addObject("product", productDetail);
        
        return modelAndView;
     }

     // 한 페이지당 상품 수 조회 API 엔드 포인트(GET /products/page/{page}/{size})
     @GetMapping("/product/page/{page}/{size}")
     public ModelAndView getPageOne(
             @PathVariable int page,
             @PathVariable int size ) {

         Long startRowNum = ((long) page - 1L) * size + 1L;  
         Long endRowNum = startRowNum + size - 1L;  

         List<Product> productList = productService.getPageOne(startRowNum, endRowNum);  
         ModelAndView modelAndView = new ModelAndView("product-page");
         modelAndView.addObject("products", productList);
         
         return modelAndView;
     }
}
