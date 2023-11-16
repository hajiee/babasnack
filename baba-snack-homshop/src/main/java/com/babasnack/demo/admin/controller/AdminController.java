package com.babasnack.demo.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.babasnack.demo.board.service.BoardAdminService;
import com.babasnack.demo.entity.Board;
import com.babasnack.demo.entity.Member;
import com.babasnack.demo.entity.Product;
import com.babasnack.demo.member.service.MemberAdminService;
import com.babasnack.demo.product.Service.ProductAdminService;

@Secured("ROLE_ADMIN")
@Controller
public class AdminController {
	@Autowired
	private BoardAdminService boardAdminService;
	@Autowired
	private MemberAdminService memberAdminService;
	@Autowired
	private ProductAdminService productAdminService;
	
	@GetMapping("/admin")
    public String adminPage(Model model) {
        // 필요한 데이터를 모델에 추가하여 JSP 파일로 전달할 수 있습니다.
        model.addAttribute("message", "관리자 페이지입니다");
        
        return "admin"; // 관리자 페이지 뷰 이름 반환
    }
    
    @GetMapping("/member/admin-member")
    public String adminMemberList(Model model) {
        // 회원 관리 페이지로 이동하는 로직
    	List<Member> members = memberAdminService.getAllMember();
        model.addAttribute("members", members);
        return "member/admin-member"; // 회원 관리 페이지 뷰 이름 반환
    }

	@GetMapping("/product/admin-product")
    public String adminProductList(Model model) {
        List<Product> products = productAdminService.getAllProducts();
        // 관리자용 상품 목록을 모델에 추가
        model.addAttribute("products", products);
        return "product/admin-product";
    }

	@GetMapping("/board/admin-board")
    public String adminBoardList(Model model) {
        List<Board> boards = boardAdminService.getAllBoardsForAdmin();
        // 관리자용 게시글 목록을 모델에 추가
        model.addAttribute("boards", boards);
        return "board/admin-board";
    }
}
