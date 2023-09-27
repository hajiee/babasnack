package com.babasnack.demo.delivery.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.babasnack.demo.delivery.dto.DeliveryDto;
import com.babasnack.demo.entity.Delivery;

@Mapper
public interface DeliveryDao {
	// 특정 회원의 배송지 목록 가져오기
	@Select("select * from delivery where username=#{username}")
	public List<Delivery> findByUsername(String username);

	// 회원 배송지 저장
	@Insert("insert into delivery values(delivery_seq.nextval, #{username}, #{name}, #{pnoTell}, #{baseDelivery}, #{addDelivery})")
	public Integer addDelivery(Delivery delivery);

	// 배송지 저장용으로 dno를 불러와 데이터를 얻는 select문
	@Select("select * from delivery where dno=#{dno} and rownum=1")
	public Delivery findByDno(Long dno);

	
	// 배송지 번호와 아이디로 검색 - 추가한 배송지가 등록되어 있는지 확인용
	@Select("select * from delivery where username=#{username} and dno=#{dno}")
	public Delivery findByUsernameAndDno(String username, Long dno);

	
	// 배송지 정보 변경
	@Update("update delivery set dno=#{dno}, name=#{name}, pno_tell=#{pnoTell}, base_delivery=#{baseDelivery}, add_delivery=#{addDelivery} where username=#{username}")
	public Integer change(Long dno, String name, Long pnoTell, String baseDelivery, String addDelivery,
			String username);

}
