package com.babasnack.demo.delivery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.babasnack.demo.delivery.dao.DeliveryDao;
import com.babasnack.demo.delivery.dto.DeliveryDto;
import com.babasnack.demo.entity.Delivery;

@Service
public class DeliveryService {
	@Autowired
	private DeliveryDao deliveryDao;

	// 특정 회원의 배송지 조회
//	@Transactional(readOnly = true)
//	public List<DeliveryDto.DeliveryEntity> search(String username) {
//		return deliveryDao.findByUsername(username);
//	}

	// 회원 주소 저장
//	public Boolean add(String username, Long dno) {
//		DeliveryDto.DeliveryEntity deliveryEntity = deliveryDao.findByUsernameAndDno(username, dno);
//		if (deliveryEntity == null) {
//			// 해당 회원 + 배송지 번호가 없으면 해당 회원 배송지정보 추가
//			DeliveryDto.DeliveryEntity deliveryDno = deliveryDao.findByDno(dno);
//			DeliveryDto.DeliveryEntity newDelivery = new DeliveryDto.DeliveryEntity(1L, username,
//					deliveryDno.getName(), deliveryDno.getPnoTell(), deliveryDno.getBaseDelivery(),
//					deliveryDno.getAddDelivery());
//			deliveryDao.addDelivery(newDelivery);
//			return true;
//
//		} else {
//			// 있으면 false로 리턴
//			return false;
//		}
//	}

	public Boolean add(DeliveryDto.DeliveryEntity deliveryEntity) {
		// 배송지 번호가 있고 해당 회원 아이디 일치시, false로 리턴
		// , String username , username
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

			deliveryDao.addDelivery(newDelivery);
			return true;
		}
	}

	// 회원 배송지 정보 변경(업데이트) , username
	public Boolean change(DeliveryDto.DeliveryEntity deliveryEntity) {
		// , String username

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
