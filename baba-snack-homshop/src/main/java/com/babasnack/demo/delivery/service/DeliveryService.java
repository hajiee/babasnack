package com.babasnack.demo.delivery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.babasnack.demo.delivery.dao.DeliveryDao;
import com.babasnack.demo.delivery.dto.DeliveryDto;

@Service
public class DeliveryService {
	@Autowired
	private DeliveryDao deliveryDao;

	// 특정 회원의 배송지 조회
	@Transactional(readOnly = true)
	public List<DeliveryDto.DeliveryEntity> search(String username) {
		return deliveryDao.findByUsername(username);
	}

	// 회원 주소 저장
	public Boolean add(String username, Long dno) {
		DeliveryDto.DeliveryEntity deliveryEntity = deliveryDao.findByUsernameAndDno(username, dno);
		if (deliveryEntity != null) {
			// 해당 회원 + 배송지 번호가 있으면 false로 리턴
			return false;
		} else {
			// 없으면 해당 회원 배송지정보 추가
			DeliveryDto.DeliveryEntity deliveryDno = deliveryDao.findByDno(dno);
			DeliveryDto.DeliveryEntity newDelivery = new DeliveryDto.DeliveryEntity(dno, username, deliveryDno.getName(), deliveryDno.getPnoTell(), deliveryDno.getBaseDelivery(), deliveryDno.getAddDelivery());
			return deliveryDao.addDelivery(newDelivery) == 1;
		}
	}
		
	// 회원 배송지 정보 변경(업데이트)
	public Boolean change(String name, Long pnoTell, String baseDelivery, String addDelivery, String username) {
		Integer changeResult = deliveryDao.change(name, pnoTell, baseDelivery, addDelivery, username);
		return changeResult == 1;
	}
	
	

}
