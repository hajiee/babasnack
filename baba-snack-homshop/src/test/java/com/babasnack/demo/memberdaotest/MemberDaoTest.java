package com.babasnack.demo.memberdaotest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.babasnack.demo.entity.Member;
import com.babasnack.demo.member.dao.MemberDao;

@SpringBootTest
public class MemberDaoTest {
    @Autowired
    MemberDao memberDao;
    @Transactional
    @Test
    public void initTest() {
        assertNotNull(memberDao);
    }
    
//    @Transactional
//    @Test
//    public void insertTest() {
//    	Member member = Member.builder().job("테스트 작업").joinday(LocalDate.of(2023, 8, 15)).build();
//   	 assertEquals(1L,memberDao.save(member));
//    	
//    }
}
