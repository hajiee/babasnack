package com.babasnack.demo.delivery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.babasnack.demo.delivery.dao.DeliveryDao;
import com.babasnack.demo.delivery.dto.DeliveryDto;
import com.babasnack.demo.entity.Delivery;
import com.babasnack.demo.entity.Member;

@Service
public class DeliveryService {
	@Autowired
	private DeliveryDao deliveryDao;	

	
	// 회원 배송지 주소 목록 가져오기
	@Transactional(readOnly = true)
	public DeliveryDto.DeliveryRead read(String username){
		List<Delivery> deliveries = deliveryDao.findByUsername(username);
		String name = deliveryDao.findByName(username);
		Long pnoTell = deliveryDao.findByPnoTell(username);
		String baseDelivery = deliveryDao.findByBaseDelivery(username);
		String addDelivery = deliveryDao.findByAddDelivery(username);
		return new DeliveryDto.DeliveryRead(deliveries, name, pnoTell, baseDelivery, addDelivery);
	}
	
	
	// 회원 주소 저장
	public Boolean add(DeliveryDto.DeliveryEntity deliveryEntity) {
		// 배송지 번호가 있으면, false로 리턴
		if (deliveryEntity.getDno() != null) {
			return false;
		} else {
			// 없으면 해당 회원 배송지정보 추가
			Delivery newDelivery = new Delivery();
			newDelivery.setDno(deliveryEntity.getDno());
			newDelivery.setUsername(deliveryEntity.getUsername());
			newDelivery.setName(deliveryEntity.getName());
			newDelivery.setPnoTell(deliveryEntity.getPnoTell());
			newDelivery.setBaseDelivery(deliveryEntity.getBaseDelivery());
			newDelivery.setAddDelivery(deliveryEntity.getAddDelivery());

			return deliveryDao.addDelivery(newDelivery) == 1;
		}
	}

	// 배송지 회원 아이디 중복확인용
	public Delivery findByUsername(String username) {
		return deliveryDao.findByUsernameCheck(username);
	}

	// 회원의 전화번호 변경(업데이트)
	public Boolean changePnoTellByMember(DeliveryDto.MemberEntity memberEntity) {
		if (memberEntity.getUsername() == null) {
			return false;

		} else {
			Member chageMember = new Member();
			chageMember.setPnoTell(memberEntity.getPnoTell());
			chageMember.setUsername(memberEntity.getUsername());
			deliveryDao.changePnoTellByMember(chageMember);
			return true;
		}
	}

	// 회원 배송지 정보 변경(업데이트)
	public Boolean change(DeliveryDto.DeliveryEntity deliveryEntity) {
		if (deliveryEntity.getDno() != null) {
			return false;

		} else {
			Delivery changeDelivery = new Delivery();
			changeDelivery.setName(deliveryEntity.getName());
			changeDelivery.setPnoTell(deliveryEntity.getPnoTell());
			changeDelivery.setBaseDelivery(deliveryEntity.getBaseDelivery());
			changeDelivery.setAddDelivery(deliveryEntity.getAddDelivery());
			changeDelivery.setUsername(deliveryEntity.getUsername());

			deliveryDao.change(changeDelivery);
			return true;
		}
	}

}
