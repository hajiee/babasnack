package com.babasnack.demo.delivery.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.babasnack.demo.entity.Delivery;
import com.babasnack.demo.entity.Member;

@Mapper
public interface DeliveryDao {
	// 회원 배송지 list로 목록 가져오기
	@Select("select * from delivery where username=#{username}")
	public List<Delivery> findByUsername(String username);
	
	// 회원 배송지 저장
	@Insert("insert into delivery(dno, username, name, pno_tell, base_delivery, add_delivery) values(delivery_seq.nextval, #{username}, #{name}, #{pnoTell}, #{baseDelivery}, #{addDelivery})")
	public Integer addDelivery(Delivery delivery);

	// 배송지 저장시 중복 회원아이디 확인용
	@Select("select * from delivery where username=#{username}")
	public Delivery findByUsernameCheck(String username);

	// 회원 테이블 전화번호 정보 업데이트
	@Update("update member set pno_tell=#{pnoTell} where username=#{username}")
	public Integer changePnoTellByMember(Member member);

	// 배송지 정보 변경
	@Update("update delivery set name=#{name}, pno_tell=#{pnoTell}, base_delivery=#{baseDelivery}, add_delivery=#{addDelivery} where username=#{username}")
	public Integer change(Delivery delivery);
	
	
	// === 배송지 목록 출력용 =======================================
	// 배송지 목록 출력용(name)
	@Select("select name from delivery where username=#{username}")
	public String findByName(String username);

	// 배송지 목록 출력용(pnoTell)
	@Select("select pno_tell from delivery where username=#{username}")
	public Long findByPnoTell(String username);

	// 배송지 목록 출력용(baseDelivery)
	@Select("select base_delivery from delivery where username=#{username}")
	public String findByBaseDelivery(String username);

	// 배송지 목록 출력용(addDelivery)
	@Select("select add_delivery from delivery where username=#{username}")
	public String findByAddDelivery(String username);
	// =========================================================

}
