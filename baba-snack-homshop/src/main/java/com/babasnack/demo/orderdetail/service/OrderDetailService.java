package com.babasnack.demo.orderdetail.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.babasnack.demo.entity.Member;
import com.babasnack.demo.entity.OrderDetail;
import com.babasnack.demo.orderdetail.dao.OrderDetailDao;
import com.babasnack.demo.orderdetail.dto.OrderDetailDto;

@Service
public class OrderDetailService {
	@Autowired
	private OrderDetailDao orderDetailDao;

	// 주문상세 정보 저장
	public Boolean add(OrderDetail orderDetail, String username) {
		return orderDetailDao.addOrderDetail(orderDetail, username) == 1;
	}

	// 회원 정보 읽기(member 테이블)
	public OrderDetailDto.ReadMemberProfile readMemberProfile(String username) {
		Member member = orderDetailDao.findUsernameFromMember(username);
		return new OrderDetailDto.ReadMemberProfile(member.getUsername(), member.getPnoTell(), member.getEmail());		
	}


	// 회원 주문자 정보(관리자)
	public OrderDetailDto.ReadOrderDetailMember orderDetailMember(String username, Long odno) {
		return orderDetailDao.findByUsernameAndOdno(username, odno);
	}

	// 회원 주문목록(관리자)
	public List<OrderDetailDto.ReadOrderDetailAdmin> orderDetailAdmin(String username, Long ono) {
		return orderDetailDao.orderDetailAdmin(username, ono);
	}

}
