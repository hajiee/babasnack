package com.babasnack.demo.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.babasnack.demo.entity.Member;

@Mapper
public interface MemberDao {
    @Insert("INSERT INTO member (username, password, pno_tell, ps_email, join_day) " +
            "VALUES (#{username}, #{password}, #{pnoTell}, #{email}, #{joinDay})")
   public Integer save(Member member);

    @Select("SELECT * FROM member")
    public List<Member> findAllForMember();
    
    @Select("SELECT * FROM member WHERE username = #{username} AND ROWNUM = 1")
    public Member findById(String username);

    @Select("SELECT * FROM member WHERE email = #{email} AND ROWNUM = 1")
    public Member findByEmail(String email);
    
    @Select("SELECT username FROM member WHERE email = #{email} AND ROWNUM = 1")
    public String findUsernameByEmail(String email);
    
    @Select("SELECT * FROM member WHERE email = #{email} AND username = #{username} AND ROWNUM = 1")
   	public Member findByEmailAndUsername(String email, String username);
       

    @Update("UPDATE member SET ps_email = #{email} WHERE username = #{username}")
    public Integer psChangeEm(String email, String username);

    @Update("UPDATE member SET pno_tell = #{pnoTell} WHERE username = #{username}")
    public Integer psChangePno(String pnoTell, String username);

    @Delete("DELETE FROM member WHERE username = #{username}")
    public Integer psWithdrawal(String username);
    
    @Update("UPDATE member SET password = #{password} WHERE username = #{username}")
    public Integer update(Member member);
   
    
    
}
